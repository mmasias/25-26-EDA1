
class GeneradorPedidos {
    
    private static final String[][] PLATOS = {
        {"Bebida", "1", "2"},
        {"Café", "2", "3"},
        {"Colacao", "2", "4"},
        {"Bocadillo", "3", "5"},
        {"Ensalada", "5", "8"}
    };
    
    private int contadorId;
    
    public GeneradorPedidos() {
        this.contadorId = 0;
    }
    
    public Pedido generar(int minutoActual) {
        int indice = (int) (Math.random() * PLATOS.length);
        String tipo = PLATOS[indice][0];
        int tiempoMin = Integer.parseInt(PLATOS[indice][1]);
        int tiempoMax = Integer.parseInt(PLATOS[indice][2]);
        
        int tiempo = tiempoMin + (int) (Math.random() * (tiempoMax - tiempoMin + 1));
        
        contadorId++;
        return new Pedido(tipo, tiempo, contadorId, minutoActual);
    }
    
    public boolean hayLlegada() {
        return Math.random() < 0.40;
    }
}