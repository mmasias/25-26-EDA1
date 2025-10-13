
public class Tiempo {

    private static final int MINUTOS_POR_HORA = 60;
    private int minutos;

    public Tiempo(int horas, int minutos) {
        this.minutos = horas * MINUTOS_POR_HORA + minutos;
    }

    public boolean terminado() {
        return minutos <= 0;
    }

    public void siguiente() {
        if (minutos > 0)
            minutos--;
    }

    public void imprimir() {
        Console.imprimir("Tiempo restante: " + (minutos / MINUTOS_POR_HORA) + " horas y " + (minutos % MINUTOS_POR_HORA) + " minutos.");
        Console.imprimirLinea();
    }

    public int getMinutos() {
        return minutos;
    }

}
