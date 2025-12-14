public class EjemploMatriz {
    public static void main(String[] args) {
        Matriz matriz = new Matriz(3, 4);

        System.out.println("Matriz inicial:");
        matriz.imprimir();

        matriz.setValor(0, 0, 10);
        matriz.setValor(0, 1, 11);
        matriz.setValor(1, 2, 22);
        matriz.setValor(2, 3, 33);

        System.out.println("\nDespués de setValor:");
        matriz.imprimir();

        System.out.println("\ngetValor(0,1): " + matriz.getValor(0, 1));
        System.out.println("getValor(1,2): " + matriz.getValor(1, 2));
        System.out.println("getValor(2,3): " + matriz.getValor(2, 3));

        System.out.println("\nAccesos fuera de rango:");
        matriz.setValor(-1, 0, 99);
        matriz.getValor(3, 0);

        System.out.println("\nCopia de fila 1:");
        Fila filaCopia = matriz.getFila(1);
        filaCopia.imprimir();

        System.out.println("\nModificación de la copia:");
        filaCopia.setValor(2, 999);
        filaCopia.imprimir();

        System.out.println("\nMatriz original tras modificar la copia:");
        matriz.imprimir();
    }
}
