public class ColaNinos {
    private Nino[] listaNinos;
    private int totalNinos;

    public ColaNinos(int capacidadInicial) {
        this.listaNinos = new Nino[capacidadInicial];
        this.totalNinos = 0;
    }

    public boolean estaVacia() {
        return totalNinos == 0;
    }

    public boolean estaLlena() {
        return totalNinos == listaNinos.length;
    }

    public void encolar(Nino nuevoNino) {
        if (estaLlena()) {
            Nino[] nuevaLista = new Nino[listaNinos.length * 2];
            System.arraycopy(listaNinos, 0, nuevaLista, 0, totalNinos);
            listaNinos = nuevaLista;
        }
        listaNinos[totalNinos++] = nuevoNino;
    }

    public Nino desencolar() {
        if (estaVacia()) return null;
        Nino primero = listaNinos[0];
        System.arraycopy(listaNinos, 1, listaNinos, 0, totalNinos - 1);
        totalNinos--;
        return primero;
    }

    public Nino[] obtenerNinos() {
        Nino[] copia = new Nino[totalNinos];
        System.arraycopy(listaNinos, 0, copia, 0, totalNinos);
        return copia;
    }

    public int cantidad() {
        return totalNinos;
    }

    public void vaciar() {
        totalNinos = 0;
    }
}
