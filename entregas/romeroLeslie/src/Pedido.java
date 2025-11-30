import java.util.Random;

public class Pedido {
    
    private static final String[] TIPOS_PLATO_ARRAY = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] RANGOS_PREP_ARRAY = {
        {1, 2}, 
        {2, 3}, 
        {2, 4}, 
        {3, 5},
        {5, 8}  
    };
    
    private static final int NUM_PLATOS = TIPOS_PLATO_ARRAY.length;
    private static final Random RAND = new Random();

    private String tipo;
    private int tiempoPrep;
    private int tiempoRestante; 
    private int llegada; 
    private int inicio;
    private int id;

    public Pedido(int id, int minutoLlegada) {
        this.id = id;
        this.llegada = minutoLlegada;
        this.inicio = -1;
        
        int indice = RAND.nextInt(NUM_PLATOS);
        
        this.tipo = TIPOS_PLATO_ARRAY[indice];
        
        int[] rango = RANGOS_PREP_ARRAY[indice];
        
      
        this.tiempoPrep = RAND.nextInt(rango[1] - rango[0] + 1) + rango[0];
        this.tiempoRestante = this.tiempoPrep;
    }

    public boolean procesarMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
        return tiempoRestante == 0;
    }
    
    public void setInicio(int minuto) {
        if (this.inicio == -1) {
            this.inicio = minuto;
        }
    }
    
    public int getTiempoEspera() {
        return (inicio != -1) ? (inicio - llegada) : 0;
    }

    public String getTipo() { return tipo; }
    public int getTiempoPrep() { return tiempoPrep; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getLlegada() { return llegada; }
    public int getId() { return id; }
}
