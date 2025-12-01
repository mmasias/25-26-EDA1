public class ComparadorTiempo {
    private double contadorComparaciones;

    public ComparadorTiempo() {
        this.contadorComparaciones = 0;
    }

 
    public int comparacion(Pedido pedido1, Pedido pedido2) {
        this.contadorComparaciones++;
        
        int tiempo1 = pedido1.obtenerTiempoRestante();
        int tiempo2 = pedido2.obtenerTiempoRestante();

        if (tiempo1 < tiempo2) {
            return -1;
        } else if (tiempo1 > tiempo2) {
            return 1;  
        } else {
            return 0;  
        }
    }

    public double obtenerContadorComparaciones() {
        return contadorComparaciones;
    }
}