public class ColaPrioridadHeap {

    private static final int CAPACIDAD_MAXIMA = 150;

    private Pedido[] heap;
    private int tamaño;

    public ColaPrioridadHeap() {
        this.heap = new Pedido[CAPACIDAD_MAXIMA];
        this.tamaño = 0;
    }

    private int getIndicePadre(int i) { return (i - 1) / 2; }
    private int getIndiceHijoIzq(int i) { return 2 * i + 1; }
    private int getIndiceHijoDer(int i) { return 2 * i + 2; }

    private boolean tienePadre(int i) { return getIndicePadre(i) >= 0; }
    private boolean tieneHijoIzq(int i) { return getIndiceHijoIzq(i) < tamaño; }
    private boolean tieneHijoDer(int i) { return getIndiceHijoDer(i) < tamaño; }

    private void intercambiar(int i, int j) {
        Pedido temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    public int getTamaño() {
        return this.tamaño;
    }

    public boolean estaVacia() {
        return this.tamaño == 0;
    }

    public void agregar(Pedido pedidoAAgregar, Estadisticas stats) {
        if (tamaño == CAPACIDAD_MAXIMA) {
            System.out.println("ERROR: El Heap está lleno. Aumentar CAPACIDAD_MAXIMA.");
            return;
        }
        
        heap[tamaño] = pedidoAAgregar;
        tamaño++;
        
        flotar(tamaño - 1, stats);
    }

    public Pedido extraerMinimo(Estadisticas stats) {
        if (estaVacia()) {
            return null;
        }

        Pedido minimo = heap[0];

        heap[0] = heap[tamaño - 1];
        heap[tamaño - 1] = null;
        tamaño--;

        hundir(0, stats);

        return minimo;
    }

    private void flotar(int indice, Estadisticas stats) {
        int indiceActual = indice;
        
        while (tienePadre(indiceActual)) {
            int indicePadre = getIndicePadre(indiceActual);
            
            stats.registrarComparacion();
            if (heap[indiceActual].getTiempoPreparacion() < heap[indicePadre].getTiempoPreparacion()) {
                intercambiar(indiceActual, indicePadre);
                indiceActual = indicePadre;
            } else {
              
                return;
            }
        }
    }

    private void hundir(int indice, Estadisticas stats) {
        int indiceActual = indice;
        
        while (tieneHijoIzq(indiceActual)) {
            int indiceHijoMenor = getIndiceHijoIzq(indiceActual);
            
        
            if (tieneHijoDer(indiceActual)) {
                stats.registrarComparacion();
                if (heap[getIndiceHijoDer(indiceActual)].getTiempoPreparacion() < heap[indiceHijoMenor].getTiempoPreparacion()) {
                    indiceHijoMenor = getIndiceHijoDer(indiceActual);
                }
            }

           
            stats.registrarComparacion();
            if (heap[indiceActual].getTiempoPreparacion() > heap[indiceHijoMenor].getTiempoPreparacion()) {
                intercambiar(indiceActual, indiceHijoMenor);
                indiceActual = indiceHijoMenor;
            } else {
               
                return;
            }
        }
    }
}
