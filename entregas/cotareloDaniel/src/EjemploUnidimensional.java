public class EjemploUnidimensional {

    public static void main(String[] args) {
        ListaConArray lista;

        lista = new ListaConArray(3);

        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);

        lista.imprimir();

        lista.remove(1);

        lista.imprimir();

        System.out.println("Elemento en posición 1: " + lista.get(1));
    }
}