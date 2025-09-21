public class Tiempo {

    private int minutosTotales = 0;

    public int horas() {
        return minutosTotales / 60;
    }

    public void pasarMinuto() {
        minutosTotales++;
    }

    public int minutos() {
        return minutosTotales % 60;
    }

}
