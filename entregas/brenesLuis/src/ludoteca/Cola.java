public class Cola {
    private Ninno[] cola;
    private int frente;
    private int fin;
    private int capacidad;

    public Cola(int capacidad) {
        this.capacidad = capacidad;
        cola = new Ninno[capacidad];
        frente = 0;
        fin = 0;
    }

    public boolean estaVacia() {
        return frente == fin;
    }

    public boolean estaLlena() {
        return fin == capacidad;
    }

    public void encolar(Ninno ninno) {
        if (!estaLlena()) {
            cola[fin] = ninno;
            fin++;
        }
    }

    public Ninno desencolar() {
        if (!estaVacia()) {
            Ninno ninno = cola[frente];
            frente++;
            return ninno;
        } else {
            return null;
        }
    }

    public int size() {
        return fin - frente;
    }

    public void reiniciar() {
        frente = 0;
        fin = 0;
    }

    public Ninno[] getElementos() {
        Ninno[] activos = new Ninno[size()];
        for (int i = frente; i < fin; i++) {
            activos[i - frente] = cola[i];
        }
        return activos;
    }
}
