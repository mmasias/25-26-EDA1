package entregas.munozManuel.src;

class Pedido {
    private final String pedido;
    private final int tiempoPreparacion;
    private final String[] PEDIDOS_DISPONIBLES = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final static int[][] TIEMPO_PREPARACION = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

    public Pedido() {
        int numeroDePedido = (int)(Math.random() * 5);
        pedido = PEDIDOS_DISPONIBLES[numeroDePedido];
        tiempoPreparacion = calcularTiempoPreparacion(numeroDePedido);
    }

    public String pedido() {
        return pedido;
    }

    public int tiempoPreparacion() {
        return tiempoPreparacion;
    }

    private static int calcularTiempoPreparacion(int numeroDePedido){
        int min = TIEMPO_PREPARACION[numeroDePedido][0];
        int max = TIEMPO_PREPARACION[numeroDePedido][1];
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
}
