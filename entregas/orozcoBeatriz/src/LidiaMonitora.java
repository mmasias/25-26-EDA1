public class LidiaMonitora extends Monitor {
    private Fila retenidos = new Fila();

    public LidiaMonitora(String nombre) {
        super(nombre);
    }

    public void recibirNino(Nino n, boolean juegoEnCurso) {
        if (juegoEnCurso) {
            retenidos.agregar(n);
        } else {
            System.out.println(nombre + " pasa a " + n.getNombre() + " con Aisha.");
        }
    }

    public Nino[] liberarNinos() {
        return retenidos.vaciar();
    }
}
