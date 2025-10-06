public class Nino {
    private String nombreNino;
    private int edadNino;

    public Nino(String nombreNino, int edadNino) {
        this.nombreNino = nombreNino;
        this.edadNino = edadNino;
    }

    public String getNombreNino() {
        return nombreNino;
    }

    public int getEdadNino() {
        return edadNino;
    }

    public String presentarse() {
        return "Hola, soy " + nombreNino + " y tengo " + edadNino + " años";
    }

    public boolean esMayorQue(int edadMinima) {
        return edadNino >= edadMinima;
    }

    public boolean nombreEmpiezaCon(char letraInicial) {
        return nombreNino.toLowerCase().charAt(0) == Character.toLowerCase(letraInicial);
    }

    public String toString() {
        return nombreNino + " (" + edadNino + " años)";
    }
}
