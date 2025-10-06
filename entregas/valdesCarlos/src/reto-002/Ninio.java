
public class Ninio {
    private String nombre;
    private int edad;
    private Pizarra pizarrin;

    public Ninio(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.pizarrin = new Pizarra();
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public void limpiarPizarrin() {
        pizarrin.limpiar();
    }

    
    public void copiarMensaje(String mensaje) {
        if (mensaje == null) mensaje = "";
        String result = distorsionarMensaje(mensaje);
        pizarrin.escribir(result);
    }

    public String leerMensaje() {
        return pizarrin.leer();
    }

    private String distorsionarMensaje(String mensaje) {
        if (mensaje.length() == 0) return mensaje;
        
        int cambios = (int) (Math.random() * 3); 
        StringBuilder sb = new StringBuilder(mensaje);
        for (int k = 0; k < cambios; k++) {
            int pos = (int) (Math.random() * mensaje.length());
            char c = (char) ('A' + (int) (Math.random() * 26));
            sb.setCharAt(pos, c);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
