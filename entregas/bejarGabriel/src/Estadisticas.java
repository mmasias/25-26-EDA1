import java.io.Console;

class Estadisticas {
    private int totalJuegos = 0;
    private int totalParticipantes = 0;
    private int sumaDuracion = 0;
    private int totalErroresAcumulados = 0;
    private int maximoParticipantes = 0;

    public void registrarJuego(Juego juego) {
        totalJuegos++;
        int participantes = juego.getNumeroParticipantes();
        totalParticipantes += participantes;
        sumaDuracion += juego.getDuracion();
        totalErroresAcumulados += juego.getErroresTotales();
        if (participantes > maximoParticipantes) {
            maximoParticipantes = participantes;
        }
    }

    public double getDuracionMedia() {
        return totalJuegos == 0 ? 0 : (double) sumaDuracion / totalJuegos;
    }

    public double getParticipantesMedios() {
        return totalJuegos == 0 ? 0 : (double) totalParticipantes / totalJuegos;
    }

    public double getErroresMediosPorJuego() {
        return totalJuegos == 0 ? 0 : (double) totalErroresAcumulados / totalJuegos;
    }

    public void imprimirResumen() {
        new Console().writeln("Total juegos: " + totalJuegos);
        new Console().writeln("Duración media: " + String.format("%.2f", getDuracionMedia()) + " minutos");
        new Console().writeln("Participantes medios por juego: " + String.format("%.2f", getParticipantesMedios()));
        new Console().writeln("Errores medios por juego: " + String.format("%.2f", getErroresMediosPorJuego()));
        new Console().writeln("Máximo participantes en un juego: " + maximoParticipantes);
    }
}