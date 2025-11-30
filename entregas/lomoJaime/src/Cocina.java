import java.util.Random;

public class Cocina {
    private ColaPrioridadPedidos cola;
    private Cocinero[] cocineros;

    public Cocina(int numeroDeCocineros, int capacidadCola) {
        this.cola = new ColaPrioridadPedidos(capacidadCola);
        this.cocineros = new Cocinero[numeroDeCocineros];
        for (int i = 0; i < numeroDeCocineros; i++) {
            cocineros[i] = new Cocinero();
        }
    }

    public void procesaMinuto(int minutoActual, ContextoSimulacion contexto) {
        if (contexto.getGeneradorAleatorio().nextDouble() < contexto.getProbabilidadDeLlegada()) {
            Pedido nuevoPedido = generarPedidoAleatorio(minutoActual, contexto);
            cola.agregar(nuevoPedido);
            System.out.println("Llega pedido: " + nuevoPedido.getTipo() + " (" + nuevoPedido.getTiempoPreparacion() + " min)");
        }

        for (Cocinero cocinero : cocineros) {
            cocinero.avanzarMinuto();
            if (cocinero.finalizarSiEstaListo() != null) {
                contexto.incrementarPedidosAtendidos();
            }
        }

        for (Cocinero cocinero : cocineros) {
            if (cocinero.estaLibre() && !cola.estaVacia()) {
                Pedido pedido = cola.extraerMinimo(contexto, minutoActual);
                if (pedido != null) {
                    cocinero.asignarPedido(pedido);
                }
            }
        }
    }

    private Pedido generarPedidoAleatorio(int minutoActual, ContextoSimulacion contexto) {
        Random generador = contexto.getGeneradorAleatorio();

        String[] tipos = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
        int[][] rangos = {
            {1, 2},
            {2, 3},
            {2, 4},
            {3, 5},
            {5, 8}
        };

        int indice = generador.nextInt(tipos.length);
        String tipo = tipos[indice];
        int min = rangos[indice][0];
        int max = rangos[indice][1];
        int tiempo = min + generador.nextInt(max - min + 1);

        return new Pedido(tipo, tiempo, minutoActual);
    }

    public int getNumeroDePedidosEnCola() {
        return cola.size();
    }

    public Cocinero[] getCocineros() {
        return cocineros;
    }
}