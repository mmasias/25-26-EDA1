public class Pedido {
    private String nombre;
    private int tiempo;
    private int tiempoInicial; 

    public Pedido(TipoPlato tipo) {
        this.nombre = tipo.nombre();
        this.tiempo = tipo.calcularTiempoReal();
        this.tiempoInicial = this.tiempo;
    }

    public void cocinar() {
        if (tiempo > 0) {
            tiempo--;
        }
    }

    public boolean esMasRapidoQue(Pedido otro) {
        return this.tiempo < otro.tiempo;
    }

    public String nombrePlato() { 
        return nombre; 
    }

    public int tiempoTotal() { 
        return tiempoInicial; 
    }

    public int tiempoRestante() { 
        return tiempo; 
    }

    public String toString() {
        return String.format("[%s - %d min]", nombre, tiempo);
    }
}