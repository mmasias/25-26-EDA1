public class Simulacion {
    private int tiempoJornada;
    private Cocina cocina;
    private Plato[] menu; 

    public RCCCF(int tiempoJornada) {
        this.tiempoJornada = tiempoJornada;
        this.cocina = new Cocina();
        inicializarMenu();
    }

    private void inicializarMenu() {
        menu = new Plato[4];
        menu[0] = new Plato("Bebida", 1, 2);
        menu[1] = new Plato("cafe", 2, 3);
        menu[2] = new Plato("Colacao", 2, 4);
        menu[3] = new Plato("Bocadillo", 3, 5);
        menu[4] = new Plato("Ensalada", 5, 8);
    }

    public void generarEventosAleatorios(int minutoActual) {
        
        if (Math.random() < 0.3) {
            int indiceAleatorio = (int) (Math.random() * menu.length);
            Plato platoAleatorio = menu[indiceAleatorio];
            
            Pedido nuevoPedido = new Pedido(platoAleatorio, minutoActual);
            cocina.recibirPedido(nuevoPedido);
        }
    }

    public void iniciarSimulacion() {
        System.out.println("=== INICIANDO SIMULACIÓN RCCCF (" + tiempoJornada + " minutos) ===");
        
        for (int i = 1; i <= tiempoJornada; i++) {
            System.out.println("\n[Minuto " + i + "]");
            generarEventosAleatorios(i);
            cocina.trabajarPorMinuto();
        }
        
        mostrarReporteFinal();
    }

    public void mostrarReporteFinal() {
        System.out.println("\n=======================================");
        System.out.println("       FIN DE LA JORNADA");
        System.out.println("=======================================");
        System.out.println(cocina.obtenerMetricas());
    }
}