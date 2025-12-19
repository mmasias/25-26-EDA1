
public class ListaUsandoArraySimulado {

    private ArraySimulado arreglo;
    private int elementos;

    public ListaUsandoArraySimulado() {
        arreglo = new ArraySimulado(10);
        elementos = 0;
    }

    public int size() {
        return elementos;
    }

    public boolean isEmpty() {
        return elementos == 0;
    }

    public int get(int pos) {
        if (pos < 0 || pos >= elementos) return 0;
        return arreglo.get(pos);
    }

    public void set(int pos, int valor) {
        if (pos < 0 || pos >= elementos) return;
        arreglo.set(pos, valor);
    }

    public void add(int valor) {
        asegurar(elementos + 1);
        arreglo.set(elementos, valor);
        elementos++;
    }

    public void add(int pos, int valor) {
        if (pos < 0 || pos > elementos) return;
        asegurar(elementos + 1);
        for (int i = elementos - 1; i >= pos; i--) {
            arreglo.set(i + 1, arreglo.get(i));
        }
        arreglo.set(pos, valor);
        elementos++;
    }

    public int remove(int pos) {
        if (pos < 0 || pos >= elementos) return 0;
        int eliminado = arreglo.get(pos);
        for (int i = pos; i < elementos - 1; i++) {
            arreglo.set(i, arreglo.get(i + 1));
        }
        elementos--;
        arreglo.set(elementos, 0);
        return eliminado;
    }

    private void asegurar(int minimo) {
        int cap = arreglo.length();
        if (minimo <= cap) return;
        int nuevaCap = cap * 2;
        if (nuevaCap < minimo) nuevaCap = minimo;
        ArraySimulado nuevo = new ArraySimulado(nuevaCap);
        for (int i = 0; i < elementos; i++) nuevo.set(i, arreglo.get(i));
        arreglo = nuevo;
    }
}
