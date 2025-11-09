import java.util.Random;

public class Mundo {
    private static final int FASE1_MINUTOS = 10;  // 0..9: 0-2 por minuto
    private static final int FASE2_MINUTOS = 20;  // 10..29: <=1 cada 3 min con 50%

    private final Ludoteca ludoteca;
    private final int tiempoApertura;
    private final Random rnd = new Random();

    public Mundo(Ludoteca ludoteca, int tiempoApertura) {
        this.ludoteca = ludoteca;
        this.tiempoApertura = tiempoApertura;
    }

    public void iniciarSimulacion() {
        for (int minuto = 0; minuto < tiempoApertura; minuto++) {
            int llegadas = llegadasEn(minuto);
            for (int i = 0; i < llegadas; i++) ludoteca.llegarNiño();
            ludoteca.actualizar();
            System.out.printf("Min %3d | %s%n", minuto, ludoteca.estado());
        }
    }

    private int llegadasEn(int minuto) {
        if (minuto < FASE1_MINUTOS) {
            return rnd.nextInt(3); // 0,1,2
        } else if (minuto < FASE1_MINUTOS + FASE2_MINUTOS) {
            boolean enMinutoMultiploDe3 = (minuto % 3 == 0);
            return (enMinutoMultiploDe3 && rnd.nextBoolean()) ? 1 : 0;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        Mundo mundo = new Mundo(ludoteca, 120); // 2 horas
        mundo.iniciarSimulacion();
    }
}
