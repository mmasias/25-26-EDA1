public class Pedido {
    private String tipoDePlato;
    private int tiempoDePreparacion;
    

    public Pedido (String tipoDePlato, int tiempoDePreparacion)
    {
        this.tiempoDePreparacion = tiempoDePreparacion;
        this.tipoDePlato = tipoDePlato;
    }

    public String getTipoDePlato() {
        return tipoDePlato;
    }

    public int getTiempoDePreparacion(){
        return tiempoDePreparacion;
    }
 
}
