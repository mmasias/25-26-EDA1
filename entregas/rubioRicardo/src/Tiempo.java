public class Tiempo {
    private int minutosTotales = 0;

    public int horas() { return minutosTotales / 60; }

    public int minutos() { return minutosTotales % 60; }

    public int totalMinutos() { return minutosTotales; }

    public void pasarMinuto() { minutosTotales++; }

    @Override
    public String toString() {
        return horas() + ":" + String.format("%02d", minutos());
    }
}
