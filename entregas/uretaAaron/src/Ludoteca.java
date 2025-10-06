public class Ludoteca {
    private final Monitor lydia;
    private final Monitor aisha;
    private final Monitor dalsy;

    private Nino[] copiaAishaAntesSeparacion;

    public Ludoteca() {
        this.lydia = new Monitor("Lydia", 50);
        this.aisha = new Monitor("Aisha", 50);
        this.dalsy = new Monitor("Dalsy", 50);
        this.copiaAishaAntesSeparacion = null;
    }

    public void llegadaDeNino(String nombre, int edad) {
        Nino n = new Nino(nombre, edad);
        System.out.println("Llega " + n.getNombre() + " (" + n.getEdad() + " años)");
        if (lydia.recibir(n)) {
            System.out.println(n.getNombre() + " pasa a la cola de Lydia");
        } else {
            System.out.println("Cola de Lydia llena. " + n.getNombre() + " no puede entrar.");
        }
    }

    public void intentoInicioDeJuego() {
        int total = lydia.tamanoCola() + aisha.tamanoCola();
        if (total >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            StringBuilder sb = new StringBuilder();
            int mov = lydia.transferirTodosA(aisha, sb);
            if (mov == 0) {
                System.out.println("(No había niños en Lydia)");
            } else {
                System.out.print(sb.toString());
            }
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionesGeneralesAisha() {
        aisha.presentacionesGenerales();
    }

    public void presentacionesPorEdadMinima(int edadMin) {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        aisha.presentacionesPorEdadMinima(edadMin);
    }

    public void presentacionesPorInicial(char letra) {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        aisha.presentacionesPorInicial(letra);
    }

    public void primerosCinco() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        aisha.primerosCinco();
    }

    public void ultimosCinco() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        aisha.ultimosCinco();
    }

    public void conteoAsistencia() {
        int x = lydia.tamanoCola();
        int y = aisha.tamanoCola();
        int z = dalsy.tamanoCola();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + x + " niños en cola");
        System.out.println("Aisha tiene " + y + " niños en cola");
        System.out.println("Dalsy tiene " + z + " niños en cola");
        System.out.println("Total: " + (x + y + z) + " niños");
    }

    public void edadPromedioAisha() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double prom = aisha.edadPromedio();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", prom);
    }

    public void intentoJuegoDeLaRana() {
        int total = aisha.tamanoCola();
        System.out.println("Verificando condiciones para el juego de la rana...");
        System.out.println("Total de niños: " + total);
        int mayores = aisha.contarMayoresOIgualA(5);
        System.out.println("Niños de 5 años o más: " + mayores);
        if (total > 0 && mayores > total / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarMenoresAJuegoRana() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Separando niños para el juego de la rana...");
        copiaAishaAntesSeparacion = aisha.snapshot();

        aisha.restaurarDesde(new Nino[0]);
        for (Nino n : copiaAishaAntesSeparacion) {
            if (n == null) continue;
            if (n.getEdad() < 5) dalsy.recibir(n);
            else aisha.recibir(n);
        }

        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        boolean algunoMenor = false;
        for (Nino n : copiaAishaAntesSeparacion) {
            if (n != null && n.getEdad() < 5) {
                System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                algunoMenor = true;
            }
        }
        if (!algunoMenor) System.out.println("(No había menores de 5 años)");

        System.out.println();
        System.out.println("Niños que se quedan con Aisha para jugar:");
        boolean algunoMayor = false;
        Nino[] snap = aisha.snapshot();
        for (Nino n : snap) {
            if (n != null) {
                System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                algunoMayor = true;
            }
        }
        if (!algunoMayor) System.out.println("(Nadie quedó con Aisha)");

        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        StringBuilder sb = new StringBuilder();

        int mov1 = dalsy.transferirTodosA(lydia, sb);
        int mov2 = aisha.transferirTodosA(lydia, sb);
        int total = mov1 + mov2;
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println(total + " niños transferidos\n");
        System.out.println(sb.toString());
        System.out.println("Lydia ahora tiene " + lydia.tamanoCola() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        lydia.listarTodosDetallado("LYDIA:");
        aisha.listarTodosDetallado("\nAISHA:");
        dalsy.listarTodosDetallado("\nDALSY:");
        System.out.println("\n========================================");
    }
}
