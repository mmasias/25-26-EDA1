class Estadisticas {
    private int totalJuegos = 0;
    private int totalParticipantes = 0;
    private int sumaDuracion = 0;
    public void registrarJuego(Juego juego) {
        totalJuegos++;
        totalParticipantes += juego.getNumeroParticipantes();
        sumaDuracion += juego.getDuracion();
    }
    public double getDuracionMedia() {
        return totalJuegos == 0 ? 0 : (double) sumaDuracion / totalJuegos;
    }
    public double getParticipantesMedios() {
        return totalJuegos == 0 ? 0 : (double) totalParticipantes / totalJuegos;
    }
    public void imprimirResumen() {
        System.out.println("Total juegos: " + totalJuegos);
        System.out.printf("Duración media: %.2f minutos%n", getDuracionMedia());
        System.out.printf("Participantes medios por juego: %.2f%n", getParticipantesMedios());
    }
}