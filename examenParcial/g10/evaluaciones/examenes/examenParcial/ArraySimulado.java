package evaluaciones.examenes.examenParcial;

public class ArraySimulado {
    private int length;
    private Nodo cabeza;

    public ArraySimulado(int length) {
        if (length < 0) length = 0;
        this.length = length;
        Nodo prev = null;
        for (int i = 0; i < length; i++) {
            Nodo n = new Nodo(0);
            if (i == 0) {
                cabeza = n;
            } else {
                prev.sig = n;
            }
            prev = n;
        }
    }

    public int length() {
        return length;
    }

    private Nodo nodoEn(int i) {
        if (i < 0 || i >= length) return null;
        Nodo cur = cabeza;
        int k = 0;
        while (k < i && cur != null) {
            cur = cur.sig;
            k++;
        }
        return cur;
    }

    public int get(int index) {
        Nodo n = nodoEn(index);
        if (n == null) return 0;
        return n.dato;
    }

    public void set(int index, int value) {
        Nodo n = nodoEn(index);
        if (n == null) return;
        n.dato = value;
    }
}