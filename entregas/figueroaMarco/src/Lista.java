public class Lista {
    private CustomArray array;
    private int capacidadInicial;
    private int tamañoActual;

    public Lista() {
        this.capacidadInicial = 10;
        this.array = new CustomArray(capacidadInicial);
        this.tamañoActual = 0;
    }

    public void agregar(Object elemento) {
        if (tamañoActual >= capacidadInicial) {
            CustomArray nuevoArray = new CustomArray(capacidadInicial * 2);
            for (int i = 0; i < tamañoActual; i++) {
                nuevoArray.modificarElemento(i, obtener(i));
            }
            array = nuevoArray;
            capacidadInicial *= 2;
        }
        array.modificarElemento(tamañoActual, elemento);
        tamañoActual++;
    }

    public void agregarEnPosicion(int posicion, Object elemento) {
        if (posicion < 0 || posicion > tamañoActual) {
            System.out.println("Error: Posición inválida");
            return;
        }

        if (tamañoActual >= capacidadInicial) {
            CustomArray nuevoArray = new CustomArray(capacidadInicial * 2);
            for (int i = 0; i < posicion; i++) {
                nuevoArray.modificarElemento(i, obtener(i));
            }
            nuevoArray.modificarElemento(posicion, elemento);
            for (int i = posicion; i < tamañoActual; i++) {
                nuevoArray.modificarElemento(i + 1, obtener(i));
            }
            array = nuevoArray;
            capacidadInicial *= 2;
        } else {
            for (int i = tamañoActual; i > posicion; i--) {
                array.modificarElemento(i, obtener(i - 1));
            }
            array.modificarElemento(posicion, elemento);
        }
        tamañoActual++;
    }

    public Object obtener(int posicion) {
        if (posicion < 0 || posicion >= tamañoActual) {
            System.out.println("Error: Posición inválida");
            return null;
        }
        array.mostrarElemento(posicion);
        return array.getTipoDato();
    }

    public void eliminar(int posicion) {
        if (posicion < 0 || posicion >= tamañoActual) {
            System.out.println("Error: Posición inválida");
            return;
        }

        for (int i = posicion; i < tamañoActual - 1; i++) {
            array.modificarElemento(i, obtener(i + 1));
        }
        array.eliminarElemento(tamañoActual - 1);
        tamañoActual--;
    }

    public int tamaño() {
        return tamañoActual;
    }

    public boolean estaVacia() {
        return tamañoActual == 0;
    }

    public void mostrar() {
        array.mostrarArray();
    }
}