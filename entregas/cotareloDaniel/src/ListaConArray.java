package entregas.cotareloDaniel.src;

public class ListaConArray {

    private int[] datos;
    private int cantidad;

    public ListaConArray(int capacidadInicial) {
        this.datos = new int[capacidadInicial];
        this.cantidad = 0;
    }

    public int size() {
        return this.cantidad;
    }

    public int get(int posicion) {
        assert posicion >= 0 && posicion < this.cantidad;

        return this.datos[posicion];
    }

    public void add(int valor) {
        this.asegurarCapacidad();

        this.datos[this.cantidad] = valor;
        this.cantidad = this.cantidad + 1;
    }

    public void remove(int posicion) {
        int indice;

        assert posicion >= 0 && posicion < this.cantidad;

        indice = posicion;
        while (indice < this.cantidad - 1) {
            this.datos[indice] = this.datos[indice + 1];
            indice = indice + 1;
        }

        this.cantidad = this.cantidad - 1;
    }

    private void asegurarCapacidad() {
        boolean llena;

        llena = this.cantidad == this.datos.length;

        if (llena) {
            this.redimensionar();
        }
    }

    private void redimensionar() {
        int nuevaCapacidad;
        int[] nuevoArray;
        int indice;

        if (this.datos.length == 0) {
            nuevaCapacidad = 1;
        } else {
            nuevaCapacidad = this.datos.length * 2;
        }

        nuevoArray = new int[nuevaCapacidad];

        indice = 0;
        while (indice < this.cantidad) {
            nuevoArray[indice] = this.datos[indice];
            indice = indice + 1;
        }

        this.datos = nuevoArray;
    }

    public void imprimir() {
        int indice;

        System.out.print("[");

        indice = 0;
        while (indice < this.cantidad) {
            System.out.print(this.datos[indice]);

            if (indice < this.cantidad - 1) {
                System.out.print(", ");
            }

            indice = indice + 1;
        }

        System.out.println("]");
    }
}