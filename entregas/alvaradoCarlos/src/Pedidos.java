class Pedido {
    private String nombre;
    private int tiempoTotal;
    private int tiempoRestante;

    public Pedido(TipoPlato plato) {
        this.nombre = plato.nombre();
        this.tiempoTotal = plato.calcularTiempoReal();
        this.tiempoRestante = this.tiempoTotal;
    }

    public void cocinar() {
        if (this.tiempoRestante > 0) {
            this.tiempoRestante--;
        }
    }

    public boolean estaTerminado() {
        return this.tiempoRestante <= 0;
    }

    public boolean esMasUrgenteQue(Pedido otroPedido) {
        return this.tiempoRestante < otroPedido.tiempoRestante;
    }

    public String nombrePlato() {
        return nombre;
    }

    public int tiempoRestante() {
        return tiempoRestante;
    }

    public int tiempoTotal() {
        return tiempoTotal;
    }

    public String toString() {
        return String.format("%s (%d min)", nombre, tiempoRestante);
    }
}