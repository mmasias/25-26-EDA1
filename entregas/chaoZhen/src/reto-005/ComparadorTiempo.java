public class ComparadorTiempo {
    private double contadorComparaciones;

    public ComparadorTiempo() {
        this.contadorComparaciones = 0;
    }

 
    public int comparacion(Pedido pedido1, Pedido pedido2) {
        this.contadorComparaciones++;
        
        int t1 = pedido1.obtenerTiempoRestante();
        int t2 = pedido2.obtenerTiempoRestante();

        if (t1 < t2) {
            return -1;
        } else if (t1 > t2) {
            return 1;  
        } else {
            return 0;  
        }
    }

    public double obtenerContadorComparaciones() {
        return contadorComparaciones;
    }
}