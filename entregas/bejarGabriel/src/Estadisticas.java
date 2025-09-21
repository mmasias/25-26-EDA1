public class Estadisticas {
    private int totalNiñosLlegados = 0;
    private int totalJuegos = 0;
    private int totalNiñosProcesados = 0;
    private int sumaDistanciasHamming = 0;
    private int maxDistancia = 0;
    private int maxParticipantes = 0;

    public void registrarLlegadas(int n) { totalNiñosLlegados += n; }

    public void registrarJuego(int participantes, int distanciaHamming) {
        totalJuegos++;
        totalNiñosProcesados += participantes;
        sumaDistanciasHamming += distanciaHamming;
        if (distanciaHamming > maxDistancia) maxDistancia = distanciaHamming;
        if (participantes > maxParticipantes) maxParticipantes = participantes;
    }

    public void imprimirInforme() {
        System.out.println("Niños llegados totales: " + totalNiñosLlegados);
        System.out.println("Juegos realizados: " + totalJuegos);
        System.out.println("Niños que participaron en juegos: " + totalNiñosProcesados);
        System.out.println("Máximo participantes en un juego: " + maxParticipantes);
        if (totalJuegos > 0) {
            System.out.printf("Distancia Hamming media: %.2f%n",
                    (double) sumaDistanciasHamming / totalJuegos);
            System.out.println("Máxima distancia Hamming: " + maxDistancia);
        } else {
            System.out.println("No se realizaron juegos.");
        }
    }
}
