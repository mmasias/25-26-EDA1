import java.util.Scanner;

public class Ludoteca {
    private Monitora lydia = new Monitora("Lydia");
    private Monitora aisha = new Monitora("Aisha");
    private Monitora dalsy = new Monitora("Dalsy");
    private Scanner sc = new Scanner(System.in);

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());
            procesar(opcion);
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                sc.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("""
        ========================================
                LUDOTECA - SIMULACIÓN
        ========================================
        1.  Simular llegada de niño
        13. Mostrar monitoras y niños
        0.  Salir
        ========================================
        """);
    }

    private void procesar(int opcion) {
        switch (opcion) {
            case 1 -> simularLlegada();
            case 13 -> mostrarEstado();
            case 8 -> conteoAsistencia();
            case 9 -> edadPromedio();
            case 11 -> separarParaJuego();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private void simularLlegada() {
    System.out.println("¿Deseas crear un niño aleatorio? (s/n)");
    String respuesta = sc.nextLine().toLowerCase();

    Ninio n;
    if (respuesta.equals("s")) {
        n = new Ninio();  // usa el constructor aleatorio
    } else {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        n = new Ninio(nombre, edad);
    }

    System.out.println("Llega " + n.toString());
    System.out.println(n.getNombre() + " pasa a la cola de Lydia");
    lydia.recibirNinio(n);
}

    private void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println("\nLYDIA:");
        lydia.mostrarCola();
        System.out.println("\nAISHA:");
        aisha.mostrarCola();
        System.out.println("\nDALSY:");
        dalsy.mostrarCola();
    }
}

    private void presentacionGeneral() {
        aisha.presentarse();
        for (Ninio n : aisha.getCola()) n.presentarse();
}

    private void presentacionPorEdad() {
        System.out.print("Edad mínima: ");
        int edadMinima = Integer.parseInt(sc.nextLine());
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
        for (Ninio n : aisha.getCola()) {
            if (n.getEdad() > edadMinima) n.presentarse();
    }
}

    private void presentacionPorInicial() {
        System.out.print("Letra inicial: ");
        char letra = sc.nextLine().charAt(0);
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
        for (Ninio n : aisha.getCola()) {
            if (n.nombreEmpiezaPor(letra)) System.out.println("Hola, soy " + n.getNombre());
    }
}

    private void primerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        int limite = Math.min(5, aisha.getCola().size());
        for (int i = 0; i < limite; i++) {
            System.out.println("Hola, soy " + aisha.getCola().get(i).getNombre());
    }
}

    private void ultimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        int size = aisha.getCola().size();
        int inicio = Math.max(0, size - 5);
        for (int i = inicio; i < size; i++) {
            System.out.println("Hola, soy " + aisha.getCola().get(i).getNombre());
    }
}

    private void conteoAsistencia() {
        int x = lydia.getCola().size();
        int y = aisha.getCola().size();
        int z = dalsy.getCola().size();
        System.out.printf("""
            CONTEO DE ASISTENCIA:
            Lydia tiene %d niños en cola
            Aisha tiene %d niños en cola
            Dalsy tiene %d niños en cola
            Total: %d niños
            """, x, y, z, x + y + z);
}

    private void edadPromedio() {
        if (aisha.getCola().isEmpty()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
    }
        double suma = 0;
        for (Ninio n : aisha.getCola()) suma += n.getEdad();
        double promedio = suma / aisha.getCola().size();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", promedio);
}

    private void separarParaJuego() {
        System.out.println("Separando niños para el juego de la rana...");
        for (Ninio n : new LinkedList<>(aisha.getCola())) {
            if (n.getEdad() < 5) {
                dalsy.recibirNinio(n);
                aisha.getCola().remove(n);
        }
    }
}

