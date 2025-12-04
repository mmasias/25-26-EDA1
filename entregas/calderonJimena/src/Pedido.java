class Pedido {
    String tipoPlato;
    int tiempoTotalPreparacion;
    int tiempoRestantePreparacion;
    int minutoLlegada;

    Pedido(String tipoPlato, int tiempoTotalPreparacion, int minutoLlegada) {
        this.tipoPlato = tipoPlato;
        this.tiempoTotalPreparacion = tiempoTotalPreparacion;
        this.tiempoRestantePreparacion = tiempoTotalPreparacion;
        this.minutoLlegada = minutoLlegada;
    }

    int calcularTiempoEspera(int minutoActual) {
        return minutoActual - minutoLlegada;
    }
}