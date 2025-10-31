public class Insertar {
    
    public Insertar() {
    }

    public void insertar(DataSet dataSet, int valor) {
        dataSet.add(valor);
        System.out.println("Insertado valor " + valor + " al final");
    }

    public void insertarEnPosicion(DataSet dataSet, int valor, int posicion) {
        dataSet.add(posicion, valor);
        System.out.println("Insertado valor " + valor + " en posición " + posicion);
    }
    
    public void insertarElementos(DataSet dataSet, int primerValor, int... valores) {
        System.out.println("\nInsertando elementos...");
        dataSet.add(primerValor);
        for (int valor : valores) {
            dataSet.add(valor);
        }
        System.out.println("Elementos insertados:");
        dataSet.display();
    }
    
    public void insertarEnPrincipio(DataSet dataSet, int valor) {
        System.out.println("\nInsertando " + valor + " al principio:");
        dataSet.add(0, valor);
        dataSet.display();
    }
    
      public void insertarAlFinal(DataSet dataSet, int valor) {
        System.out.println("\nInsertando " + valor + " al final:");
        dataSet.add(valor);
        dataSet.display();
    }
}