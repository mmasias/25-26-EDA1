public class Array {

    private ListaEnlazada datos;
    private int capacidad;

    public Array(int capacidad) {
        assert capacidad > 0 : "Capacidad invalida";

        this.capacidad = capacidad;
        this.datos = new ListaEnlazada();

        for (int i = 0; i < capacidad; i++) {
            datos.agregar(0);
        }
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < capacidad : "Indice fuera de rango";
        return datos.obtener(indice);
    }

    public void modificar(int indice, int valor) {
        assert indice >= 0 && indice < capacidad : "Indice fuera de rango";
        datos.modificar(indice, valor);
    }

    public int capacidad() {
        return capacidad;
    }
}