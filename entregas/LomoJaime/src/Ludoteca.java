import utils.Console;

class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public void recibirNiño(Niño niño) {
        lydia.recibeNiño(niño);
        new Console().writeln(niño.getNombre() + " pasa a la cola de Lydia");
    }

    public void intentarInicioJuego() {
        if (lydia.puedeJugar()) {
            new Console().writeln("Lydia transfiere sus niños a Aisha");
            lydia.entregaNiños(aisha);
            new Console().writeln("[" + aisha.getCantidadNiños() + " niños transferidos]");
            aisha.mostrarListaNiños();
        } else {
            new Console().writeln("No hay suficientes niños para iniciar el juego");
            new Console().writeln("Se necesitan al menos 5 niños en la cola de Lydia");
        }
    }

    public void presentarATodos() {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños para presentar");
            return;
        }
        aisha.presentarATodos();
    }

    public void presentarMayoresDe(int edad) {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños");
            return;
        }
        aisha.presentarMayoresDe(edad);
    }

    public void presentarNombresConLetra(char letra) {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños");
            return;
        }
        aisha.presentarNombresConLetra(letra);
    }

    public void presentarPrimeros() {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños");
            return;
        }
        aisha.presentarPrimeros(5);
    }

    public void presentarUltimos() {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños");
            return;
        }
        aisha.presentarUltimos(5);
    }

    public void mostrarConteo() {
        int l = lydia.getCantidadNiños();
        int a = aisha.getCantidadNiños();
        int d = dalsy.getCantidadNiños();
        new Console().writeln("CONTEO DE ASISTENCIA:");
        new Console().writeln("Lydia tiene " + l + " niños en cola (esperando juego)");
        new Console().writeln("Aisha tiene " + a + " niños en cola");
        new Console().writeln("Dalsy tiene " + d + " niños en cola");
        new Console().writeln("Total: " + (l + a + d) + " niños");
    }

    public void mostrarEdadPromedio() {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("No hay niños en la cola de Aisha");
        } else {
            double prom = aisha.getEdadPromedio();
            new Console().writeln("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", prom) + " años");
        }
    }

    public void intentarJuegoRana() {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños");
            return;
        }
        aisha.mostrarInfoRana();
    }

    public void separarParaJuegoRana() {
        if (aisha.getCantidadNiños() == 0) {
            new Console().writeln("Aisha no tiene niños");
            return;
        }
        aisha.moverMenoresADalsy(dalsy);
    }

    public void activarAlarmaIncendios() {
        new Console().writeln("\n¡ALARMA CONTRA INCENDIOS!\n");
        new Console().writeln("PROTOCOLO DE EMERGENCIA ACTIVADO\n");

        Niño[] deDalsy = dalsy.transferirTodos();
        Niño[] deAisha = aisha.transferirTodos();
        Niño[] deLydia = lydia.transferirTodos();

        int total = deDalsy.length + deAisha.length + deLydia.length;
        new Console().writeln("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        new Console().writeln("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        new Console().writeln("Lydia conserva a sus " + deLydia.length + " niños");
        new Console().writeln(total + " niños listos para evacuar\n");

        for (Niño n : deLydia) if (n != null) lydia.recibeNiño(n);
        for (Niño n : deAisha) if (n != null) lydia.recibeNiño(n);
        for (Niño n : deDalsy) if (n != null) lydia.recibeNiño(n);

        new Console().writeln("Lydia ahora tiene " + lydia.getCantidadNiños() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        String linea = new String(new char[40]).replace('\0', '=');
        new Console().writeln(linea);
        new Console().writeln("        ESTADO ACTUAL");
        new Console().writeln(linea);

        new Console().writeln("\nLYDIA:");
        if (lydia.getCantidadNiños() == 0) {
            new Console().writeln("  Cola vacía");
        } else {
            new Console().writeln("  Niños en cola: " + lydia.getCantidadNiños() + " (esperando juego)");
          
        }

        mostrarMonitor("AISHA", aisha);
        mostrarMonitor("DALSY", dalsy);

        new Console().writeln(linea);
    }

    private void mostrarMonitor(String nombre, Monitor monitor) {
        new Console().writeln("\n" + nombre + ":");
        if (monitor.getCantidadNiños() == 0) {
            new Console().writeln("  Cola vacía");
        } else {
            new Console().writeln("  Niños en cola: " + monitor.getCantidadNiños());
            for (int i = 0; i < monitor.getCantidadNiños(); i++) {
                Niño n = monitor.colaNiños.getNiño(i);
                if (n != null) {
                    new Console().writeln("  - " + n.getNombre() + " (" + n.getEdad() + " años)");
                }
            }
        }
    }

    public Monitor getLydia() { return lydia; }
    public Monitor getAisha() { return aisha; }
    public Monitor getDalsy() { return dalsy; }
}