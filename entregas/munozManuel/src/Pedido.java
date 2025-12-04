package entregas.munozManuel.src;

class Pedido {
    private final String nombreDelPedido;
    private int tiempoPreparacion;
    private boolean pedidoHecho;
    private final String[] PEDIDOS_DISPONIBLES = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
    private final static int[][] TIEMPO_PREPARACION = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

    public Pedido() {
        int numeroDePedido = (int)(Math.random() * 5);
        pedidoHecho = false;
        nombreDelPedido = PEDIDOS_DISPONIBLES[numeroDePedido];
        tiempoPreparacion = calcularTiempoPreparacion(numeroDePedido);
    }

    public String nombreDelPedido() {
        return nombreDelPedido;
    }

    public int tiempoPreparacion() {
        return tiempoPreparacion;
    }

    public boolean pedidoHehco(){
        return pedidoHecho;
    }

    public void terminarPedido(){
        pedidoHecho = true;
    }

    private int calcularTiempoPreparacion(int numeroDePedido){
        int min = TIEMPO_PREPARACION[numeroDePedido][0];
        int max = TIEMPO_PREPARACION[numeroDePedido][1];
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
}
