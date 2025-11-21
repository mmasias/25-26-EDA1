public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Lista lista = new Lista();
        lista.agregarFinal(10);
        lista.agregarFinal('c');
        lista.agregarFinal("nodo aleatorio");
        lista.agregarFinal(true);
        lista.agregarFinal(10.22);
        lista.agregarInicio('b');

        lista.imprimirLista();
        System.out.println("Lista detallada despues de agregar datos\n");
        lista.imprimirArrayDetallado();
        
        lista.eliminar(3);
        lista.imprimirLista();
        System.out.println("Posicion 3 eliminado\n");
        lista.imprimirArrayDetallado();
        
        lista.insertar(45, 3);
        lista.imprimirLista();
        System.out.println("Lista detallada despues de insertar en posicion 3\n");
        lista.imprimirArrayDetallado();

        lista.actualizar("dato 3", 3);
        lista.imprimirLista();
        System.out.println("Lista detallada despues de actualizar en posicion 3\n");
        lista.imprimirArrayDetallado();

        lista.imprimirLista();
        Nodo nodoPrubas = lista.obtener(4);
        System.out.println("dato: " + nodoPrubas.dato());
        System.out.println("refSiguiente: " + nodoPrubas.siguiente());
        System.out.println("Lista detallada despues de buscar nodo en posicion 4\n");
        lista.imprimirArrayDetallado();

        lista.actualizar(50, 4);
        lista.imprimirLista();
        System.out.println("Lista detallada despues de actualizar dato en posicion 4\n");
        lista.imprimirArrayDetallado();

        lista.limpiarLista();
        lista.imprimirLista();
        System.out.println("Lista detallada despues de limpiar lista\n");
        lista.imprimirArrayDetallado();

        lista.agregarInicio("nuevo inicio");
        lista.imprimirLista();
        System.out.println("Lista detallada despues de agregar nuevo inicio\n");
        lista.imprimirArrayDetallado();

        lista.agregarFinal("nodo en posicion 2");
        lista.imprimirLista();
        System.out.println("Lista detallada despues de agregar nodo en posicion final\n");
        lista.imprimirArrayDetallado();
    }
}
