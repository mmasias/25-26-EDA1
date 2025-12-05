public class Pedido {

    private ItemMenu item;
    private int tiempoPreparacion;
    private int tiempoEspera;

    public Pedido(ItemMenu item) {
        this.item = item;
        this.tiempoPreparacion = generarTiempo(item);
        this.tiempoEspera = 0;
    }

    private int generarTiempo(ItemMenu item) {
        int mininmo = item.obtenerMinTiempo();
        int maximo = item.obtenerMaxTiempo();
        return mininmo + (int) (Math.random() * (maximo - mininmo + 1));
    }

    public void incrementarTiempoEspera() {
        tiempoEspera++;
    }

    public String getNombre() {
        return item.obtenerNombre();
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }
}
