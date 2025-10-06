public class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Pizarra pizarraDelSalon;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        pizarraDelSalon = new Pizarra();
    }

    

    public void simularLlegadaNiño() {
        Console console = new Console();
        String nombre = console.readString("Nombre del niño: ");
        int edad = console.readInt("Edad: ");
        Niño n = new Niño(nombre, edad);
        console.writeln("Llega " + nombre + " (" + edad + " años)");
        console.writeln(nombre + " pasa a la cola de Lydia");
        lydia.recibeNiño(n);
    }

    
    public void simularInicioJuego() {
        if (lydia.numeroNiños() >= 5) {
            lydia.entregaNiños(aisha);
            new Console().writeln("Lydia transfiere sus niños a Aisha");
            aisha.mostrarListaNiños();
        } else {
            new Console().writeln("No hay suficientes niños para iniciar el juego");
            new Console().writeln("Se necesitan al menos 5 niños");
        }
    }

   
    public void presentacionGeneral() {
        Console c = new Console();
        c.writeln("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        Niño[] lista = aisha.getNiños();
        for (int i = 0; i < lista.length; i++) {
            c.writeln(lista[i].presentarse());
        }
    }

   
    public void presentarMayoresDe(int edadMin) {
        Console c = new Console();
        c.writeln("Aisha pide que se presenten los mayores de " + edadMin + " años:\n");
        Niño[] lista = aisha.getNiños();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getEdad() > edadMin) {
                c.writeln(lista[i].presentarse());
            }
        }
    }

    
    public void presentarPorInicial(char letra) {
        Console c = new Console();
        c.writeln("Aisha pide que se presenten los niños cuyo nombre empieza por '" + letra + "':\n");
        Niño[] lista = aisha.getNiños();
        for (int i = 0; i < lista.length; i++) {
            String nombre = lista[i].getNombre();
            if (!nombre.isEmpty() && Character.toUpperCase(nombre.charAt(0)) == Character.toUpperCase(letra)) {
                c.writeln(lista[i].presentarse());
            }
        }
    }

    
    public void presentarPrimerosCinco() {
        Console c = new Console();
        Niño[] lista = aisha.getNiños();
        c.writeln("Aisha pide que se presenten los cinco primeros niños:\n");
        for (int i = 0; i < lista.length && i < 5; i++) {
            c.writeln(lista[i].presentarse());
        }
    }

    
    public void presentarUltimosCinco() {
        Console c = new Console();
        Niño[] lista = aisha.getNiños();
        int inicio = lista.length > 5 ? lista.length - 5 : 0;
        c.writeln("Aisha pide que se presenten los cinco últimos niños:\n");
        for (int i = inicio; i < lista.length; i++) {
            c.writeln(lista[i].presentarse());
        }
    }

    
    public void mostrarCantidadNiños() {
        Console c = new Console();
        c.writeln("Lydia tiene " + lydia.numeroNiños() + " niños en cola");
        c.writeln("Aisha tiene " + aisha.numeroNiños() + " niños en cola");
    }

    
    public void mostrarEdadPromedio() {
        Console c = new Console();
        Niño[] lista = aisha.getNiños();
        if (lista.length == 0) {
            c.writeln("No hay niños en cola");
            return;
        }
        int suma = 0;
        for (int i = 0; i < lista.length; i++) {
            suma += lista[i].getEdad();
        }
        double promedio = (double) suma / lista.length;
        c.writeln("Edad promedio: " + String.format("%.2f", promedio));
    }

    
    public void simularJuegoRana() {
        new Console().writeln("Simulando el juego de la rana 🐸...");
    }

    
    public void pasarMenoresDe5() {
        new Console().writeln("Lydia transfiere los menores de 5 años a la monitora Dalsy");
        
    }

    
    public void protocoloEmergencia() {
        new Console().writeln("¡Alarma contra incendios! Todos los niños evacuando...");
        lydia.vaciar();
        aisha.vaciar();
    }

    
    public void mostrarMonitoresYNiños() {
        lydia.mostrarListaNiños();
        aisha.mostrarListaNiños();
    }

    public void usarPizarra() {
        System.out.println(pizarraDelSalon); 
    }
}