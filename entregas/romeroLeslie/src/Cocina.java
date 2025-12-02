public class Cocina {
    private HeapPedidos colaPedidos;
    private Pedido pedidoActual;
    
    private int pedidosAtendidos;
    private int tiempoEsperaTotal; 

    public Cocina() {
        this.colaPedidos = new HeapPedidos();
        this.pedidoActual = null;
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
    }

    public void recibirPedido(Pedido pedido) {
        this.colaPedidos.insertar(pedido);
    }

    public void asignarPedido(int minutoActual) {
        boolean puedeAsignar = (this.pedidoActual == null) && (this.colaPedidos.size() > 0);
        
        if (puedeAsignar) {
            this.pedidoActual = this.colaPedidos.extraerMin();
            this.pedidoActual.setInicio(minutoActual);
        }
    }

    public boolean procesarMinuto() {
        boolean resultado = false;
        if (this.pedidoActual != null) {
            boolean terminado = this.pedidoActual.procesarMinuto();
            
            if (terminado) {
                liberarPedido();
                resultado = true;
            }
        }
        return resultado;
    }
    
    public void liberarPedido() {
        if (this.pedidoActual != null) {
            this.pedidosAtendidos++;
            this.tiempoEsperaTotal += this.pedidoActual.getTiempoEspera();
            
            System.out.println(">>> LIBERADO: Pedido ID:" + this.pedidoActual.getId() + " - " + this.pedidoActual.getTipo() + 
                               "  Espera: " + this.pedidoActual.getTiempoEspera() + " min.");
            this.pedidoActual = null;
        }
    }

    public int getPedidosAtendidos() { return pedidosAtendidos; }
    public int pedidosEnCola() { return colaPedidos.size(); }
    public Pedido getActual() { return pedidoActual; }
    public int getComparaciones() { return colaPedidos.getComparaciones(); }
    public int getTiempoEsperaTotal() { return tiempoEsperaTotal; } 
}

