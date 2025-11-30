import java.util.Random;

public class ContextoSimulacion {
    private int totalDeComparaciones = 0;
    private int tiempoTotalDeEspera = 0;
    private int numeroDePedidosAtendidos = 0;
    private int duracionDeLaJornadaEnMinutos = 120;
    private double probabilidadDeLlegada = 0.4;
    private Random generadorAleatorio = new Random();

    public void sumarComparaciones(int cantidad) {
        totalDeComparaciones += cantidad;
    }

    public void sumarTiempoEspera(int tiempo) {
        tiempoTotalDeEspera += tiempo;
    }

    public void incrementarPedidosAtendidos() {
        numeroDePedidosAtendidos++;
    }

    public int getTotalDeComparaciones() { return totalDeComparaciones; }
    public int getTiempoTotalDeEspera() { return tiempoTotalDeEspera; }
    public int getNumeroDePedidosAtendidos() { return numeroDePedidosAtendidos; }
    public int getDuracionDeLaJornadaEnMinutos() { return duracionDeLaJornadaEnMinutos; }
    public double getProbabilidadDeLlegada() { return probabilidadDeLlegada; }
    public Random getGeneradorAleatorio() { return generadorAleatorio; }
}