public class ColaNinos {
    private final Nino[] datos;
    private int indiceInicio;
    private int indiceFin;
    private int cantidad;

    public ColaNinos(int capacidad) {
        this.datos = new Nino[capacidad];
        this.indiceInicio = 0;
        this.indiceFin = 0;
        this.cantidad = 0;
    }

    public void encolar(Nino elemento) {
        this.datos[this.indiceFin] = elemento;
        this.indiceFin = this.indiceFin + 1;
        if (this.indiceFin == this.datos.length) {
            this.indiceFin = 0;
        }
        this.cantidad = this.cantidad + 1;
    }

    public Nino desencolar() {
        Nino elementoResultado;
        elementoResultado = this.datos[this.indiceInicio];
        this.datos[this.indiceInicio] = null;
        this.indiceInicio = this.indiceInicio + 1;
        if (this.indiceInicio == this.datos.length) {
            this.indiceInicio = 0;
        }
        this.cantidad = this.cantidad - 1;
        return elementoResultado;
    }

    public Nino obtener(int posicionRelativa) {
        int indiceAbsoluto;
        indiceAbsoluto = this.indiceInicio + posicionRelativa;
        if (indiceAbsoluto >= this.datos.length) {
            indiceAbsoluto = indiceAbsoluto - this.datos.length;
        }
        Nino elementoResultado;
        elementoResultado = this.datos[indiceAbsoluto];
        return elementoResultado;
    }

    public int tamano() {
        int tamanoResultado;
        tamanoResultado = this.cantidad;
        return tamanoResultado;
    }
}
