
public class Cliente {
    public static void main(String[] args) {
        Array array = new Array(4);

        array.set(0, 10);
        array.set(1, 20);
        array.set(2, 30);
        array.set(3, 40);

        System.out.println("Contenido del array simulado:");
        for (int i = 0; i < array.longitud(); i++) {
            System.out.print(array.get(i) + " ");
        }

        System.out.println("\n\nCambiamos la posición 1 a 99:");
        array.set(1, 99);

        for (int i = 0; i < array.longitud(); i++) {
            System.out.print(array.get(i) + " ");
        }
    }
}
