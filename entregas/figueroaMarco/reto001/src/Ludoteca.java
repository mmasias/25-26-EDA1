import java.util.Random;

public class Ludoteca {
    
    private static final int MINIMO_NINOS_PARA_JUGAR = 5;
    private static final int TIEMPO_POR_NINO_MINUTOS = 1;
    private static final int DURACION_LUDOTECA_MINUTOS = 120;
    private static final int MAX_NINOS = 6;
    private static final int MAX_JUEGOS = 50;
    
    private final Nino[] colaJuego;
    private final Nino[] ninosEsperando;
    private final ResultadoJuego[] historialJuegos;
    private final Random random;
    private final GeneradorMensajes generadorMensajes;
    
    private boolean juegoEnCurso;
    private int tiempoActual;
    private int contadorNinos;
    private int cantidadEnCola;
    private int cantidadEsperando;
    private int cantidadJuegos;
    
    public Ludoteca() {
        this.colaJuego = new Nino[MAX_NINOS];
        this.ninosEsperando = new Nino[MAX_NINOS];
        this.historialJuegos = new ResultadoJuego[MAX_JUEGOS];
        this.random = new Random();
        this.generadorMensajes = new GeneradorMensajes();
        this.juegoEnCurso = false;
        this.tiempoActual = 0;
        this.contadorNinos = 1;
        this.cantidadEnCola = 0;
        this.cantidadEsperando = 0;
        this.cantidadJuegos = 0;
    }
    
    public void ejecutarSimulacion() {
        System.out.println("=== INICIO DE LA SIMULACIÓN DE LA LUDOTECA ===");
        System.out.println("Duración: " + DURACION_LUDOTECA_MINUTOS + " minutos (2 horas)");
        System.out.println();
        
        for (tiempoActual = 1; tiempoActual <= DURACION_LUDOTECA_MINUTOS; tiempoActual++) {
            procesarMinuto();
        }
        
        mostrarResumenFinal();
    }
    
    private void procesarMinuto() {
        procesarLlegadaNinos();        
        if (!juegoEnCurso) {
            agregarNinosEsperandoACola();
            intentarIniciarJuego();
        }
    }
    
    private void procesarLlegadaNinos() {
        int ninosQueLlegan = calcularNinosQueLlegan();
        
        for (int i = 0; i < ninosQueLlegan; i++) {
            String nombreNino = "Niño" + contadorNinos++;
            Nino nuevoNino = new Nino(nombreNino, tiempoActual);
            
            if (juegoEnCurso) {
                if (cantidadEsperando < MAX_NINOS) {
                    ninosEsperando[cantidadEsperando] = nuevoNino;
                    cantidadEsperando++;
                    System.out.printf("Minuto %d: %s llega pero espera (juego en curso)%n", 
                                    tiempoActual, nuevoNino.getNombre());
                }
            } else {
                if (cantidadEnCola < MAX_NINOS) {
                    colaJuego[cantidadEnCola] = nuevoNino;
                    cantidadEnCola++;
                    System.out.printf("Minuto %d: %s llega y se une a la cola%n", 
                                    tiempoActual, nuevoNino.getNombre());
                }
            }
        }
    }
    
    private int calcularNinosQueLlegan() {
        if (tiempoActual <= 10) {
            return random.nextInt(3);
        } else if (tiempoActual <= 30) {
            if (tiempoActual % 3 == 0 && random.nextBoolean()) {
                return 1;
            }
        }
        return 0;
    }

    private void agregarNinosEsperandoACola() {
        if (cantidadEsperando > 0) {
            System.out.printf("Minuto %d: Agregando %d niños que esperaban a la cola%n", 
                            tiempoActual, cantidadEsperando);
            
            for (int i = 0; i < cantidadEsperando && cantidadEnCola < MAX_NINOS; i++) {
                colaJuego[cantidadEnCola] = ninosEsperando[i];
                cantidadEnCola++;
            }
            cantidadEsperando = 0;
        }
    }
    
    private void intentarIniciarJuego() {
        if (cantidadEnCola > MINIMO_NINOS_PARA_JUGAR) {
            iniciarJuego();
        }
    }

