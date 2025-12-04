public class ArbolPedidos {

    private Nodo raiz;
    private int cantidadNodos;

    public ArbolPedidos() {
        this.raiz = null;
        this.cantidadNodos = 0;

        assert cantidadNodos >= 0 : "La cantidad de nodos no puede ser negativa";
    }

    public boolean estaVacio() {
        assert cantidadNodos >= 0 : "cantidadNodos no debe ser negativa";
        return cantidadNodos == 0;
    }

    public int getCantidad() {
        assert cantidadNodos >= 0 : "cantidadNodos no debe ser negativa";
        return cantidadNodos;
    }

    public void insertar(Pedido pedidoNuevo) {

        assert pedidoNuevo != null : "No se puede insertar un pedido null";
        assert pedidoNuevo.getTiempoPreparacion() >= 0 : "El tiempo de preparación no puede ser negativo";

        int antes = cantidadNodos;

        raiz = insertarReciente(raiz, pedidoNuevo);
        cantidadNodos++;

        assert cantidadNodos == antes + 1 : "La cantidad de nodos no se incrementó correctamente";
    }

    private Nodo insertarReciente(Nodo actual, Pedido pedidoNuevo) {
        if (actual == null)
            return new Nodo(pedidoNuevo);

        assert actual.getPedido() != null : "Nodo con pedido null detectado";

        if (pedidoNuevo.getTiempoPreparacion() < actual.getPedido().getTiempoPreparacion()) {
            actual.setIzquierdo(insertarReciente(actual.getIzquierdo(), pedidoNuevo));
        } else {
            actual.setDerecho(insertarReciente(actual.getDerecho(), pedidoNuevo));
        }

        return actual;
    }

    public Pedido extraerMinimo() {
        assert cantidadNodos >= 0 : "cantidadNodos no debe ser negativa";

        if (raiz == null)
            return null;

        Nodo padre = null;
        Nodo actual = raiz;

        while (actual.getIzquierdo() != null) {
            padre = actual;
            actual = actual.getIzquierdo();
        }

        assert actual != null : "El nodo mínimo no debe ser null";

        Pedido resultado = actual.getPedido();

        if (padre == null) {
            raiz = actual.getDerecho();
        } else {
            padre.setIzquierdo(actual.getDerecho());
        }

        cantidadNodos--;

        assert cantidadNodos >= 0 : "cantidadNodos no puede quedar en negativo";

        return resultado;
    }

    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
            return;
        }

        Nodo[] cola = new Nodo[cantidadNodos];
        int inicio = 0, fin = 0;

        cola[fin++] = raiz;

        while (inicio < fin) {
            Nodo actual = cola[inicio++];

            String izq = (actual.getIzquierdo() != null) ? "SI" : "NO";
            String der = (actual.getDerecho() != null) ? "SI" : "NO";

            System.out.println(
                    "Pedido: " + actual.getPedido().getNombre() +
                            " | Prep: " + actual.getPedido().getTiempoPreparacion() +
                            " | Espera: " + actual.getPedido().getTiempoEspera() +
                            " | Hijos [Izq:" + izq + " Der:" + der + "]");

            if (actual.getIzquierdo() != null)
                cola[fin++] = actual.getIzquierdo();
            if (actual.getDerecho() != null)
                cola[fin++] = actual.getDerecho();
        }
    }

    public void actualizarTiemposDeEspera(Pedido pedidoEnCocina) {
        recorrerYActualizar(raiz, pedidoEnCocina);
    }

    private void recorrerYActualizar(Nodo nodo, Pedido pedidoEnCocina) {

        assert nodo.getPedido() != null : "Nodo con pedido null detectado";

        if (nodo.getPedido() != pedidoEnCocina) {
            int antes = nodo.getPedido().getTiempoEspera();
            nodo.getPedido().incrementarTiempoEspera();

            assert nodo.getPedido().getTiempoEspera() == antes + 1 : "Tiempo de espera no se incrementó correctamente";
        }

        recorrerYActualizar(nodo.getIzquierdo(), pedidoEnCocina);
        recorrerYActualizar(nodo.getDerecho(), pedidoEnCocina);
    }

}
