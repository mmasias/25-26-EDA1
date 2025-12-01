import java.util.Random;


public class GeneradorPedidos {

    private static final String[] NOMBRES_PLATOS = {
        "Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"
    };
    
    private static final int[] TIEMPOS_MIN = {
        1, 2, 2, 3, 5
    };

    private static final int[] TIEMPOS_MAX = {
        2, 3, 4, 5, 8
    };
    

    private Random random;

    public GeneradorPedidos() {
        this.random = new Random();
    }


    public Pedido generar(int minutoActual) {
        
        int indicePlato = random.nextInt(NOMBRES_PLATOS.length);
        String tipoNombre = NOMBRES_PLATOS[indicePlato];
        int min = TIEMPOS_MIN[indicePlato];
        int max = TIEMPOS_MAX[indicePlato];

        int tiempo = random.nextInt(max - min + 1) + min;

        return new Pedido(tipoNombre, tiempo, minutoActual);
    }
}
