public class Indexar {
    
    public int buscarPorIndice(DataSet dataSet, int index) {
        System.out.println("\nBuscando elemento en índice " + index + ":");
        int valor = dataSet.getValue(index);
        System.out.println("Valor encontrado: " + valor);
        return valor;
    }
    
    public int obtenerIndicePrevio(DataSet dataSet, int index) {
        System.out.println("\nObteniendo índice previo a " + index + ":");
        int indicePrevio = dataSet.getBeforeIndex(index);
        if (indicePrevio != -1) {
            System.out.println("Índice previo: " + indicePrevio);
        }
        return indicePrevio;
    }
    
    public int obtenerIndiceSiguiente(DataSet dataSet, int index) {
        System.out.println("\nObteniendo índice siguiente a " + index + ":");
        int indiceSiguiente = dataSet.getAfterIndex(index);
        if (indiceSiguiente != -1) {
            System.out.println("Índice siguiente: " + indiceSiguiente);
        }
        return indiceSiguiente;
    }
    
    public boolean esIndiceValido(DataSet dataSet, int index) {
        int size = obtenerTamaño(dataSet);
        return index >= 0 && index < size;
    }
    
    public int obtenerTamaño(DataSet dataSet) {
        return dataSet.getSize();
    }
    
    public void mostrarInformacionIndexacion(DataSet dataSet) {
        System.out.println("\n=== Información de Indexación ===");
        System.out.print("Tamaño: ");
        dataSet.size();
        System.out.print("¿Está vacío?: ");
        dataSet.isEmpty();
        System.out.print("Contenido: ");
        dataSet.display();
    }
}
