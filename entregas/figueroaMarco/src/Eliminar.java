class Eliminar {
    
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
