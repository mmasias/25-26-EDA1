public class ArbolPedidos {

    private Nodo raiz;
    private int cantidadNodos;

    public ArbolPedidos() {
        this.raiz = null;
        this.cantidadNodos = 0;
    }

    public boolean estaVacio() {
        return cantidadNodos == 0;
    }

    public int getCantidad() {
        return cantidadNodos;
    }

    public void insertar(Pedido pedidoNuevo) {
        raiz = insertarRec(raiz, pedidoNuevo);
        cantidadNodos++;
    }

    private Nodo insertarRec(Nodo actual, Pedido pedidoNuevo) {
        if (actual == null)
            return new Nodo(pedidoNuevo);

        if (pedidoNuevo.getTiempoPreparacion() < actual.getPedido().getTiempoPreparacion()) {
            actual.setIzquierdo(insertarRec(actual.getIzquierdo(), pedidoNuevo));
        } else {
            actual.setDerecho(insertarRec(actual.getDerecho(), pedidoNuevo));
        }

        return actual;
    }

    public Pedido extraerMinimo() {
        if (raiz == null) return null;

        Nodo padre = null;
        Nodo actual = raiz;

        while (actual.getIzquierdo() != null) {
            padre = actual;
            actual = actual.getIzquierdo();
        }

        Pedido resultado = actual.getPedido();

        if (padre == null) {
            raiz = actual.getDerecho();
        } else {
            padre.setIzquierdo(actual.getDerecho());
        }

        cantidadNodos--;
        return resultado;
    }

    public void imprimirArbolIterativo() {
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
            return;
        }

        Nodo[] cola = new Nodo[cantidadNodos];
        int inicio = 0, fin = 0;

        cola[fin++] = raiz;

        while (inicio < fin) {
            Nodo act = cola[inicio++];

            String izq = (act.getIzquierdo() != null) ? "SI" : "NO";
            String der = (act.getDerecho() != null) ? "SI" : "NO";

            System.out.println("Pedido: " + act.getPedido().getNombre()
                    + " | Prep: " + act.getPedido().getTiempoPreparacion()
                    + " | Espera: " + act.getPedido().getTiempoEspera()
                    + " | Hijos [Izq:" + izq + " Der:" + der + "]");

            if (act.getIzquierdo() != null) cola[fin++] = act.getIzquierdo();
            if (act.getDerecho() != null) cola[fin++] = act.getDerecho();
        }
    }

    public void actualizarTiemposDeEspera() {
        recorrerYActualizar(raiz);
    }

    private void recorrerYActualizar(Nodo nodo) {
        if (nodo == null) return;
        nodo.getPedido().incrementarTiempoEspera();
        recorrerYActualizar(nodo.getIzquierdo());
        recorrerYActualizar(nodo.getDerecho());
    }


    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
        } else {
            imprimirNodo(raiz, "", true);
        }
    }

    private void imprimirNodo(Nodo nodo, String prefijo, boolean esUltimo) {
        System.out.print(prefijo);

        if (esUltimo) {
            System.out.print("└── ");
        } else {
            System.out.print("├── ");
        }

    
        System.out.println(nodo.getPedido().getNombre() + " (" + nodo.getPedido().getTiempoPreparacion() + ")");

       
        String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

        
        boolean tieneIzq = (nodo.getIzquierdo() != null);
        boolean tieneDer = (nodo.getDerecho() != null);

       
        if (tieneIzq) {
            imprimirNodo(nodo.getIzquierdo(), nuevoPrefijo, !tieneDer);
        }

       
        if (tieneDer) {
            imprimirNodo(nodo.getDerecho(), nuevoPrefijo, true);
        }
    }
}
