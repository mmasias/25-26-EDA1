public class Pedido {

    private Plato plato;
    private int tiempoPreparacion;
    private int tiempoEspera;

    public Pedido(Plato plato) {
        this.plato = plato;
        this.tiempoPreparacion = generarTiempo(plato);
        this.tiempoEspera = 0;
    }

    private int generarTiempo(Plato plato) {
        int min = plato.obtenerMinTiempo();
        int max = plato.obtenerMaxTiempo();
        return min + (int)(Math.random() * (max - min + 1));
    }

    public void incrementarTiempoEspera() {
        tiempoEspera++;
    }

    public String getNombre() { 
        return plato.obtenerNombre(); 
    }

    public int getTiempoPreparacion() { 
        return tiempoPreparacion; 
    }

    public int getTiempoEspera() { 
        return tiempoEspera; 
    }
}
