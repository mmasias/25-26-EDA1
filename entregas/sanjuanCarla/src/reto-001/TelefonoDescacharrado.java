import java.util.Random;

public class TelefonoDescacharrado {
    static final int MINUTOS_TOTALES = 120;
    static final int LONGITUD_MENSAJE = 10;
    static final double PROBABILIDAD_CAMBIO_CERO = 0.70;
    static final double PROBABILIDAD_CAMBIO_UNO = 0.20;
    static final double PROBABILIDAD_CAMBIO_DOS = 0.10;
    static final Random NUMERO_ALEATORIO = new Random();

    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion(
                MINUTOS_TOTALES, LONGITUD_MENSAJE,
                PROBABILIDAD_CAMBIO_CERO, PROBABILIDAD_CAMBIO_UNO, PROBABILIDAD_CAMBIO_DOS,
                NUMERO_ALEATORIO
        );
        simulacion.ejecutar();
        simulacion.imprimirResumen();
    }
}
