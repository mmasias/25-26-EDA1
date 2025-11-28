class Pedido {
    private final String nombre;
    private final int tiempoPreparacion;

    public Pedido(String nombre, int tiempoPreparacion) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String nombre() {
        return nombre;
    }

    public int tiempoPreparacion() {
        return tiempoPreparacion;
    }
    
}
