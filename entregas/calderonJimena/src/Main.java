import java.io.Console;

public class Main {
    private static final Console console = System.console();

    public static void main(String[] args) {
        console.printf("=== Creación de CustomArray ===\n");
        console.printf("Ingrese el tamaño del custom array: ");
        int tamaño = Integer.parseInt(console.readLine().trim());

        CustomArray miArray = new CustomArray(tamaño);

        int opcion;
        do {
            console.printf("\n=== MENÚ ===\n");
            console.printf("1. Mostrar todo el custom array\n");
            console.printf("2. Mostrar un elemento\n");
            console.printf("3. Modificar un elemento\n");
            console.printf("4. Eliminar un elemento\n");
            console.printf("0. Salir\n");
            console.printf("Seleccione una opción: ");
            opcion = Integer.parseInt(console.readLine().trim());

            switch (opcion) {
                case 1:
                    miArray.mostrarArray();
                    break;
                case 2:
                    console.printf("Ingrese la posición a mostrar: ");
                    int posMostrar = Integer.parseInt(console.readLine().trim());
                    miArray.mostrarElemento(posMostrar);
                    break;
                case 3:
                    console.printf("Ingrese la posición a modificar: ");
                    int posModificar = Integer.parseInt(console.readLine().trim());
                    Object nuevoValor = leerValorPorTipo(miArray.getTipoDato());
                    miArray.modificarElemento(posModificar, nuevoValor);
                    break;
                case 4:
                    console.printf("Ingrese la posición a eliminar: ");
                    int posEliminar = Integer.parseInt(console.readLine().trim());
                    miArray.eliminarElemento(posEliminar);
                    break;
                case 0:
                    console.printf("Saliendo...\n");
                    break;
                default:
                    console.printf("Opción no válida.\n");
            }

        } while (opcion != 0);
    }

    private static Object leerValorPorTipo(String tipo) {
        console.printf("Ingrese el valor: ");
        String entrada = console.readLine().trim();

        switch (tipo) {
            case "entero":
                try {
                    return Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    console.printf("Valor inválido. Usando 0 por defecto.\n");
                    return 0;
                }
            case "double":
                try {
                    return Double.parseDouble(entrada);
                } catch (NumberFormatException e) {
                    console.printf("Valor inválido. Usando 0.0 por defecto.\n");
                    return 0.0;
                }
            case "char":
                if (entrada.length() > 0) return entrada.charAt(0);
                console.printf("Valor inválido. Usando ' ' por defecto.\n");
                return ' ';
            case "string":
            default:
                return entrada;
        }
    }
}
