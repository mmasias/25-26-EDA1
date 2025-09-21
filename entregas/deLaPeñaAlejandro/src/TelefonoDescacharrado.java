import java.util.*;

public class TelefonoDescacharrado {

    private static final int DURACION_TOTAL_MINUTOS = 120; 

    private final Random rnd;
    private final String mensajeOriginal;
    private final double probCambio1; 
    private final double probCambio2; 
    
    private int totalPartidas = 0;
    private int totalNinosAtendidos = 0;
    private final List<Partida> partidas = new ArrayList<>();

    public TelefonoDescacharrado(Random rnd, String mensajeOriginal,
                                 double probCambio1, double probCambio2) {
        this.rnd = rnd;
        this.mensajeOriginal = mensajeOriginal;
        this.probCambio1 = probCambio1;
        this.probCambio2 = probCambio2;
    }

    private static class Partida {
        int minutoInicio;
        int ninosEnCola;
        String mensajeInicial;
        String mensajeFinal;
        int hamming;
        int duracion;
        Partida(int minutoInicio, int ninosEnCola, String mensajeInicial,
               String mensajeFinal, int hamming, int duracion) {
            this.minutoInicio = minutoInicio;
            this.ninosEnCola = ninosEnCola;
            this.mensajeInicial = mensajeInicial;
            this.mensajeFinal = mensajeFinal;
            this.hamming = hamming;
            this.duracion = duracion;
        }
    }

    private String simulaPasoMensaje(int N) {
        
        String actual = mensajeOriginal;
    
        for (int i = 0; i < N; i++) {
            actual = escribirConErrores(actual);
        }
        return actual;
    }

    private String escribirConErrores(String texto) {
        double p = rnd.nextDouble();
        int cambios = 0;
        if (p < probCambio1) cambios = 1;
        else if (p < probCambio1 + probCambio2) cambios = 2;
        else cambios = 0;

        if (cambios == 0) return texto;

        char[] arr = texto.toCharArray();
        int L = arr.length;
      
        Set<Integer> pos = new HashSet<>();
        while (pos.size() < cambios) {
            pos.add(rnd.nextInt(L));
        }
        for (int position : pos) {
            char original = arr[position];
            char nuevo;
            do {
                nuevo = (char) ('A' + rnd.nextInt(26));
            } while (nuevo == original);
            arr[position] = nuevo;
        }
        return new String(arr);
    }

    private int hamming(String a, String b) {
        if (a.length() != b.length()) return Math.max(a.length(), b.length());
        int d = 0;
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) != b.charAt(i)) d++;
        return d;
    }

    public void ejecutarSimulacion() {
        Queue<Integer> cola = new LinkedList<>(); 
        List<Integer> esperandoConLidia = new ArrayList<>(); 
        boolean enJuego = false;
        int tiempoRestanteJuego = 0;
        int minuto = 0;

        while (minuto < DURACION_TOTAL_MINUTOS) {
            
            int llegado = llegadasEnMinuto(minuto);
            if (llegado > 0) {
                if (enJuego) {
                    
                    for (int i = 0; i < llegado; i++) esperandoConLidia.add(1);
                } else {
                    for (int i = 0; i < llegado; i++) cola.add(1);
                }
            }

           
            if (!enJuego && cola.size() > 5) {
                inciaJuego(minuto, cola, esperandoConLidia);
                
                int N = cola.size();
                int duracion = N + 1; 
                enJuego = true;
                tiempoRestanteJuego = duracion;
                
            }

            minuto++;
            if (enJuego) {
                tiempoRestanteJuego--;
                if (tiempoRestanteJuego <= 0) {
                    
                    enJuego = false;
                    
                    for (int k = 0; k < esperandoConLidia.size(); k++) cola.add(1);
                    esperandoConLidia.clear();

                }
            }
        }
    }

    private int llegadasEnMinuto(int minuto) {
       
        if (minuto >= 0 && minuto <= 9) {
            return rnd.nextInt(3); 
        }
       
        if (minuto >= 10 && minuto <= 29) {
           
            int offset = minuto - 10;
            if (offset % 3 == 0) {
                
                return rnd.nextDouble() < 0.5 ? 1 : 0;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private void inciaJuego(int minutoInicio, Queue<Integer> cola, List<Integer> esperandoConLidia) {
        int N = cola.size();
        if (N == 0) return; 
        totalPartidas++;
        totalNinosAtendidos += N;

        String mensajeInicial = mensajeOriginal;
        String mensajeFinal = simulaPasoMensaje(N);
        int h = hamming(mensajeOriginal, mensajeFinal);
        int duracion = N + 1;

        Partida p = new Partida(minutoInicio, N, mensajeInicial, mensajeFinal, h, duracion);
        partidas.add(p);

        
        cola.clear();
       
    }

   
    public void printReport() {
        System.out.println("=== Simulación Teléfono Descacharrado ===");
        System.out.println("Mensaje original: " + mensajeOriginal);
        System.out.println("Probabilidades de cambio: 1 letra=" + probCambio1 +
                ", 2 letras=" + probCambio2 + ", 0 letras=" + (1 - probCambio1 - probCambio2));
        System.out.println();

        if (partidas.isEmpty()) {
            System.out.println("No se inició ninguna partida.");
        } else {
            for (int i = 0; i < partidas.size(); i++) {
                Partida p = partidas.get(i);
                System.out.printf("Partida %2d - minuto %3d - N=%2d - dur=%2d min - final=\"%s\" - Hamming=%d%n",
                        i + 1, p.minutoInicio, p.ninosEnCola, p.duracion, p.mensajeFinal, p.hamming);
            }
            System.out.println();
            System.out.println("Total partidas: " + totalPartidas);
            System.out.println("Total niños que participaron: " + totalNinosAtendidos);
        }
    }

   
    public static void main(String[] args) {
       
        long seed = 12345L; 
        String mensaje = "ABCDEFGHIJ";
        double probCambio1 = 0.25;
        double probCambio2 = 0.10;

        
        if (args.length >= 1) {
            try {
                seed = Long.parseLong(args[0]);
            } catch (Exception ignored) {}
        }
        if (args.length >= 2) mensaje = args[1];
        if (args.length >= 3) {
            try {
                probCambio1 = Double.parseDouble(args[2]);
            } catch (Exception ignored) {}
        }
        if (args.length >= 4) {
            try {
                probCambio2 = Double.parseDouble(args[3]);
            } catch (Exception ignored) {}
        }

        TelefonoDescacharrado sim = new TelefonoDescacharrado(new Random(seed), mensaje, probCambio1, probCambio2);
        sim.ejecutarSimulacion();
        sim.printReport();
    }
}

