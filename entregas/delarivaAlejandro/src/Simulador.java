public class Simulador {

    static class Juego {
        int inicio;
        int participantes;
        int duracion;
        String mensajeOriginal;
        String mensajeFinal;
        int distanciaHamming;
    }

    private Juego[] registros = new Juego[128];
    private int cantidad = 0;

    private int faseCorta = 0;
    private int faseLarga = 0;
    private int tiempoTotal = 0;

    public void sumarFaseCorta(int n) {
        faseCorta += n;
    }

    public void sumarFaseLarga() {
        faseLarga++;
    }

    public int getFaseCorta() {
        return faseCorta;
    }

    public int getFaseLarga() {
        return faseLarga;
    }

    public void registrarJuego(int inicio, int n, int dur, String original, String fin, int distancia) {
        if (cantidad == registros.length) {
            Juego[] nuevo = new Juego[registros.length * 2];
            for (int i = 0; i < registros.length; i++) nuevo[i] = registros[i];
            registros = nuevo;
        }
        Juego j = new Juego();
        j.inicio = inicio;
        j.participantes = n;
        j.duracion = dur;
        j.mensajeOriginal = original;
        j.mensajeFinal = fin;
        j.distanciaHamming = distancia;
        registros[cantidad++] = j;
    }

    public void sumarUso(int minutos) {
        tiempoTotal += minutos;
    }

    public double promedioHamming() {
        if (cantidad == 0) return 0.0;
        int suma = 0;
        for (int i = 0; i < cantidad; i++) suma += registros[i].distanciaHamming;
        return suma / (double) cantidad;
    }

    public int maximoHamming() {
        int max = 0;
        for (int i = 0; i < cantidad; i++) {
            if (registros[i].distanciaHamming > max) max = registros[i].distanciaHamming;
        }
        return max;
    }

    public double promedioParticipantes() {
        if (cantidad == 0) return 0.0;
        int suma = 0;
        for (int i = 0; i < cantidad; i++) suma += registros[i].participantes;
        return suma / (double) cantidad;
    }

    public void imprimirResumen(int enCola) {
        System.out.println("\n=== Estadísticas Generales ===");
        System.out.println("Juegos registrados: " + cantidad);
        System.out.println("Llegados fase 0-9: " + faseCorta);
        System.out.println("Llegados fase 10-29: " + faseLarga);
        System.out.printf("Tiempo de uso: %.4f (%d/120)%n", tiempoTotal / 120.0, tiempoTotal);
        System.out.printf("Distancia Hamming promedio: %.3f, máxima: %d%n", promedioHamming(), maximoHamming());
        System.out.printf("Participantes promedio: %.3f%n", promedioParticipantes());
        System.out.println("Niños restantes en cola: " + enCola);

        System.out.println("\n=== Detalle de Juegos ===");
        if (cantidad == 0) {
            System.out.println("(N/A)");
            return;
        }
        System.out.println("inicio | N | dur | original       | final          | Hamming");
        for (int i = 0; i < cantidad; i++) {
            Juego j = registros[i];
            System.out.printf("%6d | %2d | %3d | %s | %s | %7d%n",
                    j.inicio, j.participantes, j.duracion, j.mensajeOriginal, j.mensajeFinal, j.distanciaHamming);
        }
    }
}
