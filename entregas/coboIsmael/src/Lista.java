public class Lista {

    private Array almacenamiento;
    private int tamanio;

    public Lista() {
        almacenamiento = new Array(10);
        tamanio = 0;
    }

    public void agregar(int valor) {
        if (tamanio == almacenamiento.capacidad()) {
            expandir();
        }
        almacenamiento.modificar(tamanio, valor);
        tamanio++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamanio : "Indice fuera de rango";
        return almacenamiento.obtener(indice);
    }

    public void modificar(int indice, int valor) {
        assert indice >= 0 && indice < tamanio : "Indice fuera de rango";
        almacenamiento.modificar(indice, valor);
    }

    public int tamanio() {
        return tamanio;
    }

    private void expandir() {
        int nuevaCapacidad = almacenamiento.capacidad() * 2;
        Array nuevo = new Array(nuevaCapacidad);

        for (int i = 0; i < tamanio; i++) {
            nuevo.modificar(i, almacenamiento.obtener(i));
        }

        almacenamiento = nuevo;
    }
}