public class Cocina {
    private Pedido[] colaDePedidos;
    private int cantidadPedidos;
    private Pedido pedidoActual;

    private int pedidosAtendidos;
    private int tiempoEsperaTotal;
    private int numeroComparaciones;

    public Cocina() {
        this.colaDePedidos = new Pedido[200];
        this.cantidadPedidos = 0;
        this.pedidoActual = null;
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
        this.numeroComparaciones = 0;
    }

    public void agregarPedido(Pedido p) {
        if (cantidadPedidos < colaDePedidos.length) {
            colaDePedidos[cantidadPedidos] = p;
            cantidadPedidos++;
        }
    }

    public void procesarMinuto() {
        if (pedidoActual == null) {
            buscarSiguientePedidoMasCorto();
        }

        if (pedidoActual != null) {
            pedidoActual.tick();
            if (pedidoActual.isTerminado()) {
                pedidosAtendidos++;
                tiempoEsperaTotal += pedidoActual.getTIEMPO_PREP();
                pedidoActual = null;
            }
        }
    }

    private void buscarSiguientePedidoMasCorto() {
        if (cantidadPedidos == 0) {
            return;
        }

        int indiceMejor = buscarIndiceDelMejorCandidato();

        pedidoActual = colaDePedidos[indiceMejor];

        eliminarPedidoDeLaCola(indiceMejor);
    }

    private int buscarIndiceDelMejorCandidato() {
        int indiceMejor = 0;
        Pedido mejorCandidato = colaDePedidos[0];

        for (int i = 1; i < cantidadPedidos; i++) {
            numeroComparaciones++;
            if (colaDePedidos[i].getTIEMPO_PREP() < mejorCandidato.getTIEMPO_PREP()) {
                mejorCandidato = colaDePedidos[i];
                indiceMejor = i;
            }
        }
        return indiceMejor;
    }

    private void eliminarPedidoDeLaCola(int indiceEliminar) {
        for (int i = indiceEliminar; i < cantidadPedidos - 1; i++) {
            colaDePedidos[i] = colaDePedidos[i + 1];
        }

        colaDePedidos[cantidadPedidos - 1] = null;
        cantidadPedidos--;
    }

    public void mostrarColaConPrioridad() {
        if (cantidadPedidos == 0) {
            System.out.println("   (La cola está vacía)");
            return;
        }

        Pedido[] copiaTemporal = new Pedido[cantidadPedidos];
        for (int i = 0; i < cantidadPedidos; i++) {
            copiaTemporal[i] = colaDePedidos[i];
        }

        for (int i = 0; i < cantidadPedidos - 1; i++) {
            for (int j = 0; j < cantidadPedidos - i - 1; j++) {
                if (copiaTemporal[j].getTIEMPO_PREP() > copiaTemporal[j + 1].getTIEMPO_PREP()) {
                    Pedido temp = copiaTemporal[j];
                    copiaTemporal[j] = copiaTemporal[j + 1];
                    copiaTemporal[j + 1] = temp;
                }
            }
        }

        System.out.println("\n   --- VISTA DE ÁRBOL DE PRIORIDAD ---");
        System.out.println("   (Raíz a la izquierda - Ramas a la derecha)");
        System.out.println("   ------------------------------------------------");
        imprimirArbolRecursivo(copiaTemporal, 0, "");
        System.out.println("   ------------------------------------------------\n");
    }

    private void imprimirArbolRecursivo(Pedido[] arbol, int indice, String indentacion) {
        if (indice >= cantidadPedidos) {
            return;
        }

        imprimirArbolRecursivo(arbol, 2 * indice + 2, indentacion + "      ");

        String flecha = (indice == 0) ? "RAIZ -> " : "      +-- ";
        System.out
                .println(indentacion + flecha + "[" + arbol[indice].getTIEMPO_PREP() + "m] " + arbol[indice].getTIPO());

        imprimirArbolRecursivo(arbol, 2 * indice + 1, indentacion + "      ");
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public int getTamanoCola() {
        return cantidadPedidos;
    }

    public int getTotalAtendidos() {
        return pedidosAtendidos;
    }

    public int getTiempoEsperaTotal() {
        return tiempoEsperaTotal;
    }

    public int getPendientes() {
        return cantidadPedidos + (pedidoActual != null ? 1 : 0);
    }

    public int getNumeroComparaciones() {
        return numeroComparaciones;
    }
}