
public class Pedido {
    private static int contadorId = 1;
    
    private int id;
    private String nombre;
    private int tiempoPreparacion;
    private int tiempoRestante;
    private int minutoLlegada;

    public Pedido(String nombre, int tiempoPreparacion, int minutoLlegada) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoRestante = tiempoPreparacion;
        this.minutoLlegada = minutoLlegada;
    }

    public void cocinarUnMinuto() {
        tiempoRestante--;
    }

    public boolean estaTerminado() {
        return tiempoRestante <= 0;
    }

    public String getNombre() { return nombre; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getMinutoLlegada() { return minutoLlegada; }
    public int getId() { return id; }

}
