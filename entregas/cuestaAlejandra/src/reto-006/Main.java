public class Main {
    public static void main(String[] args) {
        IrisEngine iris = new IrisEngine();

        iris.subscribe("alumno1", "CSJ038");
        iris.subscribe("alumno2", "CSJ038");

        Message msg = new Message();
        msg.setSubjectCode("CSJ038");
        msg.setSender("ProfesorX");
        msg.setContent("Actualizados los temas de estudio.");

        iris.processMessage(msg);
    }
}