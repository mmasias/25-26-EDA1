package evaluaciones.examenes.examenParcial;

public class Main {
    private static void imprimir(ListaUsandoArraySimulado lista) {
        System.out.print("[");
        int i = 0;
        while (i < lista.size()) {
            System.out.print(lista.get(i));
            if (i < lista.size() - 1)
                System.out.print(", ");
            i++;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("=== Lista sobre ArraySimulado (PRG2) ===");

        ListaUsandoArraySimulado lista = new ListaUsandoArraySimulado();

        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.add(50);
        System.out.println("Inicial:");
        imprimir(lista);

        lista.add(2, 25);
        System.out.println("Tras insertar 25 en pos 2:");
        imprimir(lista);

        lista.remove(4);
        System.out.println("Tras eliminar en pos 4:");
        imprimir(lista);

        lista.set(1, 200);
        System.out.println("Tras set(1, 200):");
        imprimir(lista);

        int v = 60;
        while (v <= 200) {
            lista.add(v);
            v += 20;
        }
        System.out.println("Tras crecer automáticamente:");
        imprimir(lista);

        System.out.println("=== Fin ===");
    }

}