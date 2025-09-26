package evaluaciones.retos;

final class Juego {
    private final java.util.List<Nino> participantes;
    private final java.util.Random rng;
    private final Mensaje original;
    private Mensaje finalMensaje;
    private int duracionMinutos = 0;

    Juego(java.util.List<Nino> participantes, long semilla) {
        this.participantes = new java.util.ArrayList<>(participantes);
        this.rng = new java.util.Random(semilla);
        this.original = new Mensaje(Constantes.LONG_MENSAJE, rng);
    }

    public void jugar() {
        duracionMinutos = 1 + participantes.size() + 1;
        Mensaje actual = original;
        for (final Nino n : participantes) {
            actual = actual.mutar(rng);
        }
        finalMensaje = actual;
        Impresora.registrarPartida(participantes.size(), original.texto(), finalMensaje.texto());
    }

    public int duracion() { return duracionMinutos; }
    public int distorsion() { return original.hamming(finalMensaje); }
    public String resumen() { return String.format("orig=%s final=%s dif=%d", original.texto(), finalMensaje.texto(), distorsion()); }
}
