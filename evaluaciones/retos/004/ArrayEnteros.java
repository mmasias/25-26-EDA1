package 004;


    public class ArrayEnteros {
        private int[] datos;
        private int usados;
    
        public ArrayEnteros(int capacidad) {
            datos = new int[capacidad];
            usados = 0;
        }
    
        public boolean estaLleno() {
            return usados == datos.length;
        }
    
        public boolean estaVacio() {
            return usados == 0;
        }
    
        public int totalUsados() {
            return usados;
        }
    
        public void agregarAlFinal(int valor) {
            if (estaLleno()) throw new IllegalStateException("El array está lleno");
            datos[usados++] = valor;
        }
    
        public int obtener(int indice) {
            validar(indice);
            return datos[indice];
        }
    
        public void reemplazar(int indice, int valor) {
            validar(indice);
            datos[indice] = valor;
        }
    
        public void eliminarEn(int indice) {
            validar(indice);
            for (int i = indice; i < usados - 1; i++) {
                datos[i] = datos[i + 1];
            }
            usados--;
        }
    
        public int eliminarPrimero() {
            if (estaVacio()) throw new IllegalStateException("Array vacío");
            int valor = datos[0];
            for (int i = 0; i < usados - 1; i++) {
                datos[i] = datos[i + 1];
            }
            usados--;
            return valor;
        }
    
        public int[] obtenerDatos() {
            int[] copia = new int[usados];
            System.arraycopy(datos, 0, copia, 0, usados);
            return copia;
        }
    
        private void validar(int indice) {
            if (indice < 0 || indice >= usados) {
                throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
            }
        }
    }
   