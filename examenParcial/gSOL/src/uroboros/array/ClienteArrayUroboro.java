package uroboros.array;

public class ClienteArrayUroboro {
    public static void main(String[] args) {
        System.out.println("UROBOROS: Array usando Lista (Lista internamente usa array de nodos)");
        System.out.println();

        Array array = new Array(5);

        System.out.println("INICIALIZACIÓN");
        System.out.println("Capacidad: " + array.longitud());

        System.out.println();
        System.out.println("ESTABLECIENDO VALORES");
        array.set(0, 100);
        array.set(1, 200);
        array.set(2, 300);
        array.set(3, 400);
        array.set(4, 500);

        System.out.println();
        System.out.println("CONTENIDO DEL ARRAY");
        for (int i = 0; i < array.longitud(); i++) {
            System.out.println("Posición " + i + ": " + array.get(i));
        }

        System.out.println();
        System.out.println("MODIFICANDO POSICIÓN 2");
        array.set(2, 999);

        System.out.println();
        System.out.println("CONTENIDO ACTUALIZADO");
        for (int i = 0; i < array.longitud(); i++) {
            System.out.println("Posición " + i + ": " + array.get(i));
        }

        System.out.println();
        System.out.println("La serpiente se muerde la cola:");
        System.out.println("Array → usa Lista → usa array de nodos (int[][])");
    }
}
