public class NodoPedido {
    private Pedido pedido;
    private NodoPedido izquierdo;
    private NodoPedido derecho;
    private NodoPedido padre;

    public NodoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }

    public Pedido getPedido() { 
        return pedido; 
    }

    public NodoPedido getIzquierdo() { 
        return izquierdo; 
    }

    public void setIzquierdo(NodoPedido izquierdo) { 
        this.izquierdo = izquierdo; 
    }

    public NodoPedido getDerecho() { 
        return derecho; 
    }

    public void setDerecho(NodoPedido derecho) { 
        this.derecho = derecho; 
    }

    public NodoPedido getPadre() { 
        return padre; 
    }
    
    public void setPadre(NodoPedido padre) { 
        this.padre = padre; 
    }
}
