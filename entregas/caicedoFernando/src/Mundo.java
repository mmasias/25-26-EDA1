public class Mundo {
    private final Ludoteca ludoteca;
    private final Console console;
    
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }

    public Mundo() {
        this.ludoteca = new Ludoteca();
        this.console = new Console();
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Seleccione opción: ");
            procesarOpcion(opcion);
            console.readString("Presione ENTER para continuar...");
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        console.writeln("\n========================================");
        console.writeln("        LUDOTECA - SIMULACIÓN");
        console.writeln("========================================\n");
        
        console.writeln("1.  Simular llegada de niño");
        console.writeln("2.  Simular intento de inicio de juego");
        console.writeln("3.  Aisha se presenta y pide a los niños que se presenten");
        console.writeln("4.  Aisha pide que se presenten los niños mayores de 5 años");
        console.writeln("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
        console.writeln("6.  Aisha pide que se presenten los cinco primeros niños");
        console.writeln("7.  Aisha pide que se presenten los cinco últimos niños");
        console.writeln("8.  Aisha y Lydia dicen cuántos niños hay en cola");
        console.writeln("9.  Aisha dice la edad promedio de los niños en cola");
        console.writeln("10. Simular intento de inicio del juego de la rana");
        console.writeln("11. Paso de niños menores de 5 años a monitora Dalsy");
        console.writeln("12. Alarma contra incendios y protocolo de emergencia");
        console.writeln("13. Mostrar monitoras y niños");
        console.writeln("\n0.  Salir");
    }

    private void procesarOpcion(int opcion) {
        console.writeln();
        
        switch (opcion) {
            case 1 -> simularLlegadaNiño();
            case 2 -> ludoteca.intentarInicioDeJuego();
            case 3 -> ludoteca.presentacionesGenerales();
            case 4 -> presentacionesPorEdad();
            case 5 -> presentacionesPorInicial();
            case 6 -> ludoteca.presentacionesPrimerosCinco();
            case 7 -> ludoteca.presentacionesUltimosCinco();
            case 8 -> ludoteca.conteoDeAsistencia();
            case 9 -> ludoteca.calcularEdadPromedio();
            case 10 -> ludoteca.verificarJuegoDeLaRana();
            case 11 -> ludoteca.separarNiñosParaJuego();
            case 12 -> ludoteca.alarmaContraIncendios();
            case 13 -> ludoteca.mostrarEstado();
            case 0 -> console.writeln("Saliendo de la simulación...");
            default -> console.writeln("Opción no válida");
        }
    }
    
    private void simularLlegadaNiño() {
        console.writeln("=== LLEGADA DE NIÑO ===\n");
        ludoteca.llegadaNiñoAleatorio();
    }

    private void presentacionesPorEdad() {
        console.writeln("=== PRESENTACIONES POR EDAD MÍNIMA ===\n");
        int edadMinima = console.readInt("Ingrese la edad mínima: ");
        ludoteca.presentacionesPorEdad(edadMinima);
    }

    private void presentacionesPorInicial() {
        console.writeln("=== PRESENTACIONES POR INICIAL ===\n");
        char letra = console.readChar("Ingrese la letra inicial: ", true);
        ludoteca.presentacionesPorInicial(letra);
    }
}
