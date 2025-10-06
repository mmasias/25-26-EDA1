public class Ludoteca {
    private Monitor monitoraLydia;
    private Monitor monitoraAisha;
    private Monitor monitoraDalsy;
    private Pizarra pizarraLudoteca;

    private static final int CANTIDAD_MINIMA_JUEGO = 5;
    private static final int EDAD_MINIMA_JUEGO = 5;

    public Ludoteca() {
        monitoraLydia = new Monitor("Lydia");
        monitoraAisha = new Monitor("Aisha");
        monitoraDalsy = new Monitor("Dalsy");
        pizarraLudoteca = new Pizarra();
    }

    public void registrarLlegadaNino(String nombre, int edad) {
        Nino nuevoNino = new Nino(nombre, edad);
        System.out.println("Llega " + nombre + " (" + edad + " anos)");
        System.out.println(nombre + " pasa a la cola de Lydia");
        monitoraLydia.agregarNinoACola(nuevoNino);
    }

    public void intentarIniciarJuego() {
        if (monitoraLydia.obtenerCantidadNinos() >= CANTIDAD_MINIMA_JUEGO) {
            System.out.println("Lydia transfiere sus ninos a Aisha");
            Nino[] ninosTransferidos = monitoraLydia.transferirTodosLosNinos();
            for (int i = 0; i < ninosTransferidos.length; i++) {
                System.out.println("- " + ninosTransferidos[i].getNombre());
                monitoraAisha.agregarNinoACola(ninosTransferidos[i]);
            }
        } else {
            System.out.println("No hay suficientes ninos para iniciar el juego");
            System.out.println("Se necesitan al menos " + CANTIDAD_MINIMA_JUEGO + " ninos");
        }
    }

    public void realizarPresentacionGeneral() {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos en su grupo");
            return;
        }
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (int i = 0; i < listaNinos.length; i++) {
            System.out.println(listaNinos[i].getNombre() + ": Hola, soy " + listaNinos[i].getNombre() + " y tengo " + listaNinos[i].getEdad() + " anos");
        }
    }

    public void presentarMayoresDeEdad(int edadMinima) {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos en su grupo");
            return;
        }
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " anos:");
        for (int i = 0; i < listaNinos.length; i++) {
            if (listaNinos[i].getEdad() > edadMinima) {
                System.out.println(listaNinos[i].getNombre() + ": Hola, soy " + listaNinos[i].getNombre() + " y tengo " + listaNinos[i].getEdad() + " anos");
            }
        }
    }

    public void presentarPorInicial(String letraInicial) {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos en su grupo");
            return;
        }
        System.out.println("Aisha pide que se presenten los ninos cuyo nombre empieza con '" + letraInicial + "':");
        for (int i = 0; i < listaNinos.length; i++) {
            if (listaNinos[i].getNombre().toUpperCase().startsWith(letraInicial.toUpperCase())) {
                System.out.println(listaNinos[i].getNombre() + ": Hola, soy " + listaNinos[i].getNombre());
            }
        }
    }

    public void presentarPrimerosCincoNinos() {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos");
            return;
        }
        int limite = listaNinos.length < 5 ? listaNinos.length : 5;
        System.out.println("Aisha pide que se presenten los primeros 5 ninos:");
        for (int i = 0; i < limite; i++) {
            System.out.println(listaNinos[i].getNombre() + ": Hola, soy " + listaNinos[i].getNombre());
        }
    }

    public void presentarUltimosCincoNinos() {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos");
            return;
        }
        int inicio = listaNinos.length - 5;
        if (inicio < 0) inicio = 0;
        System.out.println("Aisha pide que se presenten los ultimos 5 ninos:");
        for (int i = inicio; i < listaNinos.length; i++) {
            System.out.println(listaNinos[i].getNombre() + ": Hola, soy " + listaNinos[i].getNombre());
        }
    }

    public void mostrarConteoAsistencia() {
        int cantidadLydia = monitoraLydia.obtenerCantidadNinos();
        int cantidadAisha = monitoraAisha.obtenerCantidadNinos();
        int cantidadDalsy = monitoraDalsy.obtenerCantidadNinos();
        int total = cantidadLydia + cantidadAisha + cantidadDalsy;
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + cantidadLydia + " ninos en cola");
        System.out.println("Aisha tiene " + cantidadAisha + " ninos en cola");
        System.out.println("Dalsy tiene " + cantidadDalsy + " ninos en cola");
        System.out.println("Total: " + total + " ninos");
    }

    public void mostrarEdadPromedio() {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("No hay ninos en la cola de Aisha");
            return;
        }
        double suma = 0;
        for (int i = 0; i < listaNinos.length; i++) suma += listaNinos[i].getEdad();
        double promedio = suma / listaNinos.length;
        System.out.printf("Edad promedio de los ninos en la cola de Aisha: %.2f anos%n", promedio);
    }

    public void verificarCondicionesJuegoRana() {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos en cola");
            return;
        }
        int total = listaNinos.length;
        int mayores = 0;
        for (int i = 0; i < listaNinos.length; i++) if (listaNinos[i].getEdad() >= EDAD_MINIMA_JUEGO) mayores++;
        System.out.println("Verificando condiciones para el juego de la rana...");
        System.out.println("Total de ninos: " + total);
        System.out.println("Ninos de 5 anos o mas: " + mayores);
        if (mayores > total / 2) {
            System.out.println("Mas de la mitad cumplen la condicion");
            System.out.println("Pueden jugar al juego de la rana");
        } else {
            System.out.println("No hay suficientes ninos mayores de 5 anos");
            System.out.println("No pueden jugar todavia");
        }
    }

    public void separarNinosParaJuego() {
        Nino[] listaNinos = monitoraAisha.obtenerListaNinos();
        if (listaNinos.length == 0) {
            System.out.println("Aisha no tiene ninos");
            return;
        }
        System.out.println("Separando ninos para el juego de la rana...");
        Nino[] listaMenores = new Nino[listaNinos.length];
        Nino[] listaMayores = new Nino[listaNinos.length];
        int indiceMenores = 0;
        int indiceMayores = 0;
        for (int i = 0; i < listaNinos.length; i++) {
            if (listaNinos[i].getEdad() < EDAD_MINIMA_JUEGO) {
                listaMenores[indiceMenores++] = listaNinos[i];
                monitoraDalsy.agregarNinoACola(listaNinos[i]);
            } else {
                listaMayores[indiceMayores++] = listaNinos[i];
            }
        }
        System.out.println("Ninos menores de 5 anos pasan a Dalsy:");
        for (int i = 0; i < indiceMenores; i++) {
            System.out.println("- " + listaMenores[i].getNombre() + " (" + listaMenores[i].getEdad() + " anos)");
        }
        System.out.println("Ninos que se quedan con Aisha para jugar:");
        for (int i = 0; i < indiceMayores; i++) {
            System.out.println("- " + listaMayores[i].getNombre() + " (" + listaMayores[i].getEdad() + " anos)");
        }
        Nino[] nuevosAisha = new Nino[indiceMayores];
        for (int i = 0; i < indiceMayores; i++) nuevosAisha[i] = listaMayores[i];
        monitoraAisha.reemplazarNinos(nuevosAisha);
    }

    public void activarProtocoloEmergencia() {
        System.out.println("ALARMA CONTRA INCENDIOS");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        Nino[] transferidosDalsy = monitoraDalsy.transferirTodosLosNinos();
        Nino[] transferidosAisha = monitoraAisha.transferirTodosLosNinos();
        int totalTransferidos = transferidosDalsy.length + transferidosAisha.length;
        for (int i = 0; i < transferidosDalsy.length; i++) monitoraLydia.agregarNinoACola(transferidosDalsy[i]);
        for (int i = 0; i < transferidosAisha.length; i++) monitoraLydia.agregarNinoACola(transferidosAisha[i]);
        System.out.println(totalTransferidos + " ninos transferidos");
        System.out.println("Lydia ahora tiene " + monitoraLydia.obtenerCantidadNinos() + " ninos listos para evacuar en orden");
    }

    public void mostrarEstadoGeneral() {
        pizarraLudoteca.mostrarEstadoMonitores(monitoraLydia, monitoraAisha, monitoraDalsy);
    }
}
