package uroboros.lista;

public class ClienteListaUroboro {
    public static void main(String[] args) {
        System.out.println("UROBOROS: Lista usando Array (Array internamente usa Lista enlazada)");
        System.out.println();

        Lista lista = new Lista();

        System.out.println("AGREGANDO ELEMENTOS");
        lista.agregar(100);
        lista.agregar(200);
        lista.agregar(300);
        lista.agregar(400);
        lista.agregar(500);

        System.out.println("> Tamaño actual: " + lista.tamaño());

        System.out.println();
        System.out.println("MOSTRANDO ELEMENTOS");
        lista.mostrarEstructura();

        System.out.println();
        System.out.println("ELIMINANDO POSICIÓN 2 (valor 300)");
        lista.eliminar(2);

        System.out.println("> Nuevo tamaño: " + lista.tamaño());

        System.out.println();
        System.out.println("ELEMENTOS DESPUÉS DE ELIMINAR");
        lista.mostrarEstructura();

        System.out.println();
        System.out.println("AGREGANDO MÁS ELEMENTOS");
        lista.agregar(600);
        lista.agregar(700);

        System.out.println("> Tamaño final: " + lista.tamaño());

        System.out.println();
        System.out.println("ESTADO FINAL");
        lista.mostrarEstructura();

        System.out.println();
        System.out.println("La serpiente se muerde la cola:");
        System.out.println("Lista → usa Array → usa Lista (enlazada con nodos)");
    }
}
