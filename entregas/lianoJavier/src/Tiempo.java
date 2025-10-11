
public class Tiempo {

    private int minutos;

    public boolean menosOIgualDe(int minutos) {
        return this.minutos <= minutos;
    }

    public void siguiente() {
        minutos++;
    }

    public int getMinutosTotales() {
        return minutos;
    }

}
