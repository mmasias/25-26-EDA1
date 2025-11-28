public class Pedido {

    private final int id;
    private final String nombrePlato;
    private final int tiempoTotal;
    private int tiempoRestante;
    private final int minutoLlegada;
    private int minutoInicio; // -1 mientras no haya empezado
    
    public Pedido(int id, String nombrePlato, int tiempoTotal, int minutoLlegada) {
    this.id = id;
    this.nombrePlato = nombrePlato;
    this.tiempoTotal = tiempoTotal;
    this.tiempoRestante = tiempoTotal;
    this.minutoLlegada = minutoLlegada;
    this.minutoInicio = -1;
    }
    
    public int getId() {
    return id;
    }
    
    public String getNombrePlato() {
    return nombrePlato;
    }
    
    public int getTiempoTotal() {
    return tiempoTotal;
    }
    
    public int getTiempoRestante() {
    return tiempoRestante;
    }
    
    public void reducirUnMinuto() {
    if (tiempoRestante > 0) {
    tiempoRestante--;
    }
    }
    
    public int getMinutoLlegada() {
    return minutoLlegada;
    }
    
    public int getMinutoInicio() {
    return minutoInicio;
    }
    
    public void setMinutoInicio(int minutoInicio) {
    this.minutoInicio = minutoInicio;
    }
    
    public String descripcionCorta() {
    return "Pedido #" + id + " - " + nombrePlato + " (" + tiempoTotal + " min)";
    }
    }