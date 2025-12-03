public class RestauranteRCCCF {

    private Cocinero cocinero = new Cocinero();
    private ArbolPedidos arbol = new ArbolPedidos();
    
    private int atendidos = 0;
    private int esperaTotal = 0;

    public void abrir() {
        for (int t = 1; t <= 120; t++) {
            Utilidades.mensaje("--- Minuto " + t + " ---");
            Utilidades.saltarLinea();

            if (Math.random() < 0.4) generarPedido();

            if (cocinero.libre() && !arbol.vacio()) {
                cocinero.asignar(arbol.sacarMinimo());
            }

            boolean estabaOcupado = !cocinero.libre();
            cocinero.trabajar();
            if (estabaOcupado && cocinero.libre()) atendidos++;

            esperaTotal += arbol.getPendientes();
            imprimirEstado();
        }
        resumen();
    }

    private void generarPedido() {
        String[] nombres = {"Bebida", "Café", "Bocata", "Ensalada"};
        int[][] tiempos = {{1,2}, {2,3}, {3,5}, {5,8}};
        int i = (int)(Math.random() * nombres.length);
        int t = (int)(Math.random() * (tiempos[i][1] - tiempos[i][0] + 1) + tiempos[i][0]);
        
        Pedido p = new Pedido(nombres[i], t);
        arbol.insertar(p);
        Utilidades.mensaje("Llega: " + p + "\n");
    }

    private void imprimirEstado() {
        Utilidades.mensaje("Cola: " + arbol.getPendientes() + " | Cocina: ");
        Utilidades.mensaje(cocinero.libre() ? "[Libre]" : cocinero.getPedido().toString());
        Utilidades.saltarLinea();
    }

    private void resumen() {
        Utilidades.saltarLinea();
        Utilidades.mensaje("=== FIN JORNADA (ARBOL) ===");
        Utilidades.saltarLinea();
        Utilidades.mensaje("Atendidos: " + atendidos);
        Utilidades.saltarLinea();
        Utilidades.mensaje("Pendientes: " + arbol.getPendientes());
        Utilidades.saltarLinea();
        Utilidades.mensaje("Comparaciones: " + arbol.getComparaciones()); // Dato CLAVE
        Utilidades.saltarLinea();
    }
}