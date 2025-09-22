import java.util.Random;

public class Simulador {
    private final int duracionTotalMin = 120;
    private int tiempoActualMin;
    private int ninosEnCola;
    private int juegosRealizados;
    private final Random azar;

    public Simulador(int semilla) {
        this.tiempoActualMin = 0;
        this.ninosEnCola = 0;
        this.juegosRealizados = 0;
        this.azar = new Random(semilla);
    }

    public void simular() {
        System.out.println("--------------------------------------------------");
        System.out.println("INICIO DE LA SIMULACION (0 a 120 minutos)");
        System.out.println("Reglas de llegadas:");
        System.out.println("- Min 0 a 9: llegan entre 0 y 2 ninos por minuto.");
        System.out.println("- Min 10 a 29: cada 3 minutos puede llegar 1 nino (50%).");
        System.out.println("- Min >=30: no llegan mas ninos.");
        System.out.println("Aisha empieza un juego cuando hay mas de 5 ninos en cola.");
        System.out.println("Cada juego dura: 1 min (inicio) + N (pase por N ninos) + 1 min (final) = N+2.");
        System.out.println("--------------------------------------------------");

        while (tiempoActualMin < duracionTotalMin) {
            int llegadasEsteMinuto = Llegadas.llegadasEnMinuto(tiempoActualMin, azar);
            ninosEnCola = ninosEnCola + llegadasEsteMinuto;

            if (ninosEnCola > 5) {
                int duracionJuego = ninosEnCola + 2;
                int ninosEsperandoConLydia = Llegadas.contarLlegadasEntre(
                        tiempoActualMin,
                        tiempoActualMin + duracionJuego,
                        azar
                );
                ResultadoJuego resultado = JuegoTelefono.jugarUnaRonda(ninosEnCola, azar);

                juegosRealizados = juegosRealizados + 1;
                System.out.println();
                System.out.println("=== Juego #" + juegosRealizados + " ===");
                System.out.println("Comenzo en el minuto: " + tiempoActualMin);
                System.out.println("Ninos en cola (N): " + ninosEnCola);
                System.out.println("Duracion del juego: " + duracionJuego + " min");
                System.out.println("Mensaje original:  " + resultado.mensajeOriginal);
                System.out.println("Mensaje final:     " + resultado.mensajeFinal);
                System.out.println("Letras distintas entre original y final: " + resultado.diferencias);
                System.out.println("Llegaron " + ninosEsperandoConLydia + " ninos durante el juego (esperaron con Lydia).");

                tiempoActualMin = tiempoActualMin + duracionJuego;
                ninosEnCola = ninosEnCola + ninosEsperandoConLydia;
            } else {
                tiempoActualMin = tiempoActualMin + 1;
            }
        }

        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("FIN DE LA SIMULACION");
        System.out.println("Tiempo total transcurrido: " + tiempoActualMin + " minutos");
        System.out.println("Juegos realizados: " + juegosRealizados);
        System.out.println("Ninos en cola al cierre: " + ninosEnCola);
        System.out.println("--------------------------------------------------");
    }
}
