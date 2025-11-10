package evaluaciones.examenes.examenParcial;

 public  class Bloque{
    private final ArrayEnteros arrayInterno;

    public Bloque(int capacidad) {
        this.arrayInterno = new ArrayEnteros(capacidad);
    }

    public boolean estaLleno() {
        return arrayInterno.estaLleno();
    }

    public boolean estaVacio() {
        return arrayInterno.estaVacio();
    }

    public int usados() {
        return arrayInterno.totalUsados();
    }

    public void agregarAlFinal(int valor) {
        arrayInterno.agregarAlFinal(valor);
    }

    public int obtener(int indice) {
        return arrayInterno.obtener(indice);
    }

    public void reemplazar(int indice, int valor) {
        arrayInterno.reemplazar(indice, valor);
    }

    public void eliminarEn(int indice) {
        arrayInterno.eliminarEn(indice);
    }

    public int eliminarPrimero() {
        return arrayInterno.eliminarPrimero();
    }

    public int[] obtenerDatos() {
        return arrayInterno.obtenerDatos();
    }
}
    