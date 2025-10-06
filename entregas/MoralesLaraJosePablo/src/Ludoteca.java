public class Ludoteca {
    private static final int MIN_NIÑOS_JUEGO = 5;
    private static final int EDAD_JUEGO_RANA = 5;
    private static final int MAX_NIÑOS = 100;
    
    private Monitora lydia;
    private Monitora aisha;
    private Monitora dalsy;
    
    private Niño[] backupAisha;
    private int numBackupAisha;
    private boolean hayBackup;
    
    public Ludoteca() {
        this.lydia = new Monitora("Lydia");
        this.aisha = new Monitora("Aisha");
        this.dalsy = new Monitora("Dalsy");
        this.backupAisha = new Niño[MAX_NIÑOS];
        this.numBackupAisha = 0;
        this.hayBackup = false;
    }
    
    public void simularLlegadaNiño(String nombre, int edad) {
        Niño nuevoNiño = new Niño(nombre, edad);
        System.out.println("Llega " + nombre + " (" + edad + " años)");
        lydia.agregarNiño(nuevoNiño);
        System.out.println(nombre + " pasa a la cola de Lydia");
    }

    public void registrarNiñoSilencioso(String nombre, int edad) {
        Niño nuevoNiño = new Niño(nombre, edad);
        aisha.agregarNiño(nuevoNiño);
    }
    
    public void simularInicioJuego() {
        if (lydia.getCantidadNiños() >= MIN_NIÑOS_JUEGO) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            lydia.transferirTodosA(aisha);
            System.out.println("Transferidos " + aisha.getCantidadNiños() + " niños a Aisha");
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos " + MIN_NIÑOS_JUEGO + " niños");
        }
    }
    
    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (int i = 0; i < aisha.getCantidadNiños(); i++) {
            Niño niño = aisha.obtenerNiño(i);
            System.out.println("Niño: Hola, soy " + niño.getNombre() + ", tengo " + niño.getEdad() + " años");
        }
    }
    
    public void presentacionesPorEdad(int edadMinima) {
        for (int i = 0; i < aisha.getCantidadNiños(); i++) {
            Niño niño = aisha.obtenerNiño(i);
            if (niño.getEdad() > edadMinima) {
                System.out.println("Niño: Hola, soy " + niño.getNombre() + ", tengo " + niño.getEdad() + " años");
            }
        }
    }
    
    public void presentacionesPorInicial(char letra) {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
        System.out.println();
        char letraMayuscula = Character.toUpperCase(letra);
        for (int i = 0; i < aisha.getCantidadNiños(); i++) {
            Niño niño = aisha.obtenerNiño(i);
            char primeraLetra = Character.toUpperCase(niño.getNombre().charAt(0));
            if (primeraLetra == letraMayuscula) {
                niño.presentarseSinEdad();
            }
        }
    }
    
    public void presentacionesPrimerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        System.out.println();
        int cantidad = Math.min(5, aisha.getCantidadNiños());
        for (int i = 0; i < cantidad; i++) {
            Niño niño = aisha.obtenerNiño(i);
            niño.presentarseSinEdad();
        }
    }
    
    public void presentacionesUltimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        System.out.println();
        int totalNiños = aisha.getCantidadNiños();
        int inicio = Math.max(0, totalNiños - 5);
        for (int i = inicio; i < totalNiños; i++) {
            Niño niño = aisha.obtenerNiño(i);
            niño.presentarseSinEdad();
        }
    }
    
    public void mostrarConteoAsistencia() {
        int totalLydia = lydia.getCantidadNiños();
        int totalAisha = aisha.getCantidadNiños();
        int totalDalsy = dalsy.getCantidadNiños();
        int total = totalLydia + totalAisha + totalDalsy;
        
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + totalLydia + " niños en cola");
        System.out.println("Aisha tiene " + totalAisha + " niños en cola");
        System.out.println("Dalsy tiene " + totalDalsy + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }
    
    public void mostrarEdadPromedio() {
        if (aisha.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            double promedio = aisha.calcularEdadPromedio();
            System.out.println("Edad promedio de los niños en la cola de Aisha: " + formatearDecimal(promedio) + " años");
        }
    }
    
    public void verificarJuegoRana() {
        int totalNiños = aisha.getCantidadNiños();
        int niñosMayores = aisha.contarNiñosPorEdad(EDAD_JUEGO_RANA);
        
        System.out.println("Verificando condiciones para el juego de la rana...");
        System.out.println("Total de niños: " + totalNiños);
        System.out.println("Niños de " + EDAD_JUEGO_RANA + " años o más: " + niñosMayores);
        
        if (niñosMayores > totalNiños / 2.0) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de " + EDAD_JUEGO_RANA + " años");
            System.out.println("No pueden jugar todavía");
        }
    }
    
    public void separarParaJuego() {
        System.out.println("Separando niños para el juego de la rana...");
        guardarBackupAisha();
        Niño[] menores = new Niño[MAX_NIÑOS];
        int numMenores = 0;
        Niño[] mayores = new Niño[MAX_NIÑOS];
        int numMayores = 0;
        
        while (!aisha.estaVacia()) {
            Niño niño = aisha.sacarNiño();
            if (niño.getEdad() < EDAD_JUEGO_RANA) {
                menores[numMenores] = niño;
                numMenores++;
            } else {
                mayores[numMayores] = niño;
                numMayores++;
            }
        }
        
        if (numMenores > 0) {
            System.out.println("Niños menores de " + EDAD_JUEGO_RANA + " años pasan a Dalsy:");
            for (int i = 0; i < numMenores; i++) {
                System.out.println("- " + menores[i].toString());
                dalsy.agregarNiño(menores[i]);
            }
        }
        
        System.out.println();
        
        if (numMayores > 0) {
            System.out.println("Niños que se quedan con Aisha para jugar:");
            for (int i = 0; i < numMayores; i++) {
                System.out.println("- " + mayores[i].toString());
                aisha.agregarNiño(mayores[i]);
            }
        }
        
        System.out.println();
        System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");
    }
    
    public void activarAlarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        System.out.println();
        
        int totalTransferidos = dalsy.getCantidadNiños() + aisha.getCantidadNiños();
        
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        dalsy.transferirTodosA(lydia);
        
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        aisha.transferirTodosA(lydia);
        
        System.out.println(totalTransferidos + " niños transferidos");
        System.out.println();
        System.out.println("Lydia ahora tiene " + lydia.getCantidadNiños() + " niños listos para evacuar en orden");
        
        hayBackup = false;
        numBackupAisha = 0;
    }
    
    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println();
        
        System.out.println("LYDIA:");
        lydia.mostrarNiños();
        System.out.println();
        
        System.out.println("AISHA:");
        aisha.mostrarNiños();
        System.out.println();
        
        System.out.println("DALSY:");
        dalsy.mostrarNiños();
        System.out.println();
        
        System.out.println("========================================");
    }
    
    private void guardarBackupAisha() {
        numBackupAisha = 0;
        for (int i = 0; i < aisha.getCantidadNiños(); i++) {
            backupAisha[numBackupAisha] = aisha.obtenerNiño(i);
            numBackupAisha++;
        }
        hayBackup = true;
    }
    
    private String formatearDecimal(double valor) {
        return String.valueOf(Math.round(valor * 10.0) / 10.0);
    }
    
    public boolean aishaTieneNiños() {
        return !aisha.estaVacia();
    }
}