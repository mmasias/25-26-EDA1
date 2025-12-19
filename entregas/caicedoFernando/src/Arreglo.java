public class Arreglo {
    private final Lista miLista;
    private final int capacidad;

    public Arreglo(int capacidad) {
        assert capacidad > 0 : "Error: La capacidad debe ser positiva";

        this.capacidad = capacidad;
        this.miLista = new Lista();
        
        int i = 0;
        boolean llenando = true;
        while (llenando) {
            if (i < capacidad) {
                miLista.agregar(0);
                i++;
            } else {
                llenando = false;
            }
        }
    }

    public Object get(int indice) {
        assert indice >= 0 && indice < capacidad : "Error: Índice fuera de límites";
        return miLista.obtener(indice);
    }

    public void set(int indice, Object valor) {
        assert indice >= 0 && indice < capacidad : "Error: Índice fuera de límites";
        miLista.modificar(indice, valor);
    }

    public int longitud() {
        return capacidad;
    }
}