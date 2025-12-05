package vaqueroInigo.reto005_refactorizacionMinimalista;

import java.util.ArrayList;
import java.util.List;

public class ColaPedidos {
    private List<Pedido> pendientes;
    private int comparacionesTotales;

    public ColaPedidos() {
        this.pendientes = new ArrayList<>();
        this.comparacionesTotales = 0;
    }

    public void agregar(Pedido p) {
        pendientes.add(p);
    }

    public boolean estaVacia() {
        return pendientes.isEmpty();
    }

    public int size() {
        return pendientes.size();
    }

    public Pedido extraerMinimo() {
        if (pendientes.isEmpty()) {
            return null;
        }

        int indiceMinimo = 0;
        Pedido mejor = pendientes.get(0);

        for (int i = 1; i < pendientes.size(); i++) {
            Pedido actual = pendientes.get(i);
            comparacionesTotales++;
            if (actual.getTiempoPreparacion() < mejor.getTiempoPreparacion()) {
                mejor = actual;
                indiceMinimo = i;
            }
        }

        pendientes.remove(indiceMinimo);
        return mejor;
    }

    public int getComparacionesTotales() {
        return comparacionesTotales;
    }
}

