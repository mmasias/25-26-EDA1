
import java.util.*;

public class Ludoteca {
    private Monitora lydia;
    private Monitora aisha;
    private Monitora dalsy;
    private Random random = new Random();

    public Ludoteca() {
        lydia = new Monitora("Lydia");
        aisha = new Monitora("Aisha");
        dalsy = new Monitora("Dalsy");
    }

    public void llegadaNiñoAleatorio() {
        String[] nombres = {"Lucas", "Sofía", "Diego", "Valeria", "Mateo", "Emma", "Noah", "Liam", "Julia", "Ariana"};
        String nombre = nombres[random.nextInt(nombres.length)] + random.nextInt(100);
        int edad = 2 + random.nextInt(9); // entre 2 y 10 años

        Niño n = new Niño(nombre, edad);
        System.out.println("Llega " + n);
        System.out.println(n.getNombre() + " pasa a la cola de Lydia");
        lydia.agregarNiño(n);
    }

    public void intentoInicioJuego() {
        if (lydia.getCola().size() < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
            return;
        }
        System.out.println("Lydia transfiere sus niños a Aisha");
        for (Niño n : lydia.getCola()) System.out.println("- " + n);
        lydia.transferirNiñosA(aisha);
    }

    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (Niño n : aisha.getCola()) n.presentarse();
    }

    public void presentacionesMayoresDeAleatorio() {
        int edad = 3 + random.nextInt(5); // entre 3 y 7
        System.out.println("Aisha pide que se presenten los mayores de " + edad + " años:\n");
        for (Niño n : aisha.getCola()) {
            if (n.getEdad() > edad) n.presentarse();
        }
    }

    public void presentacionesPorLetraAleatoria() {
        char letra = (char) ('A' + random.nextInt(26));
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (Niño n : aisha.getCola()) {
            if (Character.toUpperCase(n.getNombre().charAt(0)) == letra) {
                System.out.println("Hola, soy " + n.getNombre());
            }
        }
    }

    public void primerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        int limite = Math.min(5, aisha.getCola().size());
        for (int i = 0; i < limite; i++) {
            System.out.println("Hola, soy " + aisha.getCola().get(i).getNombre());
        }
    }

    public void ultimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        int total = aisha.getCola().size();
        int inicio = Math.max(0, total - 5);
        for (int i = inicio; i < total; i++) {
            System.out.println("Hola, soy " + aisha.getCola().get(i).getNombre());
        }
    }

    public void conteoAsistencia() {
        int total = lydia.getCola().size() + aisha.getCola().size() + dalsy.getCola().size();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + lydia.getCola().size() + " niños en cola");
        System.out.println("Aisha tiene " + aisha.getCola().size() + " niños en cola");
        System.out.println("Dalsy tiene " + dalsy.getCola().size() + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }

    public void edadPromedioAisha() {
        if (aisha.getCola().isEmpty()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double suma = 0;
        for (Niño n : aisha.getCola()) suma += n.getEdad();
        double promedio = suma / aisha.getCola().size();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", promedio);
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.getCola().size();
        if (total == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        int mayores = 0;
        for (Niño n : aisha.getCola()) if (n.getEdad() >= 5) mayores++;
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores);
        if (mayores > total / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarParaJuego() {
        System.out.println("Separando niños para el juego de la rana...");
        LinkedList<Niño> menores = new LinkedList<>();
        LinkedList<Niño> mayores = new LinkedList<>();
        for (Niño n : aisha.getCola()) {
            if (n.getEdad() < 5) menores.add(n);
            else mayores.add(n);
        }
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (Niño n : menores) System.out.println("- " + n);
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (Niño n : mayores) System.out.println("- " + n);

        dalsy.getCola().addAll(menores);
        aisha.getCola().clear();
        aisha.getCola().addAll(mayores);

        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public void alarmaIncendio() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        int total = dalsy.getCola().size() + aisha.getCola().size();
        dalsy.transferirNiñosA(lydia);
        aisha.transferirNiñosA(lydia);
        System.out.println(total + " niños transferidos");
        System.out.println("Lydia ahora tiene " + lydia.getCola().size() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================\n");
        System.out.println("LYDIA:");
        lydia.mostrarCola();
        System.out.println("\nAISHA:");
        aisha.mostrarCola();
        System.out.println("\nDALSY:");
        dalsy.mostrarCola();
        System.out.println("\n========================================");
    }
}
