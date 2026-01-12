public class TipoDePlato {
        public static final int BEBIDA = 0;
        public static final int CAFE = 1;
        public static final int COLACAO = 2;
        public static final int BOCADILLO = 3;
        public static final int ENSALADA = 4;

        private static final int CANTIDAD_TIPOS = 5;

        private static final String[] NOMBRES = {
                        "Bebida",
                        "Café",
                        "Colacao",
                        "Bocadillo",
                        "Ensalada"
        };

        private static final int[] TIEMPOS_MINIMOS = { 1, 2, 2, 3, 5 };
        private static final int[] TIEMPOS_MAXIMOS = { 2, 3, 4, 5, 8 };

        public static String obtenerNombre(int tipo) {
                return NOMBRES[tipo];
        }

        public static int obtenerTiempoMinimo(int tipo) {
                return TIEMPOS_MINIMOS[tipo];
        }

        public static int obtenerTiempoMaximo(int tipo) {
                return TIEMPOS_MAXIMOS[tipo];
        }

        public static int generarTiempoAleatorio(int tipo) {
                int minimo = obtenerTiempoMinimo(tipo);
                int maximo = obtenerTiempoMaximo(tipo);
                int rango = maximo - minimo + 1;
                return minimo + (int) (Math.random() * rango);
        }

        public static int generarTipoAleatorio() {
                return (int) (Math.random() * CANTIDAD_TIPOS);
        }

        public static void main(String[] args) {
                Console.imprimirln("=== Pruebas de TipoDePlato ===");
                Console.saltoLinea();

                Console.imprimirln("Probando nombres:");
                for (int tipo = 0; tipo < CANTIDAD_TIPOS; tipo++) {
                        Console.imprimirln("Tipo " + tipo + ": " + obtenerNombre(tipo));
                }

                Console.saltoLinea();
                Console.imprimirln("Probando tiempos:");
                for (int tipo = 0; tipo < CANTIDAD_TIPOS; tipo++) {
                        Console.imprimirln(obtenerNombre(tipo) + ": " +
                                        obtenerTiempoMinimo(tipo) + "-" + obtenerTiempoMaximo(tipo) + " min");
                }

                Console.saltoLinea();
                Console.imprimirln("Probando generación de tiempos aleatorios:");
                for (int tipo = 0; tipo < CANTIDAD_TIPOS; tipo++) {
                        int tiempo = generarTiempoAleatorio(tipo);
                        boolean dentroDeRango = tiempo >= obtenerTiempoMinimo(tipo) &&
                                        tiempo <= obtenerTiempoMaximo(tipo);
                        Console.imprimirln(obtenerNombre(tipo) + ": " + tiempo +
                                        " min (válido: " + dentroDeRango + ")");
                }

                Console.saltoLinea();
                Console.imprimirln("Probando generación de tipos aleatorios:");
                for (int iteracion = 0; iteracion < 10; iteracion++) {
                        int tipo = generarTipoAleatorio();
                        Console.imprimirln("Tipo generado: " + obtenerNombre(tipo));
                }

                Console.saltoLinea();
                Console.imprimirln("=== Todas las pruebas completadas ===");
        }
}
