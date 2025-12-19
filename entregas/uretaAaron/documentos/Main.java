
public class Main {

    private static void mostrar(ListaUsandoArraySimulado lista) {
        System.out.print("[");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i));
            if (i < lista.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("=== Lista usando ArraySimulado ===");

        ListaUsandoArraySimulado lista = new ListaUsandoArraySimulado();

        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.add(50);
        mostrar(lista);

        lista.add(2, 25);
        mostrar(lista);

        lista.remove(4);
        mostrar(lista);

        lista.set(1, 200);
        mostrar(lista);

        for (int v = 60; v <= 200; v += 20) lista.add(v);
        mostrar(lista);

        System.out.println("=== Fin ===");
    }
}
