public class ColaNiños {
    private Niño[] cola;
    private int tamaño;

    public ColaNiños() {
        cola = new Niño[100];
        tamaño = 0;
    }

    public void encolar(Niño niño) {
        cola[tamaño] = niño;
        tamaño++;
    }

    public Niño desencolar() {
        Niño primero = cola[0];
        for (int i = 0; i < tamaño - 1; i++) {
            cola[i] = cola[i + 1];
        }
        tamaño--;
        return primero;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Niño getNiño(int i) {
        return cola[i];
    }
}
