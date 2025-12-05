public class ArbolPedidos {
    private Nodo raiz;
    private int comparaciones;
    private int tamaño;

    public ArbolPedidos() {
        this.raiz = null;
        this.comparaciones = 0;
        this.tamaño = 0;
    }

    public void insertar(Pedido pedido) {
        assert pedido != null : "El pedido no puede ser null";

        if (raiz == null) {
            raiz = new Nodo(pedido);
        } else {
            Nodo actual = raiz;
            boolean insertado = false;

            while (!insertado) {
                comparaciones++;
                if (pedido.getTiempoPreparacion() < actual.getPedido().getTiempoPreparacion()) {
                    if (actual.getIzquierdo() == null) {
                        actual.setIzquierdo(new Nodo(pedido));
                        insertado = true;
                    } else {
                        actual = actual.getIzquierdo();
                    }
                } else {
                    if (actual.getDerecho() == null) {
                        actual.setDerecho(new Nodo(pedido));
                        insertado = true;
                    } else {
                        actual = actual.getDerecho();
                    }
                }
            }
        }
        tamaño++;
    }

    public Pedido extraerMinimo() {
        assert tamaño > 0 : "No se puede extraer de un árbol vacío";

        Pedido min;

        if (raiz.getIzquierdo() == null) {
            min = raiz.getPedido();
            raiz = raiz.getDerecho();
        } else {
            Nodo padre = raiz;
            while (padre.getIzquierdo().getIzquierdo() != null) {
                comparaciones++;
                padre = padre.getIzquierdo();
            }
            min = padre.getIzquierdo().getPedido();
            padre.setIzquierdo(padre.getIzquierdo().getDerecho());
        }
        tamaño--;

        return min;
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public void incrementarEspera() {
        if (raiz != null) {
            Nodo[] cola = new Nodo[tamaño];
            int inicio = 0;
            int fin = 0;

            cola[fin] = raiz;
            fin++;

            while (inicio < fin) {
                Nodo actual = cola[inicio];
                inicio++;
                actual.getPedido().incrementarEspera();

                if (actual.getIzquierdo() != null) {
                    cola[fin] = actual.getIzquierdo();
                    fin++;
                }
                if (actual.getDerecho() != null) {
                    cola[fin] = actual.getDerecho();
                    fin++;
                }
            }
        }
    }

    public int tiempoTotalEspera() {
        int total = 0;

        if (raiz != null) {
            Nodo[] cola = new Nodo[tamaño];
            int inicio = 0;
            int fin = 0;

            cola[fin] = raiz;
            fin++;

            while (inicio < fin) {
                Nodo actual = cola[inicio];
                inicio++;
                total += actual.getPedido().getTiempoEspera();

                if (actual.getIzquierdo() != null) {
                    cola[fin] = actual.getIzquierdo();
                    fin++;
                }
                if (actual.getDerecho() != null) {
                    cola[fin] = actual.getDerecho();
                    fin++;
                }
            }
        }

        return total;
    }

    public void printTree() {
        if (raiz != null) {
            Pedido pedidoRaiz = raiz.getPedido();
            System.out.println(pedidoRaiz.getTipo() + " (" + pedidoRaiz.getTiempoPreparacion() + " min)");

            Nodo[] stack = new Nodo[tamaño];
            String[] prefixStack = new String[tamaño];
            boolean[] isLastStack = new boolean[tamaño];
            boolean[] isLeftStack = new boolean[tamaño];
            int top = -1;

            if (raiz.getIzquierdo() != null) {
                top = top + 1;
                stack[top] = raiz.getIzquierdo();
                prefixStack[top] = "";
                isLastStack[top] = true;
                isLeftStack[top] = true;
            }

            if (raiz.getDerecho() != null) {
                top = top + 1;
                stack[top] = raiz.getDerecho();
                prefixStack[top] = "";
                isLastStack[top] = raiz.getIzquierdo() == null;
                isLeftStack[top] = false;
            }

            while (top >= 0) {
                Nodo nodo = stack[top];
                String prefix = prefixStack[top];
                boolean isLast = isLastStack[top];
                boolean isLeft = isLeftStack[top];
                top = top - 1;

                String connector = isLast ? "└─" : "├─";
                String extension = isLast ? "  " : "│ ";
                String side = isLeft ? "L: " : "R: ";

                Pedido pedido = nodo.getPedido();
                System.out.println(prefix + connector + side + pedido.getTipo() + " (" + pedido.getTiempoPreparacion() + " min)");

                String newPrefix = prefix + extension;

                if (nodo.getIzquierdo() != null) {
                    top = top + 1;
                    stack[top] = nodo.getIzquierdo();
                    prefixStack[top] = newPrefix;
                    isLastStack[top] = true;
                    isLeftStack[top] = true;
                }

                if (nodo.getDerecho() != null) {
                    top = top + 1;
                    stack[top] = nodo.getDerecho();
                    prefixStack[top] = newPrefix;
                    isLastStack[top] = nodo.getIzquierdo() == null;
                    isLeftStack[top] = false;
                }
            }
        } else {
            System.out.println("Árbol vacío");
        }
    }
}
