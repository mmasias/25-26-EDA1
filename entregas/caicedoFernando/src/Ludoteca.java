public class Ludoteca {
    private final Monitora lydia;
    private final Monitora aisha;
    private final Monitora dalsy;
    private final Niño[] respaldoOrdenOriginal;
    private int cantidadRespaldo;
    private final String[] nombresDisponiblesNiños;
    
    public Ludoteca() {
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
        System.out.println("\nLlega " + niño.obtenerInformacion());
        lydia.recibirNiño(niño);
        System.out.println(niño.getNombre() + " pasa a la cola de Lydia");
    }
    
    public void llegadaNiñoAleatorio() {
        String nombre = inventarNombre();
        int edad = inventarEdad();
        Niño nuevoNiño = new Niño(nombre, edad);
        llegadaNiño(nuevoNiño);
    }
    
    public void intentarInicioDeJuego() {
        System.out.println("\n=== INTENTO DE INICIO DE JUEGO ===\n");
        
        int totalNiños = lydia.getCantidadNiños() + aisha.getCantidadNiños();
        
        if (totalNiños < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
            return;
        }
        
        System.out.println("Lydia transfiere sus niños a Aisha\n");
        
        int transferidos = lydia.getCantidadNiños();
        lydia.transferirTodosLosNiños(aisha);
        
        System.out.println("Niños transferidos: " + transferidos);
    }
    
    public void presentacionesGenerales() {
        System.out.println("\n=== PRESENTACIONES GENERALES ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.presentarse();
        aisha.pedirPresentacionGeneral();
    }
    
    public void presentacionesPorEdad(int edadMinima) {
        System.out.println("\n=== PRESENTACIONES POR EDAD ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionMayoresDe(edadMinima);
    }
    
    public void presentacionesPorInicial(char letra) {
        System.out.println("\n=== PRESENTACIONES POR INICIAL ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionPorInicial(letra);
    }
    
    public void presentacionesPrimerosCinco() {
        System.out.println("\n=== PRESENTACIÓN PRIMEROS 5 NIÑOS ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionPrimerosCinco();
    }
    
    public void presentacionesUltimosCinco() {
        System.out.println("\n=== PRESENTACIÓN ÚLTIMOS 5 NIÑOS ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        aisha.pedirPresentacionUltimosCinco();
    }
    
    public void conteoDeAsistencia() {
        System.out.println("\n=== CONTEO DE ASISTENCIA ===\n");
        
        int lydiaTotal = lydia.getCantidadNiños();
        int aishaTotal = aisha.getCantidadNiños();
        int dalsyTotal = dalsy.getCantidadNiños();
        int total = lydiaTotal + aishaTotal + dalsyTotal;
        
        System.out.println("Lydia tiene " + lydiaTotal + " niños en cola");
        System.out.println("Aisha tiene " + aishaTotal + " niños en cola");
        System.out.println("Dalsy tiene " + dalsyTotal + " niños en cola");
        System.out.println("Total: " + total + " niños");
    }
    
    public void calcularEdadPromedio() {
        System.out.println("\n=== EDAD PROMEDIO ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        double promedio = aisha.calcularEdadPromedio();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años\n", promedio);
    }
    
    public void verificarJuegoDeLaRana() {
        System.out.println("\n=== VERIFICACIÓN JUEGO DE LA RANA ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        System.out.println("Verificando condiciones para el juego de la rana...");
        
        int totalNiños = aisha.getCantidadNiños();
        int niñosMayores5 = aisha.contarNiñosMayoresDe(4);
        
        System.out.println("Total de niños: " + totalNiños);
        System.out.println("Niños de 5 años o más: " + niñosMayores5);
        
        if (niñosMayores5 > totalNiños / 2.0) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }
    
    public void separarNiñosParaJuego() {
        System.out.println("\n=== SEPARACIÓN PARA EL JUEGO DE LA RANA ===\n");
        
        if (aisha.getCantidadNiños() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        
        System.out.println("Separando niños para el juego de la rana...");
        
        guardarOrdenOriginal();
        
        System.out.println("\nNiños menores de 5 años pasan a Dalsy:");
        
        int i = 0;
        while (i < aisha.getCantidadNiños()) {
            Niño niño = aisha.obtenerNiño(i);
            if (niño.getEdad() < 5) {
                System.out.println("- " + niño.obtenerInformacion());
                aisha.sacarPrimerNiño();
                dalsy.recibirNiño(niño);
            } else {
                i++;
            }
        }
        
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (int j = 0; j < aisha.getCantidadNiños(); j++) {
            Niño niño = aisha.obtenerNiño(j);
            System.out.println("- " + niño.obtenerInformacion());
        }
        
        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }
    
    private void guardarOrdenOriginal() {
        cantidadRespaldo = aisha.getCantidadNiños();
        for (int i = 0; i < cantidadRespaldo; i++) {
            respaldoOrdenOriginal[i] = aisha.obtenerNiño(i);
        }
    }
    
    public void alarmaContraIncendios() {
        System.out.println("\n¡ALARMA CONTRA INCENDIOS!\n");
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        
        int totalTransferidos = 0;
        
        if (dalsy.getCantidadNiños() > 0) {
            System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
            totalTransferidos += dalsy.getCantidadNiños();
            dalsy.transferirTodosLosNiños(lydia);
        }
        
        if (aisha.getCantidadNiños() > 0) {
            System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
            totalTransferidos += aisha.getCantidadNiños();
            aisha.transferirTodosLosNiños(lydia);
        }
        
        System.out.println(totalTransferidos + " niños transferidos\n");
        System.out.println("Lydia ahora tiene " + lydia.getCantidadNiños() + " niños listos para evacuar en orden");
    }
    
    public void mostrarEstado() {
        System.out.println("\n========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        
        lydia.mostrarEstado();
        aisha.mostrarEstado();
        dalsy.mostrarEstado();
        
        System.out.println("\n========================================");
    }
}