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
        if (raiz == null) {
            raiz = new Nodo(pedidoNuevo);
        } else {
            Nodo nodoActual = raiz;
            boolean insertado = false; 

            while (!insertado) {
                if (pedidoNuevo.getTiempoPreparacion() < nodoActual.getPedido().getTiempoPreparacion()) {
                    if (nodoActual.getIzquierdo() == null) {
                        nodoActual.setIzquierdo(new Nodo(pedidoNuevo));
                        insertado = true; 
                    } else {
                        nodoActual = nodoActual.getIzquierdo();
                    }
                } else {
                    if (nodoActual.getDerecho() == null) {
                        nodoActual.setDerecho(new Nodo(pedidoNuevo));
                        insertado = true; 
                    } else {
                        nodoActual = nodoActual.getDerecho();
                    }
                }
            }
        }
        cantidadNodos++;
    }

    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        Pedido pedidoResultado;

        if (raiz.getIzquierdo() == null) {
            pedidoResultado = raiz.getPedido();
            raiz = raiz.getDerecho(); 
        } else {
            Nodo padre = raiz;
            Nodo nodoActual = raiz.getIzquierdo();

            
            while (nodoActual.getIzquierdo() != null) {
                padre = nodoActual;
                nodoActual = nodoActual.getIzquierdo();
            }

            pedidoResultado = nodoActual.getPedido();
            padre.setIzquierdo(nodoActual.getDerecho());
        }

        cantidadNodos--;
        return pedidoResultado;
    }

    public void imprimirArbolIterativo() {
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
        } else {
            Nodo[] colaNodos = new Nodo[cantidadNodos];
            int indiceLectura = 0;
            int indiceEscritura = 0;

            colaNodos[indiceEscritura] = raiz;
            indiceEscritura++;

           
            while (indiceLectura < indiceEscritura) {
                Nodo nodoActual = colaNodos[indiceLectura];
                indiceLectura++;

                String infoIzquierda = (nodoActual.getIzquierdo() != null) ? "SI" : "NO";
                String infoDerecha = (nodoActual.getDerecho() != null) ? "SI" : "NO";
                
                System.out.println("Pedido: " + nodoActual.getPedido().getNombre() + 
                                   " | Prep: " + nodoActual.getPedido().getTiempoPreparacion() + 
                                   " | Espera: " + nodoActual.getPedido().getTiempoEspera() +
                                   " | Hijos: [Izq:" + infoIzquierda + ", Der:" + infoDerecha + "]");

                if (nodoActual.getIzquierdo() != null) {
                    colaNodos[indiceEscritura] = nodoActual.getIzquierdo();
                    indiceEscritura++;
                }
                if (nodoActual.getDerecho() != null) {
                    colaNodos[indiceEscritura] = nodoActual.getDerecho();
                    indiceEscritura++;
                }
            }
        }
    }

    public void actualizarTiemposDeEspera() {
        if (raiz != null) {
            Nodo[] colaNodos = new Nodo[cantidadNodos];
            int indiceLectura = 0;
            int indiceEscritura = 0;

            colaNodos[indiceEscritura] = raiz;
            indiceEscritura++;

            while (indiceLectura < indiceEscritura) {
                Nodo nodoActual = colaNodos[indiceLectura];
                indiceLectura++;

                nodoActual.getPedido().incrementarTiempoEspera();

                if (nodoActual.getIzquierdo() != null) {
                    colaNodos[indiceEscritura] = nodoActual.getIzquierdo();
                    indiceEscritura++;
                }
                if (nodoActual.getDerecho() != null) {
                    colaNodos[indiceEscritura] = nodoActual.getDerecho();
                    indiceEscritura++;
                }
            }
        }
    }
}