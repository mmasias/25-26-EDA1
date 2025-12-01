public class Console {

        public static void imprimir(String texto) {
                System.out.print(texto);
        }

        public static void imprimirln(String texto) {
                System.out.println(texto);
        }

        public static void saltoLinea() {
                System.out.println();
        }

        public static void separador() {
                imprimirln("========================================");
        }

        public static void separadorCorto() {
                imprimirln("----------------------------------------");
        }
}
