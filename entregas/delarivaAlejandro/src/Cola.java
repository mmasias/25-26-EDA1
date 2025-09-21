import java.util.NoSuchElementException;

public class Cola {
    private final int[] buffer;
    private int frente;
    private int fin;
    private int elementos;

    public Cola(int capacidad) {
        buffer = new int[capacidad];
        frente = 0;
        fin = 0;
        elementos = 0;
    }

    public boolean encolar(int valor) {
        if (estaLlena()) return false;
        buffer[fin] = valor;
        fin = (fin + 1) % buffer.length;
        elementos++;
        return true;
    }

    public int desencolar() {
        if (estaVacia()) throw new NoSuchElementException("La cola está vacía");
        int valor = buffer[frente];
        frente = (frente + 1) % buffer.length;
        elementos--;
        return valor;
    }

    public int getTamaño() { 
        return elementos; 
    }

    public boolean estaVacia() { 
        return elementos == 0; 
    }

    public boolean estaLlena() {
        return elementos == buffer.length;
    }
}
