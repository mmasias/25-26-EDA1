public class Cliente {
    public static void main(String[] args) {
        Lista lista = new Lista();

        System.out.println("Agregando elementos a la lista:");
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);
        lista.agregar(40);
        lista.agregar(50);
        lista.agregar(60);
        lista.agregar(70);
        lista.agregar(80);
        lista.agregar(90);

        System.out.println("Mostrando Lista que es array");
        lista.mostrar();

        lista.agregarEnPosicion(0, 3);
        lista.mostrar();

        System.out.println("Obteniendo elemento en posición");
        lista.obtener(2);
        lista.eliminar(3);

        lista.mostrar();
    }
}
