public class SimulacionTelefono {
    public static void main(String[] args) {
        try {
            mostrarBienvenida();
            
            Ludoteca ludoteca = new Ludoteca();
            ludoteca.ejecutarSimulacion();
            
            mostrarDespedida();
            
        } catch (Exception e) {
            System.err.println("Error durante la simulación: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              SIMULACIÓN DEL TELÉFONO DESCACHARRADO           ║");
        System.out.println("║                    Ludoteca de Lydia y Aisha                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Esta simulación modela una ludoteca durante 2 horas donde:");
        System.out.println("• Los niños llegan gradualmente y forman colas");
        System.out.println("• Se juega al teléfono descacharrado cuando hay >5 niños");
        System.out.println("• Los mensajes se deforman al pasar entre los niños");
        System.out.println("• Cada paso del juego toma 1 minuto");
        System.out.println();
        System.out.println("Patrones de llegada:");
        System.out.println("• Primeros 10 min: 0-2 niños por minuto");
        System.out.println("• Siguientes 20 min: 1 niño cada 3 min (50% probabilidad)");
        System.out.println("• Resto del tiempo: no llegan más niños");
        System.out.println();
        System.out.println("¡Iniciando simulación!");
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println();
    }
    
    private static void mostrarDespedida() {
        System.out.println();
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println("¡Simulación completada exitosamente!");
        System.out.println();
        System.out.println("La ludoteca ha cerrado después de 2 horas de diversión.");
        System.out.println("Los niños han disfrutado jugando al teléfono descacharrado");
        System.out.println("y han experimentado cómo los mensajes se transforman");
        System.out.println("al pasar de persona en persona.");
        System.out.println();
        System.out.println("Gracias por usar esta simulación.");
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    ¡HASTA LA PRÓXIMA!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
