package evaluaciones.retos;

final class Mensaje {
    private final char[] datos;
    Mensaje(int longitud, java.util.Random rng) {
        datos = new char[longitud];
        for (int i = 0; i < datos.length; i++) datos[i] = (char) ('A' + rng.nextInt(26));
    }
    private Mensaje(char[] d) { datos = d; }
    Mensaje mutar(java.util.Random rng) {
        final char[] copia = java.util.Arrays.copyOf(datos, datos.length);
        final int cambios = rng.nextInt(3);
        int hechos = 0;
        while (hechos < cambios) {
            final int pos = rng.nextInt(copia.length);
            copia[pos] = (char) ('A' + rng.nextInt(26));
            hechos++;
        }
        return new Mensaje(copia);
    }
    String texto() { return new String(datos); }

    int hamming(Mensaje otro) {
        int d = 0;
        for (int i = 0; i < datos.length; i++) if (datos[i] != otro.datos[i]) d++;
        return d;
    }
}