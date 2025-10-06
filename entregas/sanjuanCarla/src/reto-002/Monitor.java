public class Monitor {
    private String nombre;
    private ListaNiños niños;
    private final int CAPACIDAD_MAXIMA = 15;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.niños = new ListaNiños();
    }

    public void recibirNiño(Niño niño) {
        if (niños.tamaño() >= CAPACIDAD_MAXIMA) {
            System.out.println("ERROR: " + nombre + " no puede recibir más niños. Cola llena.");
            return;
        }
        niños.añadir(niño);
    }

    public ListaNiños getListaNiños() { return niños; }
    public String getNombre() { return nombre; }
    public int cantidadNiños() { return niños.tamaño(); }

    public void mostrarLista() {
        System.out.print(nombre + ": ");
        if (niños.vacia()) {
            System.out.println("Cola vacía");
        } else {
            niños.mostrar();
        }
    }
}
