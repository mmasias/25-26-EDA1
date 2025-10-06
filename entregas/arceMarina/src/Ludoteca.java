class Ludoteca {
    private Monitor lydia, aisha, dalsy;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public void recibirNiño(Niño n) { lydia.recibeNiño(n); }

    public boolean intentarJuego() {
        if (lydia.puedeJugar()) {
            lydia.transferirNiños(aisha);
            System.out.println("Lydia transfiere sus niños a Aisha");
            for (Niño n : aisha.getTodosNiños()) System.out.println("- " + n.getNombre());
            return true;
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
            return false;
        }
    }

    public void presentacionesGenerales() { aisha.presentacionesGenerales(); }
    public void presentacionesEdadMin(int edad) { aisha.presentacionesEdadMin(edad); }
    public void presentacionesInicial(char letra) { aisha.presentacionesInicial(letra); }
    public void primerosCinco() { aisha.primerosCinco(); }
    public void ultimosCinco() { aisha.ultimosCinco(); }

    public void conteoAsistencia() {
        int total = lydia.numeroNiños() + aisha.numeroNiños() + dalsy.numeroNiños();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + lydia.numeroNiños() + " niños en cola");
        System.out.println("Aisha tiene " + aisha.numeroNiños() + " niños en cola");
        System.out.println("Dalsy tiene " + dalsy.numeroNiños() + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }

    public void edadPromedio() {
        if (aisha.numeroNiños() == 0) System.out.println("No hay niños en la cola de Aisha");
        else System.out.println("Edad promedio de los niños en la cola de Aisha: " + aisha.promedioEdad());
    }

    public void juegoRana() {
        Niño[] todos = aisha.getTodosNiños();
        int total = todos.length;
        int mayores5 = 0;
        for (Niño n : todos) if (n.getEdad() >= 5) mayores5++;
        System.out.println("Verificando condiciones para el juego de la rana...");
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores5);
        if (mayores5 > total / 2) System.out.println("¡Más de la mitad cumplen la condición!\n¡Pueden jugar al juego de la rana!");
        else System.out.println("No hay suficientes niños mayores de 5 años\nNo pueden jugar todavía");
    }

    public void separarParaRana() {
        System.out.println("Separando niños para el juego de la rana...");
        Niño[] todos = aisha.getTodosNiños();
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (Niño n : todos) if (n.getEdad() < 5) { dalsy.recibeNiño(n); System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)"); }
        System.out.println("Niños que se quedan con Aisha para jugar:");
        for (Niño n : todos) if (n.getEdad() >= 5) System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
    }

    public void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        int transferidos = 0;
        lydia.transferirNiños(lydia); // vacía Dalsy y Aisha hacia Lydia
        aisha.transferirNiños(lydia);
        dalsy.transferirNiños(lydia);
        System.out.println("Todos los niños transferidos a Lydia");
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        mostrarMonitor(lydia);
        mostrarMonitor(aisha);
        mostrarMonitor(dalsy);
        System.out.println("========================================");
    }

    private void mostrarMonitor(Monitor m) {
        if (m.numeroNiños() == 0) System.out.println(m.nombre + ":\nCola vacía");
        else {
            System.out.println(m.nombre + ":\nNiños en cola: " + m.numeroNiños());
            for (Niño n : m.getTodosNiños()) System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
        }
    }
}
