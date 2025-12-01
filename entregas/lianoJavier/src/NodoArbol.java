public class NodoArbol {
        Pedido pedido;
        NodoArbol hijoIzquierdo;
        NodoArbol hijoDerecho;

        public NodoArbol(Pedido pedido) {
                this.pedido = pedido;
                this.hijoIzquierdo = null;
                this.hijoDerecho = null;
        }
}
