public class EjemploUnidimensional {
    public static void main(String[] args) {
        ListaComoArray lista = new ListaComoArray(5);
        lista.set(0, 10);
        lista.set(1, 20);
        lista.set(2, 30);
        lista.set(3, 40);
        lista.set(4, 50);
        lista.set(5, 60);
        lista.imprimir();
        System.out.println("Elemento en posición 2: " + lista.get(2));
    }
}