    private void iniciarJuego() {
        juegoEnCurso = true;
        
        String mensajeOriginal = generadorMensajes.generarMensaje();
        
        System.out.printf("%nMinuto %d: ¡INICIA NUEVO JUEGO!%n", tiempoActual);
        System.out.printf("Participantes: %d niños%n", cantidadEnCola);
        System.out.printf("Mensaje original de Aisha: \"%s\"%n", mensajeOriginal);
        
        for (int i = 0; i < cantidadEnCola; i++) {
            colaJuego[i].limpiarPizarrin();
        }
        
        String mensajeFinal = ejecutarPasoMensajes(mensajeOriginal);
        
        if (cantidadJuegos < MAX_JUEGOS) {
            historialJuegos[cantidadJuegos] = new ResultadoJuego(
                tiempoActual, 
                mensajeOriginal, 
                mensajeFinal, 
                cantidadEnCola
            );
            cantidadJuegos++;
        }
        
        int tiempoJuego = cantidadEnCola * TIEMPO_POR_NINO_MINUTOS + TIEMPO_POR_NINO_MINUTOS;
        tiempoActual += tiempoJuego - 1;
        
        System.out.printf("Minuto %d: Juego terminado. Mensaje final en pizarra: \"%s\"%n", 
                        tiempoActual, mensajeFinal);
        mostrarDeformacion(mensajeOriginal, mensajeFinal);
        System.out.println();
        
        cantidadEnCola = 0;
        juegoEnCurso = false;
    }

    private String ejecutarPasoMensajes(String mensajeInicial) {
        String mensajeActual = mensajeInicial;
        
        System.out.println("--- Paso de mensajes ---");
        
        for (int i = 0; i < cantidadEnCola; i++) {
            Nino ninoActual = colaJuego[i];
            mensajeActual = ninoActual.recibirMensaje(mensajeActual);
            
            System.out.printf("  %s recibe y escribe: \"%s\"%n", 
                            ninoActual.getNombre(), mensajeActual);
        }
        
        return mensajeActual;
    }
    
    private void mostrarDeformacion(String original, String final_) {
        int diferencias = contarDiferencias(original, final_);
        double porcentajeError = (double) diferencias / original.length() * 100;
        
        System.out.printf("Deformación: %d caracteres diferentes (%.1f%%)%n", 
                        diferencias, porcentajeError);
    }
    
    private int contarDiferencias(String str1, String str2) {
        int diferencias = 0;
        int longitud = Math.min(str1.length(), str2.length());
        
        for (int i = 0; i < longitud; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diferencias++;
            }
        }
        
        diferencias += Math.abs(str1.length() - str2.length());
        
        return diferencias;
    }
    
    private void mostrarResumenFinal() {
        System.out.println("=== RESUMEN FINAL DE LA SIMULACIÓN ===");
        System.out.printf("Tiempo total: %d minutos%n", DURACION_LUDOTECA_MINUTOS);
        System.out.printf("Total de niños que llegaron: %d%n", contadorNinos - 1);
        System.out.printf("Total de juegos realizados: %d%n", cantidadJuegos);
        
        if (cantidadJuegos > 0) {
            System.out.println();
            System.out.println("--- Historial de juegos ---");
            for (int i = 0; i < cantidadJuegos; i++) {
                ResultadoJuego juego = historialJuegos[i];
                System.out.printf("Juego %d (Minuto %d): \"%s\" → \"%s\" (%d participantes)%n",
                                i + 1, juego.getMinutoInicio(), juego.getMensajeOriginal(),
                                juego.getMensajeFinal(), juego.getNumParticipantes());
            }
        }
        
        System.out.printf("%nNiños restantes en cola: %d%n", cantidadEnCola);
        System.out.printf("Niños esperando: %d%n", cantidadEsperando);
    }
    
    private static class ResultadoJuego {
        private final int minutoInicio;
        private final String mensajeOriginal;
        private final String mensajeFinal;
        private final int numParticipantes;
        
        public ResultadoJuego(int minutoInicio, String mensajeOriginal, 
                            String mensajeFinal, int numParticipantes) {
            this.minutoInicio = minutoInicio;
            this.mensajeOriginal = mensajeOriginal;
            this.mensajeFinal = mensajeFinal;
            this.numParticipantes = numParticipantes;
        }
        
        public int getMinutoInicio() { return minutoInicio; }
        public String getMensajeOriginal() { return mensajeOriginal; }
        public String getMensajeFinal() { return mensajeFinal; }
        public int getNumParticipantes() { return numParticipantes; }
    }
}
