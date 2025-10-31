import java.util.ArrayList;
import java.util.List;

public class ArraySimulado {
    private final List<Elemento> ELEMENTOS;
    private final int LIMITE;

    public ArraySimulado(int LIMITE) {
        this.LIMITE = LIMITE;
        this.ELEMENTOS = new ArrayList<>();
    }

    public void añadir(int valor) {
        if (ELEMENTOS.size() < LIMITE) {
            ELEMENTOS.add(new Elemento(ELEMENTOS.size(), valor));
        } else {
            System.out.println("Se ha alcanzado el límite del array.");
        }
    }

    public void modificar(int indice, int nuevoValor) {
        if (indice >= 0 && indice < ELEMENTOS.size()) {
            ELEMENTOS.get(indice).darValor(nuevoValor);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    public int obtenerElementos(int indice) {
        if (indice >= 0 && indice < ELEMENTOS.size()) {
            return ELEMENTOS.get(indice).valor();
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
    }

    public void imprimir() {
        for (Elemento elemento : ELEMENTOS) {
            System.out.println(elemento);
        }
    }

    public int tamaño() {
        return ELEMENTOS.size();
    }

    public int limite() {
        return LIMITE;
    }
}