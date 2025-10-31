public class SimuladorLista {
    private Object[] datos;
    private int tamaño;
    private static final int CAPACIDAD_INICIAL = 4;
    public final int TAMAÑO_INICIAL = 0;
    public final int MULTIPLICADOR_ARRAY = 2;

    public SimuladorLista() {
        datos = new Object[CAPACIDAD_INICIAL];
        tamaño = TAMAÑO_INICIAL;
    }

    private void expandir() {
        int nuevaCapacidad = datos.length * MULTIPLICADOR_ARRAY;
        Object[] nuevoArray = new Object[nuevaCapacidad];

        for (int i = 0; i < tamaño; i++) {
            nuevoArray[i] = datos[i];
        }

        datos = nuevoArray;
    }

    public void agregar(Object elemento) {
        if (tamaño == datos.length) {
            expandir();
        }
        datos[tamaño] = elemento;
        tamaño++;
    }

    public void agregar(int indice, Object elemento) {
        if (indice < 0 || indice > tamaño) {
            System.out.println("Índice fuera de rango");
            return;
        }

        if (tamaño == datos.length) {
            expandir();
        }

        for (int i = tamaño; i > indice; i--) {
            datos[i] = datos[i - 1];
        }

        datos[indice] = elemento;
        tamaño++;
    }

    public Object obtener(int indice) {
        boolean rango = indice < 0 || indice >= tamaño;
        if (rango) {
            System.out.println("Índice fuera de rango");
            return null;
        }
        return datos[indice];
    }

    public Object remover(int indice) {
        boolean rango = indice < 0 || indice >= tamaño;
        if (rango) {
            System.out.println("Índice fuera de rango");
            return null;
        }

        Object eliminado = datos[indice];

        for (int i = indice; i < tamaño - 1; i++) {
            datos[i] = datos[i + 1];
        }

        datos[tamaño - 1] = null;
        tamaño--;

        return eliminado;
    }

    public int getTamaño() {
        return tamaño;
    }

    public int getCapacidad() {
        return datos.length;
    }

    public String toString() {
        String texto = "[";
        for (int i = 0; i < tamaño; i++) {
            texto += datos[i];
            if (i < tamaño - 1) {
                texto += ", ";
            }
        }
        texto += "]";
        return texto;
    }
}
