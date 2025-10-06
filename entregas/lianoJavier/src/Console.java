
public class Console {

        public static void imprimirln(String mensaje) {
                imprimir(mensaje);
                linea();
        }

        public static void imprimir(String mensaje) {
                System.out.print(mensaje);
        }

        public static void linea() {
                System.out.println();
        }

}
