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
        lista.imprimirLista();
        lista.insertar(new Nodo(51), 5);
        lista.imprimirLista();
        System.out.println("\nLista detallada\n");
        lista.imprimirListaDetallada();
        System.out.println(lista.leerDato(5));

        System.out.println("\nSegunda iteracion despues de insercion\n");
        lista.insertar(new Nodo("nevo dato en tercera posicion"), 3);
        lista.imprimirLista();
        System.out.println("Lista detallada\n");
        lista.imprimirListaDetallada();
    }
}
