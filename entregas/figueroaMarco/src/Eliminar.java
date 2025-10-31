public class Eliminar {
    
    public int eliminarPorIndice(DataSet dataSet, int indice) {
        if (dataSet.checkIsEmpty()) {
            System.out.println("Error: DataSet vacío");
            return 0;
        }
        
        if (indice < 0 || indice >= dataSet.getSize()) {
            System.out.println("Error: Índice fuera de rango: " + indice);
            return 0;
        }
        
        int valorEliminado = dataSet.getValue(indice);
        
        dataSet.removeAndReturn(indice);
        
        return valorEliminado;
    }
    
    public int eliminarInicio(DataSet dataSet) {
        if (dataSet.checkIsEmpty()) {
            System.out.println("Error: DataSet vacío");
            return 0;
        }
        
        return eliminarPorIndice(dataSet, 0);
    }
    
    public int eliminarFinal(DataSet dataSet) {
        if (dataSet.checkIsEmpty()) {
            System.out.println("Error: DataSet vacío");
            return 0;
        }
        
        int ultimoIndice = dataSet.getSize() - 1;
        return eliminarPorIndice(dataSet, ultimoIndice);
    }

    public int eliminarPorValor(DataSet dataSet, int valor) {
        int eliminados = 0;
        int i = 0;
        
        while (i < dataSet.getSize()) {
            if (dataSet.getValue(i) == valor) {
                eliminarPorIndice(dataSet, i);
                eliminados++;
            } else {
                i++;
            }
        }
        
        if (eliminados > 0) {
            System.out.println("Se eliminaron " + eliminados + " ocurrencia(s) del valor " + valor);
        } else {
            System.out.println("No se encontró el valor " + valor + " en el DataSet");
        }
        
        return eliminados;
    }
    
    public static void eliminarInicio(ArrayConstructor constructor) {
        if (constructor.getCabeza() == -1) {
            System.out.println("Error: Lista vacía");
            return;
        }
        
        int indiceEliminar = constructor.getCabeza();
        
        constructor.setCabeza(constructor.getReferencias()[indiceEliminar]);
        
        constructor.getArray()[indiceEliminar] = null;
        
        constructor.getReferencias()[indiceEliminar] = constructor.getPrimerLibre();
        constructor.setPrimerLibre(indiceEliminar);
        
        constructor.decrementarTamaño();
    }
    
    public static void eliminarFinal(ArrayConstructor constructor) {
        if (constructor.getCabeza() == -1) {
            System.out.println("Error: Lista vacía");
            return;
        }
        
        if (constructor.getReferencias()[constructor.getCabeza()] == -1) {
            eliminarInicio(constructor);
            return;
        }
        
        int anterior = constructor.getCabeza();
        int actual = constructor.getReferencias()[anterior];
        
        while (constructor.getReferencias()[actual] != -1) {
            anterior = actual;
            actual = constructor.getReferencias()[actual];
        }
        
        constructor.getReferencias()[anterior] = -1;
        constructor.getArray()[actual] = null;
        
        constructor.getReferencias()[actual] = constructor.getPrimerLibre();
        constructor.setPrimerLibre(actual);
        
        constructor.decrementarTamaño();
    }
}
