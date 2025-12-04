public class Pedido {
    private static int contadorId = 1;
    
    private int id;
    private String nombre; 
    private int tiempoPreparacionTotal; 
    private int tiempoRestante; 
    private int minutoLlegada; 

    public Pedido(String nombre, int tiempoPreparacionTotal, int minutoLlegada) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.tiempoPreparacionTotal = tiempoPreparacionTotal;
        this.tiempoRestante = tiempoPreparacionTotal;
        this.minutoLlegada = minutoLlegada;
    }

    public void cocinarUnMinuto() {
        tiempoRestante--;
    }

    public boolean estaTerminado() {
        return tiempoRestante <= 0;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getTiempoPreparacionTotal() {
        return tiempoPreparacionTotal;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    public int getId() {
        return id;
    }
}
