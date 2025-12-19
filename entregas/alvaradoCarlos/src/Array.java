public class Array {
    private ListaSimulada miLista;
    private int capacidad;

    public Array(int capacidad){
        this.capacidad = capacidad;
        this.miLista = new ListaSimulada();

        for (int i = 0; i < capacidad; i++){
            miLista.agregar(0);
        }
    }

    public int get(int indice){
        return miLista.obtener(indice);
    }

    public void set(int indice, int valor){
        miLista.modificar(indice, valor);
    }

    public int longitud(){
        return capacidad;
    }

    public String toString() {
        return miLista.toString();
    }
}