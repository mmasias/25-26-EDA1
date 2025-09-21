public class Cola {
    private Nino[] cola;
    private int inicio;
    private int fin;
    private int tamaño;

    public Cola() {
        cola = new Nino[100];
        inicio = 0;
        fin = 0;
        tamaño = 0;
    }

    public void añadirNiño(Nino niño) {
        if (tamaño < cola.length) {
            cola[fin] = niño;
            fin = (fin + 1) % cola.length;
            tamaño++;
        }
    }

    public Nino[] obtenerNiñosCola() {
        Nino[] resultado = new Nino[tamaño];
        for (int i = 0; i < tamaño; i++) {
            resultado[i] = cola[(inicio + i) % cola.length];
        }
        return resultado;
    }

    public int tamañoCola() {
        return tamaño;
    }

    public void limpiarCola() {
        inicio = 0;
        fin = 0;
        tamaño = 0;
    }

    @Override
    public String toString() {
        String s = "";
        Nino[] ninos = obtenerNiñosCola();
        for (int i = 0; i < ninos.length; i++) {
            s += ninos[i].getNombre();
            if (i < ninos.length - 1) s += ", ";
        }
        return s.isEmpty() ? "Cola vacía" : s;
    }
}