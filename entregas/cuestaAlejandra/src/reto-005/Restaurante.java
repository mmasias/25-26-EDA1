import java.util.ArrayList;
import java.util.Random;

public class Restaurante {
    String[] PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    int[][] RANGOS = {{1,2}, {2,3}, {2,4}, {3,5}, {5,8}};

    ArrayList<Pedido> cola;       
    Pedido cocinando;            
    Random rand = new Random();

    
    public int pedidosAtendidos = 0;
    public long tiempoTotalEspera = 0;
    public int comparaciones = 0; 
    public Restaurante() {
        cola = new ArrayList<Pedido>();
        cocinando = null;
    }

    public void avanzarMinuto(int minutoActual) {
        if (rand.nextDouble() < 0.4) {
            int idx = rand.nextInt(5);
            String tipo = PLATOS[idx];
            int minT = RANGOS[idx][0];
            int maxT = RANGOS[idx][1];
            int tiempo = minT + rand.nextInt(maxT - minT + 1);
            cola.add(new Pedido(tipo, tiempo, minutoActual));
            System.out.println("Llega pedido: " + tipo + " (" + tiempo + " min)");
        }

        if (cocinando != null) {
            cocinando.tiempoRestante = cocinando.tiempoRestante - 1;
            if (cocinando.tiempoRestante == 0) {
                tiempoTotalEspera = tiempoTotalEspera + (minutoActual - cocinando.minutoLlegada);
                pedidosAtendidos = pedidosAtendidos + 1;
                cocinando = null;
            }
        }

        if (cocinando == null && cola.size() > 0) {
            int indiceMenor = 0;
            int menorTiempo = cola.get(0).tiempoRestante;

            for (int i = 1; i < cola.size(); i++) {
                comparaciones = comparaciones + 1; 
                if (cola.get(i).tiempoRestante < menorTiempo) {
                    menorTiempo = cola.get(i).tiempoRestante;
                    indiceMenor = i;
                }
            }

            
            cocinando = cola.get(indiceMenor);
            cola.remove(indiceMenor);
        }

        System.out.println("COLA: " + cola.size() + " pedidos");
        if (cocinando != null) {
            System.out.println("Cocinero: [" + cocinando.tipo + " - " + cocinando.tiempoRestante + " min restantes]");
        } else {
            System.out.println("Cocinero: [libre]");
        }
    }

    public int getPedidosPendientes() {
        return cola.size();
    }

    public void cerrar(int duracion) {
        
        for (int i = 0; i < cola.size(); i++) {
            Pedido p = cola.get(i);
            tiempoTotalEspera = tiempoTotalEspera + (duracion - p.minutoLlegada);
        }
    }
}