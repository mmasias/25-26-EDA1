import utils.Console;

class Monitor {
    private String nombre;
    private Cola colaNiños;
    private boolean estaJugando;
    private int turnoActual;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
        estaJugando = false;
        turnoActual = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibeNiño(Niño niño) {
        colaNiños.addNiño(niño);
    }

    public void recibeNiñoConPizarra(Niño niño) {
        niño.recibirPizarrin(new Pizarra());
        colaNiños.addNiño(niño);
    }

    public boolean tieneNiños() {
        return colaNiños.hayNiños();
    }

    public boolean puedeJugar() {
        return colaNiños.size() >= 5;
    }

    public boolean estaJugando() {
        return estaJugando;
    }

    public void mostrarListaNiños() {
        new Console().write("> " + this.nombre + " --> ");
        colaNiños.listaNiños();
        new Console().writeln();
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (colaNiños.hayNiños()) {
            Niño unNiño = colaNiños.removeNiño();
            otroMonitor.recibeNiñoConPizarra(unNiño);
        }
    }

    public void jugar() {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;
            Niño primerNiño = colaNiños.getNiño(turnoActual);
            if (primerNiño != null) {
                primerNiño.recibirMensaje("ABCDEFGHIJKLM");
            }
        } else {
            Niño niñoActual = colaNiños.getNiño(turnoActual);
            if (turnoActual + 1 >= colaNiños.size()) {
                estaJugando = false;
                turnoActual = 0;
            } else {
                Niño siguienteNiño = colaNiños.getNiño(turnoActual + 1);
                if (niñoActual != null && siguienteNiño != null) {
                    siguienteNiño.recibirMensaje(niñoActual.mostrarMensaje());
                }
                turnoActual++;
            }
        }
    }

    private void limpiarPizarrines() {
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño niño = colaNiños.getNiño(i);
            if (niño != null) {
                niño.limpiarPizarrin();
            }
        }
    }

    public String getUsoMemoria() {
        return colaNiños.size() + "/20";
    }

    public int getCantidadNiños() {
        return colaNiños.size();
    }

    public double getEdadPromedio() {
        if (colaNiños.size() == 0) return 0.0;
        int suma = 0;
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null) suma += n.getEdad();
        }
        return (double) suma / colaNiños.size();
    }

    public void presentarATodos() {
        new Console().writeln("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null) {
                new Console().writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
            }
        }
    }

    public void presentarMayoresDe(int edadMinima) {
        new Console().writeln("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null && n.getEdad() > edadMinima) {
                new Console().writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
            }
        }
    }

    public void presentarNombresConLetra(char letra) {
        new Console().writeln("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null && !n.getNombre().isEmpty() && 
                Character.toLowerCase(n.getNombre().charAt(0)) == Character.toLowerCase(letra)) {
                new Console().writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
            }
        }
    }

    public void presentarPrimeros(int cantidad) {
        int mostrar = Math.min(cantidad, colaNiños.size());
        new Console().writeln("Aisha pide que se presenten los primeros " + mostrar + " niños:");
        for (int i = 0; i < mostrar; i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null) {
                new Console().writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
            }
        }
    }

    public void presentarUltimos(int cantidad) {
        int total = colaNiños.size();
        int mostrar = Math.min(cantidad, total);
        new Console().writeln("Aisha pide que se presenten los últimos " + mostrar + " niños:");
        for (int i = total - mostrar; i < total; i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null) {
                new Console().writeln("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
            }
        }
    }

    public boolean puedeJugarRana() {
        if (colaNiños.size() == 0) return false;
        int mayoresDe5 = 0;
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null && n.getEdad() >= 5) {
                mayoresDe5++;
            }
        }
        return mayoresDe5 > colaNiños.size() / 2.0;
    }

    public void mostrarInfoRana() {
        int total = colaNiños.size();
        int mayoresDe5 = 0;
        for (int i = 0; i < total; i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null && n.getEdad() >= 5) mayoresDe5++;
        }
        new Console().writeln("Verificando condiciones para el juego de la rana...");
        new Console().writeln("Total de niños: " + total);
        new Console().writeln("Niños de 5 años o más: " + mayoresDe5);
        if (puedeJugarRana()) {
            new Console().writeln("¡Más de la mitad cumplen la condición!");
            new Console().writeln("¡Pueden jugar al juego de la rana!");
        } else {
            new Console().writeln("No hay suficientes niños mayores de 5 años");
            new Console().writeln("No pueden jugar todavía");
        }
    }

    public void moverMenoresADalsy(Monitor dalsy) {
        Cola mayores = new Cola();
        new Console().writeln("Separando niños para el juego de la rana...");
        new Console().writeln("Niños menores de 5 años pasan a Dalsy:");
        boolean hayMenores = false;
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n != null) {
                if (n.getEdad() < 5) {
                    new Console().writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                    dalsy.recibeNiñoConPizarra(n);
                    hayMenores = true;
                } else {
                    mayores.addNiño(n);
                }
            }
        }
        if (!hayMenores) {
            new Console().writeln("(No hay niños menores de 5 años)");
        }

        new Console().writeln("\nNiños que se quedan con Aisha para jugar:");
        while (colaNiños.hayNiños()) colaNiños.removeNiño();
        boolean hayMayores = false;
        for (int i = 0; i < mayores.size(); i++) {
            Niño n = mayores.getNiño(i);
            if (n != null) {
                new Console().writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
                colaNiños.addNiño(n);
                hayMayores = true;
            }
        }
        if (!hayMayores) {
            new Console().writeln("(No hay niños de 5 o más años)");
        }
        new Console().writeln("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public Niño[] transferirTodos() {
        Niño[] lista = colaNiños.getNiñosArray();
        while (colaNiños.hayNiños()) colaNiños.removeNiño();
        return lista;
    }

    public void recibirTodos(Niño[] lista) {
        for (Niño n : lista) {
            if (n != null) recibeNiño(n);
        }
    }
}