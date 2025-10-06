public class Ludoteca {
    private Aisha monitoraAisha;
    private Lydia monitoraLydia;
    private Dalsy monitoraDalsy;

    public Ludoteca() {
        monitoraAisha = new Aisha();
        monitoraLydia = new Lydia();
        monitoraDalsy = new Dalsy();
    }

    public void llegadaNino(String nombre, int edad) {
        Nino nuevo = new Nino(nombre, edad);
        System.out.println("Llega " + nuevo + "\n" + nombre + " pasa a la cola de Lydia\n");
        monitoraLydia.agregarNino(nuevo);
    }

    public void intentoInicioJuego() {
        if (monitoraLydia.getColaNinos().cantidad() < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego\nSe necesitan al menos 5 niños\n");
        } else {
            System.out.println("Lydia transfiere sus niños a Aisha");
            monitoraLydia.transferirNinos(monitoraAisha);
            for (Nino n : monitoraAisha.getColaNinos().obtenerNinos()) {
                System.out.println("- " + n);
            }
            System.out.println();
        }
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        mostrarMonitora(monitoraLydia);
        mostrarMonitora(monitoraAisha);
        mostrarMonitora(monitoraDalsy);
        System.out.println("========================================\n");
    }

    private void mostrarMonitora(Monitora m) {
        System.out.println(m.getNombreMonitora().toUpperCase() + ":");
        if (m.getColaNinos().estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + m.getColaNinos().cantidad());
            for (Nino n : m.getColaNinos().obtenerNinos()) {
                System.out.println("  - " + n);
            }
        }
        System.out.println();
    }

    // Aquí puedes añadir el resto de opciones (3–12) según el enunciado
}

