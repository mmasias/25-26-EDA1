package v001;

public class NodoPedido {
    private Pedido pedido;
    private NodoPedido izquierdo;
    private NodoPedido derecho;
    private NodoPedido padre;

    public NodoPedido(Pedido pedido) {
        assert pedido != null : "pedido no puede ser null";
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
        assert izquierdo != this : "no puede asignarse como su propio hijo izquierdo";
        this.izquierdo = izquierdo; 
    }

    public NodoPedido getDerecho() { 
        return derecho; 
    }

    public void setDerecho(NodoPedido derecho) { 
        assert derecho != this : "no puede asignarse como su propio hijo derecho";
        this.derecho = derecho; 
    }

    public NodoPedido getPadre() { 
        return padre; 
    }
    
    public void setPadre(NodoPedido padre) { 
        assert padre != this : "no puede asignarse como su propio padre";
        this.padre = padre; 
    }
}
