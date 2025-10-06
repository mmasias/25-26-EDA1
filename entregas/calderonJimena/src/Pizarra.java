public class Pizarra {
    public void mostrarEstadoMonitores(Monitor monitoraLydia, Monitor monitoraAisha, Monitor monitoraDalsy) {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        mostrarEstadoMonitor(monitoraLydia);
        mostrarEstadoMonitor(monitoraAisha);
        mostrarEstadoMonitor(monitoraDalsy);
        System.out.println("========================================");
    }

    private void mostrarEstadoMonitor(Monitor monitora) {
        System.out.println(monitora.getNombreMonitor() + ":");
        Nino[] listaNinos = monitora.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("  Cola vacia");
        } else {
            System.out.println("  Ninos en cola: " + listaNinos.length);
            for (int i = 0; i < listaNinos.length; i++) {
                System.out.println("  - " + listaNinos[i].getNombre() + " (" + listaNinos[i].getEdad() + " anos)");
            }
        }
        System.out.println();
    }
}


