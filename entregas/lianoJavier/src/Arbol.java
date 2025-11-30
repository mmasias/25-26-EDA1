public class Arbol {
        private NodoArbol raiz;
        private int tamanio;
        private int comparaciones;

        public Arbol() {
                raiz = null;
                tamanio = 0;
                comparaciones = 0;
        }

        public void insertar(Pedido pedido) {
                raiz = insertarRecursivo(raiz, pedido);
                tamanio++;
        }

        private NodoArbol insertarRecursivo(NodoArbol nodoActual, Pedido pedido) {
                if (nodoActual == null) {
                        return new NodoArbol(pedido);
                }

                comparaciones++;

                if (pedido.obtenerTiempoPreparacion() < nodoActual.pedido.obtenerTiempoPreparacion()) {
                        nodoActual.hijoIzquierdo = insertarRecursivo(nodoActual.hijoIzquierdo, pedido);
                } else {
                        nodoActual.hijoDerecho = insertarRecursivo(nodoActual.hijoDerecho, pedido);
                }

                return nodoActual;
        }

        public Pedido extraerMinimo() {
                if (estaVacia()) {
                        return null;
                }

                Pedido minimo = encontrarMinimo(raiz);
                raiz = eliminarMinimo(raiz);
                tamanio--;

                return minimo;
        }

        private Pedido encontrarMinimo(NodoArbol nodo) {
                while (nodo.hijoIzquierdo != null) {
                        comparaciones++;
                        nodo = nodo.hijoIzquierdo;
                }
                return nodo.pedido;
        }

        private NodoArbol eliminarMinimo(NodoArbol nodo) {
                if (nodo.hijoIzquierdo == null) {
                        return nodo.hijoDerecho;
                }

                comparaciones++;
                nodo.hijoIzquierdo = eliminarMinimo(nodo.hijoIzquierdo);
                return nodo;
        }

        public boolean estaVacia() {
                return raiz == null;
        }

        public int obtenerTamanio() {
                return tamanio;
        }

        public int obtenerComparaciones() {
                return comparaciones;
        }

}
