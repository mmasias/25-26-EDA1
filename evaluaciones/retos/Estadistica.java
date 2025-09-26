package evaluaciones.retos;

final class Estadisticas {
    private final java.util.List<Juego> partidas = new java.util.ArrayList<>();

    void registrar(Juego j) { partidas.add(j); }

    void imprimirResumen(int totalMinutos) {
        final int total = partidas.size();
        double suma = 0.0;
        Juego peor = null;
        int max = -1;
        for (final Juego j : partidas) {
            suma += j.distorsion();
            if (j.distorsion() > max) { max = j.distorsion(); peor = j; }
        }
        final double media = total == 0 ? 0.0 : suma / total;
        Impresora.imprimir("--- Resumen de simulación ---");
        Impresora.imprimir("Minutos simulados: " + totalMinutos);
        Impresora.imprimir("Partidas jugadas: " + total);
        Impresora.imprimir(String.format("Distorsión media (Hamming): %.2f", media));
        Impresora.imprimir("Mensaje más dañado: " + (peor == null ? "-" : peor.resumen()));
    }
}
