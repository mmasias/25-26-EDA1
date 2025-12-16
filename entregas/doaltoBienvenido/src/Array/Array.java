package Array;

public class Array {
    private ListaEnlazada estructura;
    private int capacidad;

    public Array(int capacidad) {
        assert capacidad > 0 : "La capacidad debe ser positiva";
        this.capacidad = capacidad;
        this.estructura = new ListaEnlazada();
        for (int contador = 0; contador < capacidad; contador++) {
            estructura.agregar(0);
        }
    }

    public int get(int indice) {
        assert (indice >= 0 && indice < capacidad) : "Acceso inválido al array";
        return estructura.obtener(indice);
    }

    public void set(int indice, int valor) {
        assert (indice >= 0 && indice < capacidad) : "Modificación inválida en el array";
        estructura.modificar(indice, valor);
    }

    public int longitud() { 
        return capacidad; 
    }
}