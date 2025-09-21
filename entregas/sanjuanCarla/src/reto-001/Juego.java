public class Juego {
    private final int identificador;
    private final int minutoDeInicio;
    private final int cantidadDeParticipantes;
    private final String mensajeOriginal;
    private final String mensajeFinal;
    private final int distanciaDeHamming;

    public Juego(int identificador, int minutoDeInicio, int cantidadDeParticipantes,
                 String mensajeOriginal, String mensajeFinal, int distanciaDeHamming) {
        this.identificador = identificador;
        this.minutoDeInicio = minutoDeInicio;
        this.cantidadDeParticipantes = cantidadDeParticipantes;
        this.mensajeOriginal = mensajeOriginal;
        this.mensajeFinal = mensajeFinal;
        this.distanciaDeHamming = distanciaDeHamming;
    }

    public String toString() {
        return String.format(
            "Juego#%d inicio=%d participantes=%d original='%s' final='%s' cambios=%d",
            identificador, minutoDeInicio, cantidadDeParticipantes,
            mensajeOriginal, mensajeFinal, distanciaDeHamming
        );
    }
}
