import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class IrisEngine {

    
    private Map<String, Set<String>> subjectToSubscribers = new ConcurrentHashMap<>();

    private Map<String, Set<String>> studentToSubjects = new ConcurrentHashMap<>();

    private Map<String, List<Message>> pendingMessagesBySubject = new ConcurrentHashMap<>();

    public void processMessage(Message message) {
        if (message == null || message.getSubjectCode() == null || message.getSubjectCode().trim().isEmpty()) {
            System.out.println("❌ Mensaje descartado: sin asignatura válida.");
            return;
        }

        String subjectCode = message.getSubjectCode();

        Set<String> subscribers = subjectToSubscribers.get(subjectCode);
        if (subscribers == null || subscribers.isEmpty()) {
            System.out.println("✅ Asignatura '" + subjectCode + "' no tiene suscriptores. No se envía resumen.");
            return;
        }

        Summary summary = new Summary();
        summary.setSubjectCode(subjectCode);
        summary.setSender(message.getSender());
        summary.setSummarizedContent(extractSummary(message.getContent()));
        summary.setCreatedAt(Instant.now());
        summary.setRecipients(new ArrayList<>(subscribers));

        WhatsAppSender sender = new WhatsAppSender();
        sender.send(summary);

        message.setProcessed(true);
    }

    public void subscribe(String studentId, String subjectCode) {
        if (studentId == null || subjectCode == null) return;

        Subscription sub = new Subscription();
        sub.setStudentId(studentId);
        sub.setSubjectCode(subjectCode);
        sub.setSubscribedAt(Instant.now());
        sub.setActive(true);

        subjectToSubscribers.computeIfAbsent(subjectCode, k -> new HashSet<>()).add(studentId);

        studentToSubjects.computeIfAbsent(studentId, k -> new HashSet<>()).add(subjectCode);

        System.out.println("✅ " + studentId + " suscrito a " + subjectCode);
    }

    public void unsubscribe(String studentId, String subjectCode) {
        if (studentId == null || subjectCode == null) return;

        if (subjectToSubscribers.containsKey(subjectCode)) {
            subjectToSubscribers.get(subjectCode).remove(studentId);
        }
        if (studentToSubjects.containsKey(studentId)) {
            studentToSubjects.get(studentId).remove(subjectCode);
        }

        System.out.println("✅ " + studentId + " desuscrito de " + subjectCode);
    }

    private String extractSummary(String content) {
        return "ha actualizado información relevante.";
    }
}