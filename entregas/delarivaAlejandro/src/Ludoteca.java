import java.util.*;

public class Ludoteca {
    private final Monitora lydia = new Monitora("Lydia");
    private final Monitora aisha = new Monitora("Aisha");
    private final Monitora dalsy = new Monitora("Dalsy");

    public Monitora getLydia() { return lydia; }
    public Monitora getAisha() { return aisha; }
    public Monitora getDalsy() { return dalsy; }

    public void llegadaNinio(String nombre, int edad) {
        System.out.println("Llega " + nombre + " (" + edad + " años)");
        System.out.println(nombre + " pasa a la cola de Lydia");
        lydia.encolar(new Ninio(nombre, edad));
    }

    public void intentarInicioJuego() {
        if (lydia.tamanoCola() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            List<Ninio> transferidos = lydia.drenarTodoPreservandoOrden();
            aisha.recibirListaPreservandoOrden(transferidos);
            for (Ninio n : transferidos) System.out.println(n.toString());
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (Ninio n : aisha.verCola()) {
            System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
        }
    }

    public void presentacionesPorEdadMinima(int edadMin) {
        System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:\n");
        for (Ninio n : aisha.verCola()) {
            if (n.getEdad() > edadMin) {
                System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
            }
        }
    }

    public void presentacionesPorInicial(char letra) {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (Ninio n : aisha.verCola()) {
            if (!n.getNombre().isEmpty() && Character.toUpperCase(n.getNombre().charAt(0)) == Character.toUpperCase(letra)) {
                System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre());
            }
        }
    }

    public void primerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        List<Ninio> lista = aisha.verCola();
        int limite = Math.min(5, lista.size());
        for (int i = 0; i < limite; i++) {
            Ninio n = lista.get(i);
            System.out.println((i + 1) + ": Hola, soy " + n.getNombre());
        }
    }

    public void ultimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        List<Ninio> lista = aisha.verCola();
        int inicio = Math.max(0, lista.size() - 5);
        for (int i = inicio; i < lista.size(); i++) {
            Ninio n = lista.get(i);
            System.out.println((i - inicio + 1) + ": Hola, soy " + n.getNombre());
        }
    }

    public void conteoAsistencia() {
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + lydia.tamanoCola() + " niños en cola");
        System.out.println("Aisha tiene " + aisha.tamanoCola() + " niños en cola");
        System.out.println("Dalsy tiene " + dalsy.tamanoCola() + " niños en cola");
        System.out.println("Total: " + (lydia.tamanoCola() + aisha.tamanoCola() + dalsy.tamanoCola()) + " niños");
    }

    public void edadPromedioAisha() {
        if (aisha.tamanoCola() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            double promedio = aisha.edadPromedio();
            System.out.printf(java.util.Locale.US, "Edad promedio de los niños en la cola de Aisha: %.1f años\n", promedio);
        }
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = lydia.tamanoCola() + aisha.tamanoCola() + dalsy.tamanoCola();
        int mayores = contarMayoresOIgualesEnTodas(5);
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
        List<Ninio> original = aisha.verCola();
        List<Ninio> menores = new ArrayList<>();
        List<Ninio> mayores = new ArrayList<>();
        for (Ninio n : original) {
            if (n.getEdad() < 5) menores.add(n); else mayores.add(n);
        }
        if (!menores.isEmpty()) System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (Ninio n : menores) System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
        if (!mayores.isEmpty()) System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (Ninio n : mayores) System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        int antes = lydia.tamanoCola();
        List<Ninio> desdeDalsy = dalsy.drenarTodoPreservandoOrden();
        List<Ninio> desdeAisha = aisha.drenarTodoPreservandoOrden();
        lydia.recibirListaPreservandoOrden(desdeDalsy);
        lydia.recibirListaPreservandoOrden(desdeAisha);
        int transferidos = lydia.tamanoCola() - antes;
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println(transferidos + " niños transferidos\n");
        System.out.println("Lydia ahora tiene " + lydia.tamanoCola() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================\n");
        mostrarMonitora("LYDIA", lydia);
        mostrarMonitora("AISHA", aisha);
        mostrarMonitora("DALSY", dalsy);
        System.out.println("========================================");
    }

    private int contarMayoresOIgualesEnTodas(int edadMin) {
        int total = 0;
        total += (int) lydia.contarMayoresOIguales(edadMin);
        total += (int) aisha.contarMayoresOIguales(edadMin);
        total += (int) dalsy.contarMayoresOIguales(edadMin);
        return total;
    }

    private void mostrarMonitora(String titulo, Monitora m) {
        System.out.println(titulo + ":");
        List<Ninio> lista = m.verCola();
        if (lista.isEmpty()) {
            System.out.println("  Cola vacía\n");
            return;
        }
        System.out.println("  Niños en cola: " + lista.size());
        for (Ninio n : lista) System.out.println("  - " + n.getNombre() + " (" + n.getEdad() + " años)");
        System.out.println();
    }
}
