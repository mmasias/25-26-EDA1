public class Cocina {
    private Pedido pedidoEnPreparacion;
    
    private Pedido[] colaPrioridad;
    private int contadorCola;
    
    private Pedido[] pilaHistorial;
    private int contadorHistorial; 
    
    private ComparadorTiempo comparador;

    public Cocina() {
        this.comparador = new ComparadorTiempo();
        this.colaPrioridad = new Pedido[10];
        this.contadorCola = 0;
        this.pilaHistorial = new Pedido[10];
        this.contadorHistorial = 0;
        this.pedidoEnPreparacion = null;
    }

    public void recibirPedido(Pedido pedido) {
        System.out.println("  -> Cocina recibe nuevo: " + pedido);
        
        if (contadorCola >= colaPrioridad.length) {
            expandirCola();
        }
        
        colaPrioridad[contadorCola] = pedido;
        contadorCola++;

        ordenarCola();
    }

    public void asignarSiguientePedido() {
        if (hayPedidosPendientes()) {
            pedidoEnPreparacion = colaPrioridad[0];
            
          
            for (int i = 0; i < contadorCola - 1; i++) {
                colaPrioridad[i] = colaPrioridad[i + 1];
            }
            colaPrioridad[contadorCola - 1] = null;
            contadorCola--;
            
            System.out.println("  -> Cocina comienza a preparar: " + pedidoEnPreparacion.obtenerNombrePlato());
        } else {
            pedidoEnPreparacion = null;
        }
    }

    public void trabajarPorMinuto() {
        if (pedidoEnPreparacion == null && hayPedidosPendientes()) {
            asignarSiguientePedido();
        }

        if (pedidoEnPreparacion != null) {
            pedidoEnPreparacion.reducirTiempo(1);
            
            if (pedidoEnPreparacion.estaTerminado()) {
                System.out.println("  -> [TERMINADO] " + pedidoEnPreparacion);
                agregarAlHistorial(pedidoEnPreparacion);
                pedidoEnPreparacion = null;
                asignarSiguientePedido(); 
            }
        }
    }
    
    private void agregarAlHistorial(Pedido p) {
        if (contadorHistorial >= pilaHistorial.length) {
            expandirHistorial();
        }
        pilaHistorial[contadorHistorial] = p;
        contadorHistorial++;
    }

    public boolean hayPedidosPendientes() {
        return contadorCola > 0;
    }

   
    private void ordenarCola() {
        for (int i = 0; i < contadorCola - 1; i++) {
            for (int j = 0; j < contadorCola - i - 1; j++) {
                
                // AQUÍ USAMOS EL MÉTODO DEL UML DIRECTAMENTE
                if (comparador.comparacion(colaPrioridad[j], colaPrioridad[j+1]) > 0) {
                    
                    Pedido temp = colaPrioridad[j];
                    colaPrioridad[j] = colaPrioridad[j+1];
                    colaPrioridad[j+1] = temp;
                }
            }
        }
    }

    private void expandirCola() {
        Pedido[] nuevoArray = new Pedido[colaPrioridad.length * 2];
        for (int i = 0; i < colaPrioridad.length; i++) {
            nuevoArray[i] = colaPrioridad[i];
        }
        colaPrioridad = nuevoArray;
    }

    private void expandirHistorial() {
        Pedido[] nuevoArray = new Pedido[pilaHistorial.length * 2];
        for (int i = 0; i < pilaHistorial.length; i++) {
            nuevoArray[i] = pilaHistorial[i];
        }
        pilaHistorial = nuevoArray;
    }

    public String obtenerMetricas() {
        return "Reporte Cocina:\n" +
               "- Pedidos Terminados (Historial): " + contadorHistorial + "\n" +
               "- Pedidos Pendientes en Cola: " + contadorCola + "\n" +
               "- Comparaciones realizadas: " + comparador.obtenerContadorComparaciones();
    }
}