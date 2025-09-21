public class ColaCircular {

    private final Nino[] datos;
    private final int capacidad;
    private int frente = 0;
    private int fin = 0;    
    private int tamaño = 0; 

    public ColaCircular(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad debe ser > 0");
        this.capacidad = capacidad;
        this.datos = new Nino[capacidad];
    }

    public void encolar(Nino nino) {
        if (estaLlena()) throw new IllegalStateException("Cola llena");
        datos[fin] = nino;
        fin = (fin + 1) % capacidad;
        tamaño++;
    }

    public Nino desencolar() {
        if (estaVacia()) return null;
        Nino res = datos[frente];
        datos[frente] = null; 
        frente = (frente + 1) % capacidad;
        tamaño--;
        return res;
    }

    public Nino mirarFrente() {
        if (estaVacia()) return null;
        return datos[frente];
    }

    public boolean estaVacia() { return tamaño == 0; }

    public boolean estaLlena() { return tamaño == capacidad; }

    public int tamaño() { return tamaño; }

    // Alternativa básica a toArraySnapshot
    public Nino[] copiarCola() {
        Nino[] arreglo = new Nino[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = datos[(frente + i) % capacidad];
        }
        return arreglo;
    }

    
    public void vaciar() {
        while (!estaVacia()) desencolar();
    }

   
    public void imprimirEstado() {
        int actual = frente;
        for (int i = 0; i < tamaño; i++) {
            System.out.print("_o_ ");
            actual = (actual + 1) % capacidad;
        }
        System.out.println();
    }
}
