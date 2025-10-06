public class Nino {
    private String nombreNino;
    private int edadNino;

    public Nino(String nombreNino, int edadNino) {
        this.nombreNino = nombreNino;
        this.edadNino = edadNino;
    }

    public String getNombre() {
        return nombreNino;
    }

    public int getEdad() {
        return edadNino;
    }

    public void presentarse() {
        System.out.println("[" + nombreNino + "]: Hola, soy " + nombreNino + " y tengo " + edadNino + " años");
    }
}