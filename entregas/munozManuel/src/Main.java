public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Lista lista = new Lista();
        lista.agregarFinal(new Nodo(10));
        lista.agregarFinal(new Nodo('c'));
        lista.agregarFinal(new Nodo("nodo aleatorio"));
        lista.agregarFinal(new Nodo(true));
        lista.agregarFinal(new Nodo(10.22));

        lista.imprimirLista();
        System.out.println("Lista detallada\n");
        lista.imprimirListaDetallada();
        lista.actualizar(50, 1);

        System.out.println("\nSegunda iteracion antes de insercion\n");
        lista.agregarInicio(new Nodo('c'));
        System.out.println("nuevo dato c al inicio");
        lista.imprimirLista();
        lista.insertar(new Nodo(51), 5);
        System.out.println("nuevo dato 51 en pos 5");
        lista.imprimirLista();
        System.out.println("\nLista detallada\n");
        lista.imprimirListaDetallada();
        System.out.println(lista.leerNodo(5).dato());

        System.out.println("\nSegunda iteracion despues de insercion\n");
        lista.insertar(new Nodo("nevo dato en tercera posicion"), 3);
        lista.eliminar(1);
        System.out.println("Lista detallada\n");
        lista.imprimirListaDetallada();
        lista.imprimirLista();
        String valorDeNodo = lista.leerNodo(2).dato();
        System.out.println(valorDeNodo);
    }
}
