import java.util.Random;

public class Simulacion {
    private final int minutosTotales;
    private final int longitudDelMensaje;
    private final double probabilidadCambioCero;
    private final double probabilidadCambioUno;
    private final double probabilidadCambioDos;
    private final Random numeroAleatorio;

    private int contadorDeNinios = 1;

    private final Ninio[] colaDeNinios = new Ninio[1000];
    private int cantidadEnCola = 0;

    private final Ninio[] niniosRetenidos = new Ninio[1000];
    private int cantidadRetenidos = 0;

    private final Juego[] listaDeJuegos = new Juego[500];
    private int cantidadDeJuegos = 0;

    private int minutoActual = 0;

    public Simulacion(int minutosTotales, int longitudDelMensaje,
                      double probabilidadCambioCero, double probabilidadCambioUno,
                      double probabilidadCambioDos, Random numeroAleatorio) {
        this.minutosTotales = minutosTotales;
        this.longitudDelMensaje = longitudDelMensaje;
        this.probabilidadCambioCero = probabilidadCambioCero;
        this.probabilidadCambioUno = probabilidadCambioUno;
        this.probabilidadCambioDos = probabilidadCambioDos;
        this.numeroAleatorio = numeroAleatorio;
    }

    public void ejecutar() {
        while (minutoActual < minutosTotales) {
            int cantidadDeLlegadas = generarLlegadasEnMinuto(minutoActual);
            for (int i = 0; i < cantidadDeLlegadas; i++) {
                Ninio ninio = new Ninio(contadorDeNinios++, minutoActual);
                colaDeNinios[cantidadEnCola++] = ninio;
            }
            if (cantidadEnCola > 5) {
                jugar();
            } else {
                minutoActual++;
            }
        }
    }

    private void jugar() {
        int identificadorJuego = ++cantidadDeJuegos;
        int minutoDeInicioDelJuego = minutoActual;
        int cantidadDeParticipantes = cantidadEnCola;
        String mensajeOriginal = generarMensajeAleatorio(longitudDelMensaje);
        String mensajeEnEjecucion = mensajeOriginal;

        minutoActual++;
        capturarLlegadasDuranteIntervalo(minutoDeInicioDelJuego, minutoDeInicioDelJuego);

        while (cantidadEnCola > 0) {
            Ninio ninio = colaDeNinios[0];
            for (int i = 1; i < cantidadEnCola; i++) {
                colaDeNinios[i - 1] = colaDeNinios[i];
            }
            cantidadEnCola--;

            minutoActual++;
            capturarLlegadasDuranteIntervalo(minutoActual - 1, minutoActual - 1);
            mensajeEnEjecucion = mutarMensaje(mensajeEnEjecucion);
        }

        minutoActual++;
        capturarLlegadasDuranteIntervalo(minutoActual - 1, minutoActual - 1);

        int distancia = calcularDistanciaDeHamming(mensajeOriginal, mensajeEnEjecucion);
        listaDeJuegos[cantidadDeJuegos - 1] = new Juego(
                identificadorJuego, minutoDeInicioDelJuego, cantidadDeParticipantes,
                mensajeOriginal, mensajeEnEjecucion, distancia
        );

        for (int i = 0; i < cantidadRetenidos; i++) {
            colaDeNinios[cantidadEnCola++] = niniosRetenidos[i];
        }
        cantidadRetenidos = 0;
    }

    private int generarLlegadasEnMinuto(int minuto) {
        if (minuto >= 0 && minuto <= 9) {
            return numeroAleatorio.nextInt(3);
        } else if (minuto >= 10 && minuto <= 29) {
            if ((minuto - 10) % 3 == 0) {
                return numeroAleatorio.nextDouble() < 0.5 ? 1 : 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private void capturarLlegadasDuranteIntervalo(int desde, int hasta) {
        for (int minuto = desde; minuto <= hasta; minuto++) {
            int cantidadDeLlegadas = generarLlegadasEnMinuto(minuto);
            for (int i = 0; i < cantidadDeLlegadas; i++) {
                Ninio ninio = new Ninio(contadorDeNinios++, minuto);
                niniosRetenidos[cantidadRetenidos++] = ninio;
            }
        }
    }

    private String generarMensajeAleatorio(int longitud) {
        String mensaje = "";
        for (int i = 0; i < longitud; i++) {
            mensaje += (char) ('a' + numeroAleatorio.nextInt(26));
        }
        return mensaje;
    }

    private String mutarMensaje(String mensaje) {
        double probabilidad = numeroAleatorio.nextDouble();
        int cantidadDeCambios;
        if (probabilidad < probabilidadCambioCero) cantidadDeCambios = 0;
        else if (probabilidad < probabilidadCambioCero + probabilidadCambioUno) cantidadDeCambios = 1;
        else cantidadDeCambios = 2;

        if (cantidadDeCambios == 0) return mensaje;

        char[] arreglo = mensaje.toCharArray();
        for (int i = 0; i < cantidadDeCambios; i++) {
            int posicion = numeroAleatorio.nextInt(arreglo.length);
            char caracterViejo = arreglo[posicion];
            char caracterNuevo;
            do {
                caracterNuevo = (char) ('a' + numeroAleatorio.nextInt(26));
            } while (caracterNuevo == caracterViejo);
            arreglo[posicion] = caracterNuevo;
        }
        return new String(arreglo);
    }

    private int calcularDistanciaDeHamming(String mensajeA, String mensajeB) {
        if (mensajeA.length() != mensajeB.length()) return -1;
        int contador = 0;
        for (int i = 0; i < mensajeA.length(); i++) {
            if (mensajeA.charAt(i) != mensajeB.charAt(i)) contador++;
        }
        return contador;
    }

    public void imprimirResumen() {
        System.out.println("Total de niños llegados: " + (contadorDeNinios - 1));
        System.out.println("Total de juegos jugados: " + cantidadDeJuegos);
        for (int i = 0; i < cantidadDeJuegos; i++) {
            System.out.println(listaDeJuegos[i]);
        }
        System.out.println("Niños que quedaron en la cola al final: " + cantidadEnCola);
        if (cantidadEnCola > 0) {
            for (int i = 0; i < cantidadEnCola; i++) {
                System.out.print(colaDeNinios[i].obtenerIdentificador() + " ");
            }
            System.out.println();
        }
    }
}
