
public class Monitor {
    private String nombre;
    private Niño[] listaNiños;
    private int cantidad; 

    public static final int MAX_NINOS = 15;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.listaNiños = new Niño[MAX_NINOS];
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Niño[] getListaNiños() {
        return listaNiños;
    }

    public int cantidadNiños() {
        return cantidad;
    }

    public boolean agregarNiño(Niño n) {
        if (cantidad < MAX_NINOS) {
            listaNiños[cantidad] = n;
            cantidad++;
            return true;
        }
        return false; 
    }

    public void presentarNiños() {
        System.out.println("Monitora " + nombre + " presenta a sus niños:");
        for (int i = 0; i < cantidad; i++) {
            listaNiños[i].presentarse();
        }
    }
}
