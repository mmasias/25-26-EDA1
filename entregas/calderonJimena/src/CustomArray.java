import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class CustomArray {
    private static final Console console = System.console();

    private int tamaño;
    private List<Object> elementos;
    private String tipoDato;

    public CustomArray(int tamaño) {
        this.tamaño = tamaño;
        this.elementos = new LinkedList<>();

        console.printf("¿Qué tipo de dato tendrá el array?\n");
        console.printf("1. entero\n");
        console.printf("2. string\n");
        console.printf("3. double\n");
        console.printf("4. char\n");
        console.printf("Seleccione una opción (1-4): ");

        String opcion = console.readLine().trim();

        switch (opcion) {
            case "1":
                this.tipoDato = "entero";
                break;
            case "2":
                this.tipoDato = "string";
                break;
            case "3":
                this.tipoDato = "double";
                break;
            case "4":
                this.tipoDato = "char";
                break;
            default:
                console.printf("Opción no válida, se usará 'string' por defecto.\n");
                this.tipoDato = "string";
        }

        console.printf("Tipo de dato seleccionado: %s\n", tipoDato);

        for (int i = 0; i < tamaño; i++) {
            elementos.add(null);
        }
    }

    public void mostrarArray() {
        console.printf("Contenido del array:\n");
        for (int i = 0; i < elementos.size(); i++) {
            console.printf("[%d]: %s\n", i, elementos.get(i));
        }
    }

    public void mostrarElemento(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            console.printf("Error: la posición %d está fuera de rango.\n", posicion);
            return;
        }
        console.printf("Elemento en posición %d: %s\n", posicion, elementos.get(posicion));
    }

    public void modificarElemento(int posicion, Object elemento) {
        if (posicion < 0 || posicion >= tamaño) {
            console.printf("Error: la posición %d no existe en el array.\n", posicion);
            return;
        }
        if (!esTipoValido(elemento)) {
            console.printf("Error: el elemento no es del tipo correcto (%s).\n", tipoDato);
            return;
        }
        elementos.set(posicion, elemento);
        console.printf("Elemento modificado en posición %d.\n", posicion);
    }

    private boolean esTipoValido(Object elemento) {
        switch (tipoDato) {
            case "entero":
                return elemento instanceof Integer;
            case "string":
                return elemento instanceof String;
            case "double":
                return elemento instanceof Double;
            case "char":
                return elemento instanceof Character;
            default:
                return false;
        }
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void eliminarElemento(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            console.printf("Error: la posición %d no existe en el array.\n", posicion);
            return;
        }
        elementos.set(posicion, null);
        console.printf("Elemento en posición %d eliminado (establecido a null).\n", posicion);
    }

}