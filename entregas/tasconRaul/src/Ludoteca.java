public class Ludoteca {
    private Monitora aisha;
    private Monitora lydia;
    private Monitora dalsy;

    public Ludoteca() {
        aisha = new Monitora("Aisha");
        lydia = new Monitora("Lydia");
        dalsy = new Monitora("Dalsy");
    }

    
    public void llegadaNino(Console console) {
    String nombre = console.readString("Nombre del niño: ");
    int edad = console.readInt("Edad del niño: ");
    Nino nino = new Nino(nombre, edad);
    System.out.println("Llega " + nombre + " (" + edad + " años)");
    lydia.agregarNino(nino);
    System.out.println(nombre + " pasa a la cola de Lydia");
    }

    
    public void inicioJuego() {
        if (lydia.getCantidad() < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        } else {
            System.out.println("Lydia transfiere sus niños a Aisha");
            lydia.transferirTodosA(aisha);
            aisha.mostrarCola();
        }
    }

    
    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        Nino[] ninos = aisha.obtenerNinos();
        for (int i = 0; i < ninos.length; i++) {
            Nino nino = ninos[i];
            System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre() + " y tengo " + nino.getEdad() + " años");
        }
    }

    
    public void presentacionesPorEdad(Console console) {
        int edadMin = console.readInt("Edad mínima: ");
        System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:");
        Nino[] ninos = aisha.obtenerNinos();
        for (int i = 0; i < ninos.length; i++) {
            Nino nino = ninos[i];
            if (nino.getEdad() > edadMin) {
                System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre() + " y tengo " + nino.getEdad() + " años");
            }
        }
    }

    
    public void presentacionesPorInicial(Console console) {
        String letra = console.readString("Letra inicial: ");
        if (letra.length() == 0) return;
        char inicial = letra.charAt(0);
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + inicial + "':");
        Nino[] ninos = aisha.obtenerNinos();
        for (int i = 0; i < ninos.length; i++) {
            Nino nino = ninos[i];
            if (nino.getNombre().charAt(0) == inicial) {
                System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre());
            }
        }
    }

    
    public void presentarPrimerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        Nino[] ninos = aisha.obtenerNinos();
        for (int i = 0; i < ninos.length && i < 5; i++) {
            Nino nino = ninos[i];
            System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre());
        }
    }

    
    public void presentarUltimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        Nino[] ninos = aisha.obtenerNinos();
        int start = ninos.length > 5 ? ninos.length - 5 : 0;
        for (int i = start; i < ninos.length; i++) {
            Nino nino = ninos[i];
            System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre());
        }
    }

    
    public void conteoAsistencia() {
    int x = lydia.getCantidad();
    int y = aisha.getCantidad();
    int z = dalsy.getCantidad();
    System.out.println("CONTEO DE ASISTENCIA:");
    System.out.println("Lydia tiene " + x + " niños en cola");
    System.out.println("Aisha tiene " + y + " niños en cola");
    System.out.println("Dalsy tiene " + z + " niños en cola");
    System.out.println("Total: " + (x + y + z) + " niños");
    }

    
    public void edadPromedio() {
        if (aisha.getCantidad() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        int suma = 0;
        Nino[] ninos = aisha.obtenerNinos();
        for (int i = 0; i < ninos.length; i++) {
            suma += ninos[i].getEdad();
        }
        double promedio = (double) suma / aisha.getCantidad();
        System.out.println("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", promedio) + " años");
    }

    
    public void intentoJuegoRana() {
        int total = aisha.getCantidad();
        int mayores = 0;
        Nino[] ninos = aisha.obtenerNinos();
        for (int i = 0; i < ninos.length; i++) {
            if (ninos[i].getEdad() >= 5) mayores++;
        }
        System.out.println("Verificando condiciones para el juego de la rana...");
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

    
    public void separarParaJuegoRana() {
        System.out.println("Separando niños para el juego de la rana...");
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        Nino[] ninos = aisha.obtenerNinos();
        Monitora tempAisha = new Monitora("Aisha");
        for (int i = 0; i < ninos.length; i++) {
            Nino nino = ninos[i];
            if (nino.getEdad() < 5) {
                dalsy.agregarNino(nino);
                System.out.println("- " + nino.getNombre() + " (" + nino.getEdad() + " años)");
            } else {
                tempAisha.agregarNino(nino);
            }
        }
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        tempAisha.mostrarCola();
    }

    
    public void alarmaIncendios() {
    System.out.println("¡ALARMA CONTRA INCENDIOS!\n");
    System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
    int transferidos = 0;
    transferidos += dalsy.getCantidad();
    dalsy.transferirTodosA(lydia);
    transferidos += aisha.getCantidad();
    aisha.transferirTodosA(lydia);
    System.out.println(transferidos + " niños transferidos");
    System.out.println("\nLydia ahora tiene " + lydia.getCantidad() + " niños listos para evacuar en orden");
    }

    
    public void mostrarEstado() {
    System.out.println("========================================");
    System.out.println("        ESTADO ACTUAL");
    System.out.println("========================================");
    System.out.println("LYDIA:");
    lydia.mostrarCola();
    System.out.println("\nAISHA:");
    aisha.mostrarCola();
    System.out.println("\nDALSY:");
    dalsy.mostrarCola();
    System.out.println("========================================");
    }
}
