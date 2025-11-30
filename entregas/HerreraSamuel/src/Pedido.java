public class Pedido {
    private String tipoDePlato;
    private int tiempoDePreparacion;
    private int minutoLlegada;
    

    public Pedido (String tipoDePlato, int tiempoDePreparacion,int minutoLlegada)
    {
        this.tiempoDePreparacion = tiempoDePreparacion;
        this.tipoDePlato = tipoDePlato;
        this.minutoLlegada = minutoLlegada;
    }

    public String getTipoDePlato() {
        return tipoDePlato;
    }

    public int getTiempoDePreparacion(){
        return tiempoDePreparacion;
    }
    
    public int getMinutoLlegada() {
        return minutoLlegada;
    }
 
}
