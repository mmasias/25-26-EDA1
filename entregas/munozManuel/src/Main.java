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
        System.out.println("Segunda iteracion");
        lista.agregarInicio(new Nodo('c'));
        lista.imprimirLista();
        System.out.println("Lista detallada\n");
        lista.imprimirListaDetallada();
    }
}
