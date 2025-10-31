package reto004;

class ListaDeArrayBidimensional {
    private int[][] datos;
    private int[] usados;
    private int filas;

    public ListaDeArrayBidimensional(int filasIniciales, int colsIniciales) {
        if (filasIniciales < 1) filasIniciales = 1;
        if (colsIniciales < 1) colsIniciales = 1;
        filas = filasIniciales;
        datos = new int[filas][colsIniciales];
        usados = new int[filas];
    }

    public int filas() { return filas; }
    public int sizeFila(int f) { return usados[f]; }
    public int get(int f, int c) { return datos[f][c]; }
    public void set(int f, int c, int v) { datos[f][c] = v; }

    public void add(int f, int v) {
        ensureCapFila(f, usados[f] + 1);
        datos[f][usados[f]++] = v;
    }

    public void insert(int f, int c, int v) {
        ensureCapFila(f, usados[f] + 1);
        for (int k = usados[f]; k > c; k--) datos[f][k] = datos[f][k - 1];
        datos[f][c] = v;
        usados[f]++;
    }

    public int removeAt(int f, int c) {
        int val = datos[f][c];
        for (int k = c; k < usados[f] - 1; k++) datos[f][k] = datos[f][k + 1];
        usados[f]--;
        return val;
    }

    public void addFila(int colsIniciales) {
        if (colsIniciales < 1) colsIniciales = 1;
        int[][] nd = new int[filas + 1][];
        int[] nu = new int[filas + 1];
        for (int i = 0; i < filas; i++) { nd[i] = datos[i]; nu[i] = usados[i]; }
        nd[filas] = new int[colsIniciales];
        datos = nd; usados = nu; filas++;
    }

    public void removeFila(int f) {
        int[][] nd = new int[filas - 1][];
        int[] nu = new int[filas - 1];
        for (int i = 0, j = 0; i < filas; i++) if (i != f) { nd[j] = datos[i]; nu[j] = usados[i]; j++; }
        datos = nd; usados = nu; filas--;
    }

    private void ensureCapFila(int f, int minCap) {
        if (minCap <= datos[f].length) return;
        int nueva = Math.max(datos[f].length * 2, minCap);
        int[] nuevo = new int[nueva];
        for (int i = 0; i < usados[f]; i++) nuevo[i] = datos[f][i];
        datos[f] = nuevo;
    }

    public static void main(String[] args) {
        ListaDeArrayBidimensional tabla = new ListaDeArrayBidimensional(2, 3);

        tabla.add(0, 10); tabla.add(0, 20); tabla.add(0, 30);
        tabla.add(1, 40); tabla.add(1, 50); tabla.add(1, 60);

        tabla.insert(0, 1, 99);
        tabla.removeAt(1, 0);
        tabla.addFila(2);
        tabla.add(2, 77); tabla.add(2, 88);

        for (int f = 0; f < tabla.filas(); f++) {
            for (int c = 0; c < tabla.sizeFila(f); c++) System.out.print(tabla.get(f, c) + " ");
            System.out.println();
        }
    }
}


