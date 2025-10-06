package reto002;

class Mundo {
    private Ludoteca ludoteca;
    private Console console;
    
    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Seleccione opción: ");
            procesarOpcion(opcion);
            console.readString("Presione ENTER para continuar...");
        } while (opcion != 0);
    }
}