import java.util.Random;

public class Restaurante {
    
    private static final String[] PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private static final int[][] RANGOS = {{1,2}, {2,3}, {2,4}, {3,5}, {5,8}};

    private static class Nodo {
        Pedido pedido;
        Nodo izq, der;
        Nodo(Pedido p) { pedido = p; }
    }

    private Nodo raiz = null;
    private int comparaciones = 0;
    private Pedido cocinando = null;
    private Random rand = new Random();

    public int pedidosAtendidos = 0;
    public long tiempoTotalEspera = 0;


    private Nodo insertar(Nodo nodo, Pedido p) {
        if (nodo == null) {
            return new Nodo(p);
        }
        comparaciones++;
        if (p.tiempoRestante < nodo.pedido.tiempoRestante ||
           (p.tiempoRestante == nodo.pedido.tiempoRestante && p.minutoLlegada < nodo.pedido.minutoLlegada)) {
            nodo.izq = insertar(nodo.izq, p);
        } else {
            nodo.der = insertar(nodo.der, p);
        }
        return nodo;
    }

    private class Resultado {
        Nodo nuevoSubarbol;
        Pedido pedidoExtraido;
        Resultado(Nodo n, Pedido p) { nuevoSubarbol = n; pedidoExtraido = p; }
    }

    private Resultado extraerMin(Nodo nodo) {
        if (nodo.izq == null) {
            return new Resultado(nodo.der, nodo.pedido);
        } else {
            comparaciones++;
            Resultado res = extraerMin(nodo.izq);
            nodo.izq = res.nuevoSubarbol;
            return new Resultado(nodo, res.pedidoExtraido);
        }
    }

    private int size(Nodo n) {
        if (n == null) return 0;
        return 1 + size(n.izq) + size(n.der);
    }

    private void sumarTiemposEsperaPendientes(Nodo n, int duracion) {
        if (n == null) return;
        tiempoTotalEspera += (duracion - n.pedido.minutoLlegada);
        sumarTiemposEsperaPendientes(n.izq, duracion);
        sumarTiemposEsperaPendientes(n.der, duracion);
    }

    public void insertarPedido(Pedido p) {
        raiz = insertar(raiz, p);
    }

    public Pedido extraerMinimo() {
        if (raiz == null) return null;
        Resultado res = extraerMin(raiz);
        raiz = res.nuevoSubarbol;
        return res.pedidoExtraido;
    }

    public int tamanoCola() {
        return size(raiz);
    }

    public void cerrar(int duracion) {
        sumarTiemposEsperaPendientes(raiz, duracion);
    }

    public int getComparaciones() {
        return comparaciones;
    }


    public void avanzarMinuto(int minutoActual) {
        if (rand.nextDouble() < 0.4) {
            int idx = rand.nextInt(5);
            String tipo = PLATOS[idx];
            int minT = RANGOS[idx][0];
            int maxT = RANGOS[idx][1];
            int t = minT + rand.nextInt(maxT - minT + 1);
            insertarPedido(new Pedido(tipo, t, minutoActual));
            System.out.println("Llega pedido: " + tipo + " (" + t + " min)");
        }

        if (cocinando != null) {
            cocinando.tiempoRestante--;
            if (cocinando.tiempoRestante == 0) {
                tiempoTotalEspera += (minutoActual - cocinando.minutoLlegada);
                pedidosAtendidos++;
                cocinando = null;
            }
        }

        if (cocinando == null && tamanoCola() > 0) {
            cocinando = extraerMinimo();
        }

        System.out.println("COLA: " + tamanoCola() + " pedidos");
        if (cocinando != null) {
            System.out.println("Cocinero: [" + cocinando.tipo + " - " + cocinando.tiempoRestante + " min restantes]");
        } else {
            System.out.println("Cocinero: [libre]");
        }
    }
}