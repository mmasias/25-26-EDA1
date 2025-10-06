public class Ludoteca {
    private Lydia lydia;
    private Aisha aisha;
    private Dalsy dalsy;

    public Ludoteca() {
        lydia = new Lydia();
        aisha = new Aisha();
        dalsy = new Dalsy();
    }

    public Lydia getLydia() { return lydia; }
    public Aisha getAisha() { return aisha; }
    public Dalsy getDalsy() { return dalsy; }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================\n");

        mostrarMonitora("LYDIA", lydia.getFila());
        mostrarMonitora("AISHA", aisha.getFila());
        mostrarMonitora("DALSY", dalsy.getFila());

        System.out.println("========================================\n");
    }

    private void mostrarMonitora(String nombre, Nino[] fila) {
        System.out.println(nombre + ":");
        if (fila.length == 0) {
            System.out.println("  Cola vacía\n");
            return;
        }
        System.out.println("  Niños en cola: " + fila.length);
        for (Nino n : fila) {
            System.out.println("  - " + n.getNombre() + " (" + n.getEdad() + " años)");
        }
        System.out.println();
    }
}
