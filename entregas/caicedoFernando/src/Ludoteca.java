public class Ludoteca {
    private final Monitora lydia;
    private final Monitora aisha;
    private final Monitora dalsy;
    private final Niño[] respaldoOrdenOriginal;
    private int cantidadRespaldo;
    private final String[] nombresDisponiblesNiños;
    private final Console console;
    
    public Ludoteca() {
        this.console = new Console();
        this.lydia = new Monitora("Lydia");
        this.aisha = new Monitora("Aisha");
        this.dalsy = new Monitora("Dalsy");
        this.respaldoOrdenOriginal = new Niño[100];
        this.cantidadRespaldo = 0;
        this.nombresDisponiblesNiños = new String[] {
            "Ana", "Bruno", "Carlos", "Diana", "Elena", "Fernando",
            "Gabriel", "Hugo", "Isabel", "Juan", "Laura", "Mario",
            "Natalia", "Oscar", "Paula", "Roberto", "Sofia", "Tomas",
            "Valeria", "Xavier", "Yasmin", "Zoe", "Adrian", "Beatriz",
            "Cesar", "Daniela", "Emilio", "Fabiola", "Gustavo", "Helena"
        };
    }
    
    public Monitora getLydia() {
        return lydia;
    }
    
    public Monitora getAisha() {
        return aisha;
    }
    
    public Monitora getDalsy() {
        return dalsy;
    }
    
    private String inventarNombre() {
        int indice = (int) (Math.random() * nombresDisponiblesNiños.length);
        return nombresDisponiblesNiños[indice];
    }
    
    private int inventarEdad() {
        return 3 + (int) (Math.random() * 7);
    }
    
    public void llegadaNiño(Niño niño) {
        console.writeln("\nLlega " + niño.obtenerInformacion());
        lydia.recibirNiño(niño);
        console.writeln(niño.getNombre() + " pasa a la cola de Lydia");
    }
    
    public void llegadaNiñoAleatorio() {
        String nombre = inventarNombre();
        int edad = inventarEdad();
        Niño nuevoNiño = new Niño(nombre, edad);
        llegadaNiño(nuevoNiño);
    }
    
    public void intentarInicioDeJuego() {
        console.writeln("\n=== INTENTO DE INICIO DE JUEGO ===\n");
        
        int totalNiños = lydia.getCantidadNiños() + aisha.getCantidadNiños();
        
        if (totalNiños < 5) {
            console.writeln("No hay suficientes niños para iniciar el juego");
            console.writeln("Se necesitan al menos 5 niños");
            return;
        }
        
        console.writeln("Lydia transfiere sus niños a Aisha\n");
        
        int transferidos = lydia.getCantidadNiños();
        lydia.transferirTodosLosNiños(aisha);
        
        console.writeln("Niños transferidos: " + transferidos);
    }
    
    public void presentacionesGenerales() {
        console.writeln("\n=== PRESENTACIONES GENERALES ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.presentarse();
        aisha.pedirPresentacionGeneral();
    }
    
    public void presentacionesPorEdad(int edadMinima) {
        console.writeln("\n=== PRESENTACIONES POR EDAD ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionMayoresDe(edadMinima);
    }
    
    public void presentacionesPorInicial(char letra) {
        console.writeln("\n=== PRESENTACIONES POR INICIAL ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionPorInicial(letra);
    }
    
    public void presentacionesPrimerosCinco() {
        console.writeln("\n=== PRESENTACIÓN PRIMEROS 5 NIÑOS ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionPrimerosCinco();
    }
    
    public void presentacionesUltimosCinco() {
        console.writeln("\n=== PRESENTACIÓN ÚLTIMOS 5 NIÑOS ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionUltimosCinco();
    }
    
    public void conteoDeAsistencia() {
        console.writeln("\n=== CONTEO DE ASISTENCIA ===\n");
        
        int lydiaTotal = lydia.getCantidadNiños();
        int aishaTotal = aisha.getCantidadNiños();
        int dalsyTotal = dalsy.getCantidadNiños();
        int total = lydiaTotal + aishaTotal + dalsyTotal;
        
        console.writeln("Lydia tiene " + lydiaTotal + " niños en cola");
        console.writeln("Aisha tiene " + aishaTotal + " niños en cola");
        console.writeln("Dalsy tiene " + dalsyTotal + " niños en cola");
        console.writeln("Total: " + total + " niños");
    }
    
    public void calcularEdadPromedio() {
        console.writeln("\n=== EDAD PROMEDIO ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        double promedio = aisha.calcularEdadPromedio();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años\n", promedio);
    }
    
    public void verificarJuegoDeLaRana() {
        console.writeln("\n=== VERIFICACIÓN JUEGO DE LA RANA ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        console.writeln("Verificando condiciones para el juego de la rana...");
        
        int totalNiños = aisha.getCantidadNiños();
        int niñosMayores5 = aisha.contarNiñosMayoresDe(4);
        
        console.writeln("Total de niños: " + totalNiños);
        console.writeln("Niños de 5 años o más: " + niñosMayores5);
        
        if (niñosMayores5 > totalNiños / 2.0) {
            console.writeln("¡Más de la mitad cumplen la condición!");
            console.writeln("¡Pueden jugar al juego de la rana!");
        } else {
            console.writeln("No hay suficientes niños mayores de 5 años");
            console.writeln("No pueden jugar todavía");
        }
    }
    
    public void separarNiñosParaJuego() {
        console.writeln("\n=== SEPARACIÓN PARA EL JUEGO DE LA RANA ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
            return;
        }
        
        console.writeln("Separando niños para el juego de la rana...");
        
        guardarOrdenOriginal();
        
        console.writeln("\nNiños menores de 5 años pasan a Dalsy:");
        
        int i = 0;
        while (i < aisha.getCantidadNiños()) {
            Niño niño = aisha.obtenerNiño(i);
            if (niño.getEdad() < 5) {
                console.writeln("- " + niño.obtenerInformacion());
                aisha.sacarPrimerNiño();
                dalsy.recibirNiño(niño);
            } else {
                i++;
            }
        }
        
        console.writeln("\nNiños que se quedan con Aisha para jugar:");
        for (int j = 0; j < aisha.getCantidadNiños(); j++) {
            Niño niño = aisha.obtenerNiño(j);
            console.writeln("- " + niño.obtenerInformacion());
        }
        
        console.writeln("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }
    
    private void guardarOrdenOriginal() {
        cantidadRespaldo = aisha.getCantidadNiños();
        for (int i = 0; i < cantidadRespaldo; i++) {
            respaldoOrdenOriginal[i] = aisha.obtenerNiño(i);
        }
    }
    
    public void alarmaContraIncendios() {
        console.writeln("\n¡ALARMA CONTRA INCENDIOS!\n");
        console.writeln("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        
        int totalTransferidos = 0;
        
        if (dalsy.getCantidadNiños() > 0) {
            console.writeln("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
            totalTransferidos += dalsy.getCantidadNiños();
            dalsy.transferirTodosLosNiños(lydia);
        }
        
        if (aisha.getCantidadNiños() > 0) {
            console.writeln("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
            totalTransferidos += aisha.getCantidadNiños();
            aisha.transferirTodosLosNiños(lydia);
        }
        
        console.writeln(totalTransferidos + " niños transferidos\n");
        console.writeln("Lydia ahora tiene " + lydia.getCantidadNiños() + " niños listos para evacuar en orden");
    }
    
    public void mostrarEstado() {
        console.writeln("\n========================================");
        console.writeln("        ESTADO ACTUAL");
        console.writeln("========================================");
        
        lydia.mostrarEstado();
        aisha.mostrarEstado();
        dalsy.mostrarEstado();
        
        console.writeln("\n========================================");
    }
}