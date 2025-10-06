public abstract class Monitor {

    protected String nombre;
    protected Fila fila;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.fila = new Fila();
    }

    public String getNombre() {
        return nombre;
    }

    public int tamanoFila() {
        return fila.size();
    }

    public Fila getFila() {
        return fila;
    }

    public void agregarAFila(Nino n) {
        agregarAFila(n, false);
    }

    public void agregarAFila(Nino n, boolean silencioso) {
        fila.agregar(n);
        if (!silencioso) {
            System.out.println(nombre + " recibe a " + n.getNombre() + " (" + n.getEdad() + " años) y pasa a la cola.");
        }
    }

    public Nino[] vaciar() {
        return fila.vaciar();
    }

    public Nino[] transferirTodos() {
        return vaciar();
    }

    public void recibirMuchosSilencioso(Nino[] arr) {
        for (int i = 0; i < arr.length; i++) {
            agregarAFila(arr[i], true);
        }
    }
}
