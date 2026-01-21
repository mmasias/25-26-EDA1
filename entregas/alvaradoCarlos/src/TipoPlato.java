import java.util.Random;

class TipoPlato{
    private String nombre;
    private int tiempoMin;
    private int tiempoMax;

    public TipoPlato(String nombre, int tiempoMin, int tiempoMax){
        this.nombre = nombre;
        this.tiempoMin = tiempoMin;
        this.tiempoMax = tiempoMax;
    }

    public int calcularTiempoReal() {
        return new Random().nextInt((tiempoMax - tiempoMin) + 1) + tiempoMin;
    }

    public String nombre(){
        return nombre;
    }
}