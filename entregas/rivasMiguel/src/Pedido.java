public class Pedido {

    public enum Tipo {
        BEBIDA(1, 2),
        CAFE(2, 2),
        COLACAO(2, 3),
        BOCADILLO(3, 3),
        ENSALADA(5, 4);

        private final int tiempoBase;
        private final int variacionAleatoria;

        Tipo(int tiempoBase, int variacionAleatoria) {
            this.tiempoBase = tiempoBase;
            this.variacionAleatoria = variacionAleatoria;
        }

        public int calcularTiempoPreparacion() {
            return Main.rnd.nextInt(this.variacionAleatoria) + this.tiempoBase;
        }
    }

    private final Tipo tipo;
    private final int tiempoPreparacion;
    private int tiempoRestante;
    private int tiempoLlegada;

    public Pedido(Tipo tipo) {
        this.tipo = tipo;
        this.tiempoPreparacion = tipo.calcularTiempoPreparacion();
        this.tiempoRestante = this.tiempoPreparacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int minuto) {
        this.tiempoLlegada = minuto;
    }

    public void reducirTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    @Override
    public String toString() {
        return tipo + " (" + tiempoRestante + " min)";
    }
}