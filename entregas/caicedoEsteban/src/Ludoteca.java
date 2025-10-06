public class Ludoteca {
    private final Monitor lydia;
    private final Monitor aisha;
    private final Monitor dalsy;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public void recibirNiño(Niño niño) {
        System.out.println("Llega " + niño.getNombre() + " (" + niño.getEdad() + " años)");
        System.out.println(niño.getNombre() + " pasa a la cola de Lydia");
        lydia.recibeNiño(niño);
    }

    public void intentarInicioDeJuego() {
        if (lydia.cantidadNiños() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            lydia.entregarTodos(aisha);
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionGeneral() {
        if (aisha.cantidadNiños() == 0) {
            System.out.println("No hay niños con Aisha para presentarse.");
            return;
        }
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        aisha.presentacionGeneral();
    }

    public void presentacionMayoresDe5() {
        aisha.presentacionMayores(5);
    }

    public void presentacionPorLetra(char letra) {
        aisha.presentacionPorInicial(letra);
    }

    public void presentacionPrimerosCinco() {
        aisha.presentacionPrimeros(5);
    }

    public void presentacionUltimosCinco() {
        aisha.presentacionUltimos(5);
    }

    public void mostrarCantidadColas() {
        int total = lydia.cantidadNiños() + aisha.cantidadNiños() + dalsy.cantidadNiños();
        System.out.println("""
                CONTEO DE ASISTENCIA:
                Lydia tiene """ + lydia.cantidadNiños() + " niños en cola\n" +
                "Aisha tiene " + aisha.cantidadNiños() + " niños en cola\n" +
                "Dalsy tiene " + dalsy.cantidadNiños() + " niños en cola\n" +
                "Total: " + total + " niños");
    }

    public void mostrarEdadPromedio() {
        if (aisha.cantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double promedio = aisha.edadPromedio();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.2f años%n", promedio);
    }

    public void simularJuegoDeLaRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.cantidadNiños();
        int mayores5 = aisha.contarMayoresDe5();
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores5);

        if (mayores5 > total / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void pasarMenoresADalsy() {
        System.out.println("Separando niños para el juego de la rana...");
        aisha.separarMenoresDe5(dalsy);
    }

    public void alarmaIncendios() {
        System.out.println("""
                ¡ALARMA CONTRA INCENDIOS!

                PROTOCOLO DE EMERGENCIA ACTIVADO""");
        dalsy.entregarTodos(lydia);
        aisha.entregarTodos(lydia);
        int totalTransferidos = lydia.cantidadNiños();
        System.out.println(totalTransferidos + " niños transferidos");
        System.out.println("Lydia ahora tiene " + totalTransferidos + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        System.out.println("""
                ========================================
                        ESTADO ACTUAL
                ========================================""");
        lydia.mostrarCola();
        aisha.mostrarCola();
        dalsy.mostrarCola();
        System.out.println("========================================");
    }
}
