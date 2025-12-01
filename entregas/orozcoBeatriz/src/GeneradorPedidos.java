import java.util.Random;

public class GeneradorPedidos {

    private double probabilidadLlegada;
    private Random generadorAleatorio;

    public GeneradorPedidos(double probabilidadLlegada) {
        this.probabilidadLlegada = probabilidadLlegada;
        this.generadorAleatorio = new Random();
    }

    public Pedido generarPedido(int minutoActual) {
        if (generadorAleatorio.nextDouble() < probabilidadLlegada) {
            TipoPlato tipo = TipoPlato.obtenerAleatorio(generadorAleatorio);
            int tiempo = tipo.obtenerTiempoAleatorio(generadorAleatorio);
            return new Pedido(tipo, tiempo, minutoActual);
        }
        return null;
    }
}
