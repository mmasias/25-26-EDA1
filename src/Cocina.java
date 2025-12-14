import java.util.ArrayList;

public class Cocina {

    ArrayList<Pedido> cola;
    int comparaciones;

    public Cocina() {
        cola = new ArrayList<>();
        comparaciones = 0;
    }

    public void agregarPedido(Pedido p) {
        cola.add(p);
    }

    public boolean hayPedidos() {
        return !cola.isEmpty();
    }

    public int pedidosEnCola() {
        return cola.size();
    }

    public Pedido obtenerPedidoMasCorto() {
        Pedido menor = cola.get(0);

        for (int i = 1; i < cola.size(); i++) {
            comparaciones++;
            if (cola.get(i).tiempoRestante < menor.tiempoRestante) {
                menor = cola.get(i);
            }
        }

        cola.remove(menor);
        return menor;
    }
}
