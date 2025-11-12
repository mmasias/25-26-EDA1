public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Lista lista = new Lista();
        lista.imprimirListaDetallada();
        lista.agregarFinal(new Nodo(10));
        lista.imprimirListaDetallada();
        lista.agregarFinal(new Nodo('c'));
        System.out.println("Posicion 2 con dato c\n");
        lista.agregarFinal(new Nodo("nodo aleatorio"));
        lista.agregarFinal(new Nodo(true));
        lista.agregarFinal(new Nodo(10.22));
        lista.agregarInicio(new Nodo('b'));
        
        lista.eliminar(3);
        lista.imprimirListaDetallada();
        System.out.println("Posicion 3 eliminado\n");
        lista.insertar(new Nodo(45), 3);

        lista.imprimirLista();
        System.out.println("Lista detallada despues de insertar en posicion 3\n");
        lista.imprimirListaDetallada();

        lista.imprimirLista();
        System.out.println("Lista detallada despues de insertar al inicio o al final\n");
        lista.imprimirListaDetallada();
        

        lista.imprimirLista();
        System.out.println("Lista detallada despues de eliminar\n");
        lista.imprimirListaDetallada();

        lista.actualizar("dato 3", 3);

        lista.imprimirLista();
        System.out.println("Lista detallada despues de actualizar en posicion 3\n");
        lista.imprimirListaDetallada();

        lista.imprimirLista();
        Nodo nodoPrubas = lista.obtener(4);
        System.out.println("dato: " + nodoPrubas.dato());
        System.out.println("refSiguiente: " + nodoPrubas.siguiente());
        System.out.println("Lista detallada despues de buscar nodo en posicion 4\n");
        lista.imprimirListaDetallada();


        lista.actualizar(50, 4);

        lista.imprimirLista();
        System.out.println("Lista detallada despues de actualizar dato en posicion 4\n");
        lista.imprimirListaDetallada();
    }
}
