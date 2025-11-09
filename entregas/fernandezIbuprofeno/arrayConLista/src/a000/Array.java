package a000;

public class Array {
    private Lista miLista;
    private int capacidad;
    
    public Array(int capacidad) {
        assert capacidad > 0 : "Precondición violada: capacidad debe ser positiva";
        
        this.capacidad = capacidad;
        this.miLista = new Lista();
        
        for (int i = 0; i < capacidad; i++) {
            miLista.agregar(0);
        }
    }
    
    public int get(int indice) {
        assert indice >= 0 && indice < capacidad : "Precondición violada: índice fuera de rango";
        return miLista.obtener(indice);
    }
    
    public void set(int indice, int valor) {
        assert indice >= 0 && indice < capacidad : "Precondición violada: índice fuera de rango";
        miLista.modificar(indice, valor);
    }
    
    public int longitud() {
        return capacidad;
    }
}
