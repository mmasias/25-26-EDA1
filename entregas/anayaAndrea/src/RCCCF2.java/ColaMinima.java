package rcccf2;

import java.util.ArrayList;

public class ColaMinima {

    private ArrayList<Pedido> datos;
    private int comparaciones;

    public ColaMinima() {
        datos = new ArrayList<>();
        comparaciones = 0;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public boolean estaVacio() {
        return datos.isEmpty();
    }

    public void insertar(Pedido p) {
        datos.add(p);
        subir(datos.size() - 1);
    }

    private void subir(int pos) {
        while (pos > 0) {
            int padre = (pos - 1) / 2;

            comparaciones++;

            if (datos.get(pos).getTiempoPreparacion() <
                datos.get(padre).getTiempoPreparacion()) {

                intercambiar(pos, padre);
                pos = padre;
            } else {
                return;
            }
        }
    }

    public Pedido extraerMinimo() {
        if (datos.isEmpty()) return null;

        Pedido minimo = datos.get(0);
        Pedido ultimo = datos.remove(datos.size() - 1);

        if (!datos.isEmpty()) {
            datos.set(0, ultimo);
            bajar(0);
        }

        return minimo;
    }

    private void bajar(int pos) {
        while (true) {
            int izq = pos * 2 + 1;
            int der = pos * 2 + 2;
            int menor = pos;

            if (izq < datos.size()) {
                comparaciones++;
                if (datos.get(izq).getTiempoPreparacion() <
                    datos.get(menor).getTiempoPreparacion()) {
                    menor = izq;
                }
            }

            if (der < datos.size()) {
                comparaciones++;
                if (datos.get(der).getTiempoPreparacion() <
                    datos.get(menor).getTiempoPreparacion()) {
                    menor = der;
                }
            }

            if (menor == pos) return;

            intercambiar(pos, menor);
            pos = menor;
        }
    }

    private void intercambiar(int a, int b) {
        Pedido temp = datos.get(a);
        datos.set(a, datos.get(b));
        datos.set(b, temp);
    }

    public void aumentarEsperaGeneral() {
        for (Pedido p : datos) {
            p.aumentarEspera();
        }
    }
}
