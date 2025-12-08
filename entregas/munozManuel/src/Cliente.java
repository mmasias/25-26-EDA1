package entregas.munozManuel.src;

class Cliente {
    private final String nombre;
    private final Pedido pedido;
    private final String[] NOMBRE_CLIENTES = {
    "Ana",
    "Luis",
    "María",
    "Carlos",
    "Sofía",
    "Javier",
    "Elena",
    "Miguel",
    "Laura",
    "Andrés"
};

    public Cliente(){
        nombre = NOMBRE_CLIENTES[(int)(Math.random() * 9)];
        pedido = new Pedido();
    }

    public String nombre(){
        return nombre;
    }

    public Pedido darPedido(){
        return pedido;
    }
}
