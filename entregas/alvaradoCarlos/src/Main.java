public class Main {
    public static void main(String[] args) {
        ArraySimulado array = new ArraySimulado(3);

        System.out.println("Agregando elementos...");
        array.añadir(10);
        array.añadir(20);
        array.añadir(30);
        array.añadir(45);
        array.imprimir();
        System.out.println("Modificando índice 1...");
        array.modificar(1, 5);
        array.imprimir();
    }
}
