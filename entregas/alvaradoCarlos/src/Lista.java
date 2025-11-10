public class Lista {

    private Elemento cabeza;
    private int tamaño;

    public Lista(){
        this.cabeza = null;
        tamaño = 0;
    }

    public void agregar(int valor) {
        Elemento nuevoElemento = new Elemento(valor);

        if (cabeza == null){
            this.cabeza = nuevoElemento;
        } else {
            Elemento actual = this.cabeza; 
            while (actual.siguiente() != null) { 
                actual = actual.siguiente(); 
            }
            actual.setSiguiente(nuevoElemento);
        }
        tamaño++;
    }

    public int obtener(int posicion) {
        Elemento actual = cabeza; 

        for (int i = 0; i < posicion; i++){ 
            actual = actual.siguiente();
        }

        return actual.valor();
    }

    public void modificar(int posicion, int nuevoValor){
        Elemento actual = cabeza; 

        for (int i = 0; i < posicion; i++){ 
            actual = actual.siguiente(); 
        }

        actual.darValor(nuevoValor); 
    }

    public int tamaño(){
        return tamaño;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void mostrarLista() {
    if (cabeza == null) {
        System.out.println("La lista está vacía.");
        return;
    }

    Elemento actual = cabeza;
    System.out.print("Cabeza → ");
    while (actual != null) {
        System.out.print("[" + actual.valor() + "]");
        if (actual.siguiente() != null) {
            System.out.print(" → ");
        }
        actual = actual.siguiente();
    }
    System.out.println(" → null (Fin de la lista)");
}

    
}