public class Pizarra {
    private String mensaje = "";

    public void escribir(String m) {
        mensaje = m;
    }

    public String leer() {
        return mensaje;
    }
}
