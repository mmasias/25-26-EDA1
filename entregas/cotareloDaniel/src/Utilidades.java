public class Utilidades {
    public static int numeroEnteroAleatorio(int minimo, int maximo) {
        double aleatorioCrudo;
        aleatorioCrudo = Math.random();

        double escala;
        escala = aleatorioCrudo * (maximo - minimo + 1);

        int entero;
        entero = (int) escala;

        int resultado;
        resultado = entero + minimo;
        return resultado;
    }

    public static boolean ocurreConPorcentaje(int porcentaje) {
        int numeroAleatorio;
        numeroAleatorio = numeroEnteroAleatorio(1, 100);

        boolean ocurre;
        if (numeroAleatorio <= porcentaje) {
            ocurre = true;
        } else {
            ocurre = false;
        }

        boolean resultado;
        resultado = ocurre;
        return resultado;
    }
}
