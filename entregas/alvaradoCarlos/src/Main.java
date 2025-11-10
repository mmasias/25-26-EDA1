public class Main {
    public static void main(String[] args) {

        ArraySimulado miArray = new ArraySimulado(5);
        System.out.println("Array: " + miArray);

        miArray.set(0, 1);
        miArray.set(1, 2);
        miArray.set(2, 1);
        miArray.set(3, 2);

        System.out.println("Array: " + miArray);

        System.out.println(miArray.get(0));
        System.out.println(miArray.get(1));
    }
}
