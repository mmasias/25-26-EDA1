class Cocina {
    private MinHeap cola;
    private Pedido enProceso;
    private int completados = 0;

    public Cocina() {
        this.cola = new MinHeap(20);
    }

    public void recibirPedido(Pedido pedido) {
        cola.insertar(pedido);
    }

    public void trabajar() {
        if (enProceso == null && !cola.esVacia()) {
            enProceso = cola.extraerMinimo();
        } else {
            enProceso.cocinar();
            if (enProceso.estaTerminado()) {
                completados++;
                enProceso = null;
            }
        }
    }

    public String estado() {
        if (enProceso == null)
            return "Libre";
        return String.format("[%s - %d min]", enProceso.nombrePlato(), enProceso.tiempoRestante());
    }

    public int pendientes() {
        return cola.tamaño();
    }

    public int completados() {
        return completados;
    }

    public int eficiencia() {
        return cola.comparaciones();
    }
}