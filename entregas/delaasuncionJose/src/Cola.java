public class Cola {

    private Nino[] elementos;
    private int frente;
    private int fin;
    private int size;
    private int capacidad;

    public Cola() {
        this.capacidad = 5;
        this.elementos = new Nino[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.size = 0;
    }

    public void encolar(Nino n) {
        if (size == capacidad) {
            this.alargar(5);
        }
        fin = (fin + 1) % capacidad;
        elementos[fin] = n;
        size++;
    }

private void alargar(int numero) {
    capacidad += numero;
    Nino[] nuevaLista = new Nino[capacidad];

    for (int i = 0; i < size; i++) {
        int index = (frente + i) % capacidad;
        nuevaLista[i] = elementos[index];
    }

    elementos = nuevaLista;
    frente = 0;
    fin = size - 1;
}


    public Nino desencolar() {
        if (estaVacia()) {
            return null;
        }
        Nino n = elementos[frente];
        frente = (frente + 1) % capacidad;
        size--;
        return n;
    }

    public Nino frente() {
        if (estaVacia()) {
            return null;
        }
        return elementos[frente];
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public int tamano() {
        return size;
    }

    public void recorrer(Accion accion) {
        for (int i = 0; i < size; i++) {
            int index = (frente + i) % capacidad;
            accion.aplicar(elementos[index]);
        }
    }

    public interface Accion {
        void aplicar(Nino n);
    }
}