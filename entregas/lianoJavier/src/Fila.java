public class Fila {

    private ColaNinho cola = new ColaNinho();
    private int size = 0;

    public void agregar(Ninho ninho) {
        cola.enqueue(ninho);
        size++;
    }

    public boolean estaCompleto() {
        return size > 5;
    }

    public Ninho[] toArray() {
        Ninho[] array = new Ninho[size];
        ColaNinho temp = new ColaNinho();
        int i = 0;
        while (!cola.estaVacia()) {
            Ninho n = cola.dequeue();
            array[i++] = n;
            temp.enqueue(n);
        }
        while (!temp.estaVacia()) {
            cola.enqueue(temp.dequeue());
        }
        return array;
    }
}
