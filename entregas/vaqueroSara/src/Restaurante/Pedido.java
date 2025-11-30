package Restaurante;

class Pedido {
    String tipo;
    int tiempoPreparacion;
    int tiempoLlegada;
    Pedido izquierdo, derecho;

    public Pedido(String tipo, int tiempoPreparacion, int tiempoLlegada) {
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoLlegada = tiempoLlegada;
        this.izquierdo = null;
        this.derecho = null;
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoPreparacion + " min restantes)";
    }
}
