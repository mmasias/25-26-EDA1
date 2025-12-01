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
        menu[0] = new Plato("Arroz Frito", 5, 10);
        menu[1] = new Plato("Pollo Agridulce", 10, 20);
        menu[2] = new Plato("Rollito Primavera", 3, 7);
        menu[3] = new Plato("Sopa Wantan", 8, 15);
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