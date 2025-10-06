

class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;
    private Pizarra pizarraDelSalon;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
        pizarraDelSalon = new Pizarra();
    }

    public void simularLlegadaNiño() {
        Console console = new Console();
        String nombre = console.readString("Nombre del niño: ");
        int edad = console.readInt("Edad: ");
        Nino n = new Nino(nombre, edad);
        console.writeln("Llega " + nombre + " (" + edad + " años)");
        console.writeln(nombre + " pasa a la cola de Lydia");
        lydia.recibeNiño(n);
    }

    public void simularInicioJuego() {
        if (lydia.numeroNiños() >= 5) {
            new Console().writeln("Lydia transfiere sus niños a Aisha");
            lydia.entregaNiños(aisha);
        } else {
            new Console().writeln("No hay suficientes niños para iniciar el juego");
            new Console().writeln("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionGeneral() {
        Console c = new Console();
        c.writeln("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        Nino[] lista = aisha.getNiños();
        if (lista.length == 0) {
            c.writeln("No hay niños en la cola de Aisha");
            return;
        }
        for (int i = 0; i < lista.length; i++) {
            c.writeln(lista[i].presentarse());
        }
    }

    public void presentarMayoresDe(int edadMin) {
        Console console = new Console();
        console.writeln("Aisha pide que se presenten los mayores de " + edadMin + " años:\n");
        Nino[] lista = aisha.getNiños();
        boolean found = false;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getEdad() > edadMin) {
                console.writeln(lista[i].presentarse());
                found = true;
            }
        }
        if (!found) console.writeln("No hay niños que cumplan la condición.");
    }

    public void presentarPorInicial(char letra) {
        Console console = new Console();
        console.writeln("Aisha pide que se presenten los niños cuyo nombre empieza por '" + letra + "':\n");
        Nino[] lista = aisha.getNiños();
        boolean found = false;
        for (int i = 0; i < lista.length; i++) {
            String nombre = lista[i].getNombre();
            if (!nombre.isEmpty() && Character.toUpperCase(nombre.charAt(0)) == Character.toUpperCase(letra)) {
                console.writeln(lista[i].presentarse());
                found = true;
            }
        }
        if (!found) console.writeln("No hay niños que empiecen por esa letra.");
    }

    public void presentarPrimerosCinco() {
        Console console = new Console();
        Nino[] lista = aisha.getNiños();
        console.writeln("Aisha pide que se presenten los cinco primeros niños:\n");
        for (int i = 0; i < lista.length && i < 5; i++) {
            console.writeln(lista[i].presentarse());
        }
        if (lista.length == 0) console.writeln("No hay niños en la cola de Aisha.");
    }

    public void presentarUltimosCinco() {
        Console console = new Console();
        Nino[] lista = aisha.getNiños();
        int inicio = Math.max(0, lista.length - 5);
        console.writeln("Aisha pide que se presenten los cinco últimos niños:\n");
        for (int i = inicio; i < lista.length; i++) {
            console.writeln(lista[i].presentarse());
        }
        if (lista.length == 0) console.writeln("No hay niños en la cola de Aisha.");
    }

    public void mostrarCantidadNiños() {
        Console console = new Console();
        console.writeln("Lydia tiene " + lydia.numeroNiños() + " niños en cola");
        console.writeln("Aisha tiene " + aisha.numeroNiños() + " niños en cola");
        console.writeln("Dalsy tiene " + dalsy.numeroNiños() + " niños en cola");
        console.writeln("Total de niños en la ludoteca: " + (lydia.numeroNiños() + aisha.numeroNiños() + dalsy.numeroNiños()));
    }

    public void mostrarEdadPromedio() {
        Console console = new Console();
        Nino[] lista = aisha.getNiños();
        if (lista.length == 0) {
            console.writeln("No hay niños en cola");
            return;
        }
        int suma = 0;
        for (int i = 0; i < lista.length; i++) {
            suma += lista[i].getEdad();
        }
        double promedio = (double) suma / lista.length;
        console.writeln("Edad promedio: " + String.format("%.2f", promedio));
    }

    public void simularJuegoRana() {
        Console console = new Console();
        console.writeln("Verificando condiciones para el juego de la rana...");
        Nino[] lista = aisha.getNiños();
        console.writeln("Total de niños: " + lista.length);
        int mayores5 = 0;
        for (Nino n : lista) if (n.getEdad() >= 5) mayores5++;
        console.writeln("Niños de 5 años o más: " + mayores5);
        if (lista.length > 0 && mayores5 > lista.length / 2) {
            console.writeln("¡Más de la mitad cumplen la condición!");
            console.writeln("¡Pueden jugar al juego de la rana!");
        } else {
            console.writeln("No hay suficientes niños mayores de 5 años");
            console.writeln("No pueden jugar todavía");
        }
    }

    public void pasarMenoresDe5() {
        Console console = new Console();
        Nino[] lista = aisha.getNiños();
        if (lista.length == 0) {
            console.writeln("No hay niños en la cola de Aisha para separar");
            return;
        }

        int countMenores = 0;
        for (Nino n : lista) if (n.getEdad() < 5) countMenores++;
        int countMayores = lista.length - countMenores;

        Nino[] menores = new Nino[countMenores];
        Nino[] mayores = new Nino[countMayores];

        int im = 0, iM = 0;
        for (Nino n : lista) {
            if (n.getEdad() < 5) menores[im++] = n;
            else mayores[iM++] = n;
        }

        console.writeln("Niños menores de 5 años pasan a Dalsy:");
        if (menores.length == 0) console.writeln("- (No hay niños menores de 5 años)");
        else {
            for (Nino n : menores) {
                console.writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                dalsy.recibeNiño(n);
            }
        }

        console.writeln("\nNiños que se quedan con Aisha para jugar:");
        if (mayores.length == 0) console.writeln("- (No hay niños mayores o iguales a 5 años)");
        else for (Nino n : mayores) console.writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
    }

    public void protocoloEmergencia() {
        Console console = new Console();
        console.writeln("¡ALARMA CONTRA INCENDIOS!");
        console.writeln("PROTOCOLO DE EMERGENCIA ACTIVADO\n");

        int totalTransferidos = 0;

        Nino[] ninosDalsy = dalsy.getNiños();
        if (ninosDalsy.length > 0) {
            console.writeln("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
            for (Nino n : ninosDalsy) {
                lydia.recibeNiño(n);
                totalTransferidos++;
            }
            dalsy.vaciar();
        } else {
            console.writeln("Dalsy no tiene niños que transferir");
        }

        Nino[] ninosAisha = aisha.getNiños();
        if (ninosAisha.length > 0) {
            console.writeln("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
            for (Nino n : ninosAisha) {
                lydia.recibeNiño(n);
                totalTransferidos++;
            }
            aisha.vaciar();
        } else {
            console.writeln("Aisha no tiene niños que transferir");
        }

        console.writeln("\n" + totalTransferidos + " niños transferidos");
        console.writeln("Lydia ahora tiene " + lydia.numeroNiños() + " niños listos para evacuar en orden");
    }

    public void mostrarMonitoresYNiños() {
        lydia.mostrarListaNiños();
        aisha.mostrarListaNiños();
        dalsy.mostrarListaNiños();
    }

    public void usarPizarra() {
        pizarraDelSalon.mostrarTitulo("PIZARRA DEL SALON");
        pizarraDelSalon.mostrarMensaje("Estado actual mostrado en pizarra");
    }

    // accesores para otras clases
    public Monitor getLydia() { return lydia; }
    public Monitor getAisha() { return aisha; }
    public Monitor getDalsy() { return dalsy; }
    public Pizarra getPizarra() { return pizarraDelSalon; }
}
