public class Pedido implements Comparable<Pedido> {

    private static int siguienteId = 1;
    private static long comparaciones = 0;

    private int identificador;
    private TipoPlato tipoPlato;
    private int tiempoTotalPreparacion;
    private int tiempoRestantePreparacion;
    private int minutoLlegada;
    private Integer minutoInicioPreparacion;

    public Pedido(TipoPlato tipoPlato, int tiempoTotalPreparacion, int minutoLlegada) {
        this.identificador = siguienteId++;
        this.tipoPlato = tipoPlato;
        this.tiempoTotalPreparacion = tiempoTotalPreparacion;
        this.tiempoRestantePreparacion = tiempoTotalPreparacion;
        this.minutoLlegada = minutoLlegada;
        this.minutoInicioPreparacion = null;
    }

    public TipoPlato getTipoPlato() {
        return tipoPlato;
    }

    public int getTiempoTotalPreparacion() {
        return tiempoTotalPreparacion;
    }

    public int getTiempoRestantePreparacion() {
        return tiempoRestantePreparacion;
    }

    public void reducirTiempoRestante() {
        if (tiempoRestantePreparacion > 0) {
            tiempoRestantePreparacion--;
        }
    }

    public int calcularTiempoEspera() {
        if (minutoInicioPreparacion == null) return 0;
        return minutoInicioPreparacion - minutoLlegada;
    }

    public void setMinutoInicioPreparacion(int minuto) {
        this.minutoInicioPreparacion = minuto;
    }

    public static long getComparaciones() {
        return comparaciones;
    }

    @Override
    public int compareTo(Pedido otro) {
        comparaciones++;
        return Integer.compare(this.tiempoTotalPreparacion, otro.tiempoTotalPreparacion);
    }

    @Override
    public String toString() {
        return "Pedido #" + identificador + " - " +
               tipoPlato.getNombre() + " (" + tiempoTotalPreparacion + " min)";
    }
}
