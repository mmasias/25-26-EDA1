import java.util.List;
import java.util.Random;

public class Ludoteca {
    private static final String[] NOMBRES = {
        "Lucas", "Sofía", "Diego", "Valeria", "Mateo",
        "Emma", "Noah", "Liam", "Julia", "Ariana"
    };
    private static final int EDAD_MINIMA = 2;
    private static final int EDAD_MAXIMA = 10;
    private static final int NIÑOS_MINIMOS_JUEGO = 5;
    private static final int EDAD_MINIMA_RANA = 5;

    private Monitora lydia;
    private Monitora aisha;
    private Monitora dalsy;
    private Random random;

    public Ludoteca() {
        lydia = new Monitora("Lydia");
        aisha = new Monitora("Aisha");
        dalsy = new Monitora("Dalsy");
        random = new Random();
    }

    public void llegadaNiñoAleatorio() {
        Niño nuevo = crearNiñoAleatorio();
        System.out.println("Llega " + nuevo);
        System.out.println(nuevo.getNombre() + " pasa a la cola de Lydia");
        lydia.agregarNiño(nuevo);
    }

    public void intentoInicioJuego() {
        if (lydia.contarNiños() < NIÑOS_MINIMOS_JUEGO) {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos " + NIÑOS_MINIMOS_JUEGO + " niños");
            return;
        }
        System.out.println("Lydia transfiere sus niños a Aisha");
        mostrarListaNiños(lydia.listarNiños());
        lydia.transferirNiñosA(aisha);
    }

    public void presentacionesGenerales() {
        if (aisha.estaVacia()) {
            System.out.println("Aisha no tiene niños en su cola");
            return;
        }
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (Niño niño : aisha.listarNiños()) {
            niño.presentarse();
        }
    }

    public void presentacionesMayoresDeAleatorio() {
        int edadMinima = 3 + random.nextInt(5);
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años:\n");
        boolean alguienSePresento = false;
        for (Niño niño : aisha.listarNiños()) {
            if (niño.getEdad() > edadMinima) {
                niño.presentarse();
                alguienSePresento = true;
            }
        }
        if (!alguienSePresento) {
            System.out.println("Ningún niño supera esa edad");
        }
    }

    public void presentacionesPorLetraAleatoria() {
        char letra = (char) ('A' + random.nextInt(26));
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        boolean alguienSePresento = false;
        for (Niño niño : aisha.listarNiños()) {
            if (niño.nombreEmpiezaCon(letra)) {
                System.out.println("Hola, soy " + niño.getNombre());
                alguienSePresento = true;
            }
        }
        if (!alguienSePresento) {
            System.out.println("Ningún niño de la cola empieza con esa letra");
        }
    }

    public void primerosCinco() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños para mostrar");
            return;
        }
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        List<Niño> niños = aisha.listarNiños();
        int limite = Math.min(5, niños.size());
        for (int i = 0; i < limite; i++) {
            System.out.println("Hola, soy " + niños.get(i).getNombre());
        }
    }

    public void ultimosCinco() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños para mostrar");
            return;
        }
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        List<Niño> niños = aisha.listarNiños();
        int total = niños.size();
        int inicio = Math.max(0, total - 5);
        for (int i = inicio; i < total; i++) {
            System.out.println("Hola, soy " + niños.get(i).getNombre());
        }
    }

    public void conteoAsistencia() {
        int total = lydia.contarNiños() + aisha.contarNiños() + dalsy.contarNiños();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + lydia.contarNiños() + " niños en cola");
        System.out.println("Aisha tiene " + aisha.contarNiños() + " niños en cola");
        System.out.println("Dalsy tiene " + dalsy.contarNiños() + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }

    public void edadPromedioAisha() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double suma = 0;
        for (Niño niño : aisha.listarNiños()) {
            suma += niño.getEdad();
        }
        double promedio = suma / aisha.contarNiños();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", promedio);
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.contarNiños();
        if (total == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        int mayores = aisha.contarNiñosMayoresOIgualesA(EDAD_MINIMA_RANA);
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de " + EDAD_MINIMA_RANA + " años o más: " + mayores);
        if (mayores * 2 > total) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de " + EDAD_MINIMA_RANA + " años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarParaJuego() {
        if (aisha.estaVacia()) {
            System.out.println("Aisha no tiene niños que separar");
            return;
        }
        System.out.println("Separando niños para el juego de la rana...");
        List<Niño> menores = aisha.retirarNiñosMenoresQue(EDAD_MINIMA_RANA);
        if (menores.isEmpty()) {
            System.out.println("Todos los niños son mayores o iguales a " + EDAD_MINIMA_RANA + " años");
            return;
        }
        System.out.println("Niños menores de " + EDAD_MINIMA_RANA + " años pasan a Dalsy:");
        mostrarListaNiños(menores);
        dalsy.agregarTodos(menores);
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        mostrarListaNiños(aisha.listarNiños());
        System.out.println("\nAl terminar el juego, los niños volverán con Lydia en el orden actual");
    }

    public void alarmaIncendio() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        int total = aisha.contarNiños() + dalsy.contarNiños();
        dalsy.transferirNiñosA(lydia);
        aisha.transferirNiñosA(lydia);
        System.out.println(total + " niños transferidos a Lydia");
        System.out.println("Lydia ahora tiene " + lydia.contarNiños() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================\n");
        mostrarEstadoMonitora(lydia);
        mostrarEstadoMonitora(aisha);
        mostrarEstadoMonitora(dalsy);
        System.out.println("\n========================================");
    }

    private Niño crearNiñoAleatorio() {
        String nombreBase = NOMBRES[random.nextInt(NOMBRES.length)];
        int sufijo = random.nextInt(100);
        int edad = EDAD_MINIMA + random.nextInt(EDAD_MAXIMA - EDAD_MINIMA + 1);
        return new Niño(nombreBase + sufijo, edad);
    }

    private void mostrarEstadoMonitora(Monitora monitora) {
        System.out.println(monitora.getNombre().toUpperCase() + ":");
        monitora.mostrarCola();
        System.out.println();
    }

    private void mostrarListaNiños(List<Niño> niños) {
        if (niños.isEmpty()) {
            System.out.println("(ninguno)");
            return;
        }
        for (Niño niño : niños) {
            System.out.println("- " + niño);
        }
    }
}
