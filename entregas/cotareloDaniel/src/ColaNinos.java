public class ColaNinos {
    private static final int CAPACIDAD_MINIMA = 1;
    private Nino[] elementos;
    private int cantidad;

    public ColaNinos(int capacidad) {
        int capacidadAjustada;
        capacidadAjustada = capacidad;
        if (capacidadAjustada < CAPACIDAD_MINIMA) {
            capacidadAjustada = CAPACIDAD_MINIMA;
        }
        this.elementos = new Nino[capacidadAjustada];
        this.cantidad = 0;
    }

    public int tamano() {
        int valor;
        valor = this.cantidad;
        return valor;
    }

    public boolean estaVacia() {
        boolean vacia;
        vacia = this.cantidad == 0;
        return vacia;
    }

    public boolean estaLlena() {
        boolean llena;
        llena = this.cantidad == this.elementos.length;
        return llena;
    }

    public void encolar(Nino nino) {
        if (!estaLlena()) {
            this.elementos[this.cantidad] = nino;
            this.cantidad = this.cantidad + 1;
        }
    }

    public Nino desencolar() {
        Nino resultado;
        resultado = null;
        if (!estaVacia()) {
            resultado = this.elementos[0];
            desplazarIzquierda();
            this.cantidad = this.cantidad - 1;
        }
        return resultado;
    }

    private void desplazarIzquierda() {
        int indice;
        indice = 0;
        while (indice < this.cantidad - 1) {
            this.elementos[indice] = this.elementos[indice + 1];
            indice = indice + 1;
        }
        if (this.cantidad > 0) {
            this.elementos[this.cantidad - 1] = null;
        }
    }

    public Nino obtener(int indice) {
        Nino valor;
        valor = this.elementos[indice];
        return valor;
    }

    public void limpiar() {
        int indice;
        indice = 0;
        while (indice < this.cantidad) {
            this.elementos[indice] = null;
            indice = indice + 1;
        }
        this.cantidad = 0;
    }

    public Nino[] copiarContenido() {
        Nino[] copia;
        int indice;
        copia = new Nino[this.cantidad];
        indice = 0;
        while (indice < this.cantidad) {
            copia[indice] = this.elementos[indice];
            indice = indice + 1;
        }
        return copia;
    }

    public void encolarDesdeArray(Nino[] lista) {
        int indice;
        indice = 0;
        while (indice < lista.length && !estaLlena()) {
            encolar(lista[indice]);
            indice = indice + 1;
        }
    }
}
