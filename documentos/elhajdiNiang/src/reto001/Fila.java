package reto001;

import java.util.*;

public class Fila {
    private Queue<Niño> cola = new LinkedList<>();

    public void encolar(List<Niño> lista) {
        if (lista != null && !lista.isEmpty()) {
            cola.addAll(lista);
        }
    }

    public int tamaño() {
        return cola.size();
    }

    public List<Niño> vaciarFila() {
        List<Niño> jugadores = new ArrayList<>(cola);
        cola.clear();
        return jugadores;
    }
}

