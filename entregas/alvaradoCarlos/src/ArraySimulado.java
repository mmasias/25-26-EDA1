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

        boolean añadido = false;
        for (int i = 0; i < LIMITE; i++) {
            if (i == ELEMENTOS.size() && !añadido) {
                ELEMENTOS.add(new Elemento(i, valor));
                añadido = true;
            }
        }
    }

    public void modificar(int indice, int nuevoValor) {
        ELEMENTOS.get(indice).darValor(nuevoValor);
    }

    public int obtenerElementos(int indice) {
        return ELEMENTOS.get(indice).valor();
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