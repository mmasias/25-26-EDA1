public class Pedido {
    private int id;
    private int minutoLlegada;
    private String nombrePlato;
    private int tiempoTotal;
    private int tiempoRestante;

    private static final String[] NOMBRES = {"Bebida", "Café", "Bocadillo", "Ensalada"};
    private static final int[][] TIEMPOS = {{1, 2}, {2, 3}, {3, 5}, {5, 8}};

    public Pedido(int id, int minutoLlegada) {
        this.id = id;
        this.minutoLlegada = minutoLlegada;

        int indice = (int)(Math.random() * NOMBRES.length);
        this.nombrePlato = NOMBRES[indice];
        int min = TIEMPOS[indice][0];
        int max = TIEMPOS[indice][1];
        
        this.tiempoTotal = min + (int)(Math.random() * (max - min + 1));
        this.tiempoRestante = this.tiempoTotal;
    }

    public int getTiempoTotal() { return tiempoTotal; }
    public int getMinutoLlegada() { return minutoLlegada; }
    public String getNombrePlato() { return nombrePlato; }
    public int getTiempoRestante() { return tiempoRestante; }

    public boolean procesarMinuto() {
        this.tiempoRestante--;
        return (this.tiempoRestante == 0);
    }
}