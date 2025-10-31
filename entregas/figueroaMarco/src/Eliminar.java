public class Eliminar {
    
    public static void eliminarInicio(ArrayConstructor constructor) {
        if (constructor.getCabeza() == -1){
            System.out.println("Error: lista vacía");
            return;
        }
        
        int indiceEliminar = constructor.getCabeza();
        constructor.setCabeza(constructor.getReferencias()[indiceEliminar]);
        constructor.getArray()[indiceEliminar] = null;
        constructor.getReferencias()[indiceEliminar] = constructor.getPrimerLibre();
        constructor.setPrimerLibre(indiceEliminar);
        constructor.decrementarTamaño();

    }

}
