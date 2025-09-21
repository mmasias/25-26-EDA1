public class Metrica {
    static class Partida {
        int inicio;
        int nParticipantes;
        int duracion;
        String original;
        String finalizado;
        int distancia;
    }

    private Partida[] partidas = new Partida[128];
    private int contador = 0;

    int llegadosFaseA = 0;
    int llegadosFaseB = 0;
    int minutosUso = 0;

    public void registrarPartida(int inicio, int n, int dur, String original, String fin, int d) {
        if (contador == partidas.length) {
            Partida[] np = new Partida[partidas.length * 2];
            for (int i = 0; i < partidas.length; i++) np[i] = partidas[i];
            partidas = np;
        }
        Partida p = new Partida();
        p.inicio = inicio; p.nParticipantes = n; p.duracion = dur;
        p.original = original; p.finalizado = fin; p.distancia = d;
        partidas[contador++] = p;
    }

    public void sumarUso(int dur) { minutosUso += dur; }

    public double promedioHamming() {
        if (contador == 0) return 0.0;
        int suma = 0;
        for (int i = 0; i < contador; i++) suma += partidas[i].distancia;
        return suma / (double) contador;
    }

    public int maximoHamming() {
        int m = 0;
        for (int i = 0; i < contador; i++) if (partidas[i].distancia > m) m = partidas[i].distancia;
        return m;
    }

    public double promedioParticipantes() {
        if (contador == 0) return 0.0;
        int suma = 0;
        for (int i = 0; i < contador; i++) suma += partidas[i].nParticipantes;
        return suma / (double) contador;
    }

    public void imprimirResumen(int remanentes) {
        System.out.println("\n=== Resumen Global ===");
        System.out.println("Partidas: " + contador);
        System.out.println("Llegados fase 0-9: " + llegadosFaseA);
        System.out.println("Llegados fase 10-29: " + llegadosFaseB);
        System.out.printf("Utilización: %.4f (%d/120)%n", minutosUso / 120.0, minutosUso);
        System.out.printf("Hamming promedio: %.3f, máximo: %d%n", promedioHamming(), maximoHamming());
        System.out.printf("Participantes promedio: %.3f%n", promedioParticipantes());
        System.out.println("Niños remanentes en cola: " + remanentes);

        System.out.println("\n=== Resumen por Partida ===");
        if (contador == 0) {
            System.out.println("(N/A)");
            return;
        }
        System.out.println("inicio | N | dur | original       | final          | Hamming");
        for (int i = 0; i < contador; i++) {
            Partida p = partidas[i];
            System.out.printf("%6d | %2d | %3d | %s | %s | %7d%n",
                    p.inicio, p.nParticipantes, p.duracion, p.original, p.finalizado, p.distancia);
        }
    }
}
