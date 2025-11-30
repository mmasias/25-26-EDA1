import java.util.ArrayList;

public class colaPrioridadPedidos {

    private ArrayList<Pedido> lista = new ArrayList<>();
    private long comparaciones = 0;

    public long getComparaciones() {
        return comparaciones;
    }

    public int size() {
        return lista.size();
    }

    public void insert(Pedido p) {
        lista.add(p);
        siftUp(lista.size() - 1);
    }

    public Pedido extractMin() {
        if (lista.isEmpty()) return null;

        Pedido min = lista.get(0);
        Pedido ultimo = lista.remove(lista.size() - 1);

        if (!lista.isEmpty()) {
            lista.set(0, ultimo);
            siftDown(0);
        }

        return min;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            comparaciones++;
            if (lista.get(i).getTiempoRestante() >= lista.get(padre).getTiempoRestante())
                break;

            swap(i, padre);
            i = padre;
        }
    }

    private void siftDown(int i) {
        int size = lista.size();

        while (true) {
            int izq = 2 * i + 1;
            int der = 2 * i + 2;
            int menor = i;

            if (izq < size) {
                comparaciones++;
                if (lista.get(izq).getTiempoRestante() < lista.get(menor).getTiempoRestante())
                    menor = izq;
            }

            if (der < size) {
                comparaciones++;
                if (lista.get(der).getTiempoRestante() < lista.get(menor).getTiempoRestante())
                    menor = der;
            }

            if (menor == i) break;

            swap(i, menor);
            i = menor;
        }
    }

    private void swap(int a, int b) {
        Pedido temp = lista.get(a);
        lista.set(a, lista.get(b));
        lista.set(b, temp);
    }
}
