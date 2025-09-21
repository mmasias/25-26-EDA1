public class Monitor {

    private String nombre;

    public Monitor(String nombre) {
        this.nombre = nombre;
    }

    public void recibirNino(Nino n, Ludoteca ludoteca) {
        if (nombre.equals("Lydia")) {
            if (ludoteca.isJuegoEnCurso()) {
                ludoteca.getSalaDeEspera().encolar(n);
                System.out.println(nombre + " recibe a " + n.getNombre() + " y lo deja en la sala de espera.");
            } else {
                ludoteca.getAisha().formarFila(n, ludoteca.getFila());
                System.out.println(nombre + " recibe a " + n.getNombre() + " y lo pasa a la fila.");
            }
        }
    }

    public void formarFila(Nino n, Cola fila) {
        if (nombre.equals("Aisha")) {
            fila.encolar(n);
            System.out.println(nombre + " coloca a " + n.getNombre() + " en la fila.");
        }
    }

    public String getNombre() {
        return nombre;
    }
}