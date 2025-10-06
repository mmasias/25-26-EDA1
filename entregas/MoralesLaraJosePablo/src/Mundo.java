import java.util.Random;

public class Mundo {
    private Ludoteca ludoteca;
    private Console console;
    private Random random;
    private String[] nombres = {
        "Lucas", "Mateo", "Sofía", "Valentina", "Diego",
        "Martina", "Tomás", "Camila", "Nicolás", "Isabella",
        "Sebastián", "Lucía", "Daniel", "Emma", "Samuel"
    };

    public Mundo() {
        this.ludoteca = new Ludoteca();
        this.console = new Console();
        this.random = new Random();
    }

    public void ejecutarSimulacion() {
        int opcion;
        do {
            mostrarMenu();
            opcion = console.readInt("Seleccione una opción: ");
            System.out.println();
            procesarOpcion(opcion);
            if (opcion != 0) {
                System.out.println();
                console.readString("Presione ENTER para continuar...");
                limpiarPantalla();
            }
        } while (opcion != 0);
        System.out.println("¡Gracias por usar el simulador de la ludoteca!");
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println(" LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println();
        System.out.println("1. Simular llegada de niño (aleatoria)");
        System.out.println("2. Simular intento de inicio de juego");
        System.out.println("3. Aisha se presenta y pide a los niños que se presenten");
        System.out.println("4. Aisha pide que se presenten los niños mayores de 5 años");
        System.out.println("5. Aisha pide que se presenten los niños cuyo nombre empieza por letra");
        System.out.println("6. Aisha pide que se presenten los cinco primeros niños");
        System.out.println("7. Aisha pide que se presenten los cinco últimos niños");
        System.out.println("8. Aisha y Lydia dicen cuántos niños hay en cola");
        System.out.println("9. Aisha dice la edad promedio de los niños en cola");
        System.out.println("10. Simular intento de inicio del juego de la rana");
        System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
        System.out.println("12. Alarma contra incendios y protocolo de emergencia");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println();
        System.out.println("0. Salir");
        System.out.println();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> opcion1();
            case 2 -> ludoteca.simularInicioJuego();

            case 3 -> opcion3();

            case 4 -> {
                asegurarNiñosAisha();
                ludoteca.presentacionesPorEdad(5);
            }

            case 5 -> {
                asegurarNiñosAisha();
                char letra = console.readChar("Ingrese la letra inicial: ");
                System.out.println();
                ludoteca.presentacionesPorInicial(letra);
            }

            case 6 -> {
                asegurarNiñosAisha();
                ludoteca.presentacionesPrimerosCinco();
            }

            case 7 -> {
                asegurarNiñosAisha();
                ludoteca.presentacionesUltimosCinco();
            }

            case 8 -> ludoteca.mostrarConteoAsistencia();
            case 9 -> ludoteca.mostrarEdadPromedio();

            case 10 -> {
                asegurarNiñosAisha();
                ludoteca.verificarJuegoRana();
            }

            case 11 -> {
                asegurarNiñosAisha();
                ludoteca.separarParaJuego();
            }

            case 12 -> ludoteca.activarAlarmaIncendios();
            case 13 -> ludoteca.mostrarEstado();

            case 0 -> {}
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private void opcion3() {
        if (!ludoteca.aishaTieneNiños()) {
            if (!transferirDesdeLydia()) {
                int cantidad = random.nextInt(3) + 2;
                for (int i = 0; i < cantidad; i++) {
                    String nombre = nombres[random.nextInt(nombres.length)];
                    int edad = random.nextInt(9) + 2;
                    ludoteca.registrarNiñoSilencioso(nombre, edad);
                }
            }
        }

        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (int i = 0; i <  ludotecaContarNiñosAisha(); i++) {
            Niño niño = ludotecaObtenerNiñoAisha(i);
            System.out.println("Niño: Hola, soy " + niño.getNombre() + " y tengo " + niño.getEdad() + " años");
        }
    }

    private boolean transferirDesdeLydia() {
        if (ludotecaContarNiñosLydia() >= 1) {
            ludoteca.simularInicioJuego();
            return true;
        }
        return false;
    }

    private void asegurarNiñosAisha() {
        if (!ludoteca.aishaTieneNiños()) {
            System.out.println("Aisha no tiene niños en su cola.");
            if (random.nextBoolean()) {
                int cantidad = random.nextInt(3) + 2;
                for (int i = 0; i < cantidad; i++) {
                    String nombre = nombres[random.nextInt(nombres.length)];
                    int edad = random.nextInt(9) + 2;
                    ludoteca.registrarNiñoSilencioso(nombre, edad);
                }
            }
        }
    }

    private void opcion1() {
        int cantidadNiños = random.nextInt(3) + 1;
        System.out.println("Llegan " + cantidadNiños + " niños a la ludoteca:");
        for (int i = 0; i < cantidadNiños; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            int edad = random.nextInt(8) + 3;
            ludoteca.simularLlegadaNiño(nombre, edad);
        }
    }

    private void limpiarPantalla() {
        for (int i = 0; i < 2; i++) System.out.println();
    }

    private int ludotecaContarNiñosAisha() {
        return ludoteca.aishaTieneNiños() ? ludotecaContar("Aisha") : 0;
    }

    private int ludotecaContarNiñosLydia() {
        return ludotecaContar("Lydia");
    }

    private int ludotecaContar(String monitora) {
        if (monitora.equalsIgnoreCase("Aisha")) {
            try {
                java.lang.reflect.Field field = ludoteca.getClass().getDeclaredField("aisha");
                field.setAccessible(true);
                Monitora aisha = (Monitora) field.get(ludoteca);
                return aisha.getCantidadNiños();
            } catch (Exception e) {
                return 0;
            }
        } else if (monitora.equalsIgnoreCase("Lydia")) {
            try {
                java.lang.reflect.Field field = ludoteca.getClass().getDeclaredField("lydia");
                field.setAccessible(true);
                Monitora lydia = (Monitora) field.get(ludoteca);
                return lydia.getCantidadNiños();
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    private Niño ludotecaObtenerNiñoAisha(int index) {
        try {
            java.lang.reflect.Field field = ludoteca.getClass().getDeclaredField("aisha");
            field.setAccessible(true);
            Monitora aisha = (Monitora) field.get(ludoteca);
            return aisha.obtenerNiño(index);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.ejecutarSimulacion();
    }
}