public class ColaNinos {
    private final Nino[] cantidadDeNiños;
    private int frente = 0;
    private int atras = 0;
    private int tamaño = 0;

    public ColaNinos(int capacidad) {
        cantidadDeNiños = new Nino[capacidad];
    }

    public void agregar(Nino nino) {
        if (tamaño == cantidadDeNiños.length) return;
        cantidadDeNiños[atras] = nino;
        atras = (atras + 1) % cantidadDeNiños.length;
        tamaño++;
    }

    public Nino obtenerPrimerNino() {
        if (tamaño == 0) return null;
        Nino posicion = cantidadDeNiños[frente];
        frente = (frente + 1) % cantidadDeNiños.length;
        tamaño--;
        return posicion;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    public Nino[] listarNinos() {
        Nino[] arr = new Nino[tamaño];
        for (int i = 0; i < tamaño; i++)
            arr[i] = cantidadDeNiños[(frente + i) % cantidadDeNiños.length];
        return arr;
    }

    public void limpiar() {
        frente = atras = tamaño = 0;
    }
}
