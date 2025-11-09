import a000.Array;

public class ClienteArray {
    public static void main(String[] args) {
        Array miArray = new Array(5);

        System.out.println("INICIALIZACIÓN");
        System.out.println("Capacidad: " + miArray.longitud());

        System.out.println();
        System.out.println("ESTABLECIENDO VALORES");
        miArray.set(0, 100);
        miArray.set(1, 200);
        miArray.set(2, 300);
        miArray.set(3, 400);
        miArray.set(4, 500);

        System.out.println();
        System.out.println("CONTENIDO DEL ARRAY");
        for (int i = 0; i < miArray.longitud(); i++) {
            System.out.println("Posición " + i + ": " + miArray.get(i));
        }

        System.out.println();
        System.out.println("MODIFICANDO POSICIÓN 2");
        miArray.set(2, 999);

        System.out.println();
        System.out.println("CONTENIDO ACTUALIZADO");
        for (int i = 0; i < miArray.longitud(); i++) {
            System.out.println("Posición " + i + ": " + miArray.get(i));
        }
    }
}
