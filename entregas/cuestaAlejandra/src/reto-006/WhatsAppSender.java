import java.util.List;

public class WhatsAppSender {

    public void send(Summary summary) {
        if (summary == null || summary.getRecipients() == null) return;

        for (String recipient : summary.getRecipients()) {
            System.out.println("📩 Enviando a " + recipient + ": \"" + summary.getSummarizedContent() + "\"");
        }
    }
}