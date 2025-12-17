public class EjemploUnidimensional {

    public static void main(String[] args) {
        EjemploUnidimensional ejemplo;
        ejemplo = new EjemploUnidimensional();
        ejemplo.ejecutar();
    }

    public void ejecutar() {
        ListaComoArray lista;

        lista = new ListaComoArray(5);

        lista.set(0, 10);
        lista.set(1, 20);
        lista.set(2, 30);
        lista.set(3, 40);
        lista.set(4, 50);

        lista.imprimir();

        System.out.println("Elemento en posición 2: " + lista.get(2));
    }
}
