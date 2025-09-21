public class ColaNiños {
    private Niño[] cola;
    private int inicioCola;
    private int finalCola;
    private int cantidadCola;

    public ColaNiños() {
        cola = new Niño[100];
        inicioCola = 0;
        finalCola = 0;
        cantidadCola = 0;
    }

    public void añadirNiño(Niño niño) {
        if (cantidadCola < cola.length) {
            cola[finalCola] = niño;
            finalCola = (finalCola + 1) % cola.length;
            cantidadCola++;
        }
    }
    public Niño[] obtenerNiñosCola() {
        Niño[] resultado = new Niño[cantidadCola];
        for (int i = 0; i < cantidadCola; i++) {
            resultado[i] = cola[(inicioCola + i) % cola.length];
        }
        return resultado;
    }
    
    public int tamañoCola() {
        return cantidadCola;
    }


    public void limpiarCola() {
        inicioCola = 0;
        finalCola = 0;
        cantidadCola = 0;
    }

}

