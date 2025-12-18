public class Cliente {

    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);
        lista.agregar(40);
        lista.agregar(50);

        System.out.println("Tamaño: " + lista.tamanio());
        System.out.println("Posición 2: " + lista.obtener(2));

        lista.modificar(2, 999);

        System.out.println("Posición 2 tras modificar: " + lista.obtener(2));
        System.out.println("Contenido completo:");

        for (int i = 0; i < lista.tamanio(); i++) {
            System.out.println(i + " -> " + lista.obtener(i));
        }
    }
}