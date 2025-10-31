public class Cliente {
    public static void main(String[] args) {
        ArrayConstructor constructor = new ArrayConstructor();
        Insertar insertar = new Insertar();
        Indexar indexar = new Indexar();
        Eliminar eliminar = new Eliminar();
        
        DataSet dataSet = constructor.crearArray(20);
        
        insertar.insertarElementos(dataSet, 5, 0, 10, 15, 20, 30, 40);
        
        insertar.insertarEnPrincipio(dataSet, 3);
        
        insertar.insertarAlFinal(dataSet, 10);
        
        int valorEnIndice4 = indexar.buscarPorIndice(dataSet, 4);
        System.out.println("\nValor en índice 4: " + valorEnIndice4);
        
        int valorEliminado = eliminar.eliminarPorIndice(dataSet, 3);
        System.out.println("\nDespués de eliminar índice 3 (valor " + valorEliminado + "):");
        dataSet.display();
        
    }
}
