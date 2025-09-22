import java.util.Random;

public class JuegoTelefono {
    private static final double PROBABILIDAD_ERROR = 0.30;

    public static void jugarUnaRonda(int numeroNinosEnCola, Random generadorAzar) {
        char[] mensajeOriginal = generarMensajeAleatorio(10, generadorAzar);
        char[] mensajeActual = copiarArreglo(mensajeOriginal);

        int indiceNino = 0;
        while (indiceNino < numeroNinosEnCola) {
            boolean cometeError = generadorAzar.nextDouble() < PROBABILIDAD_ERROR;

            if (cometeError) {
                int numeroCambios = 1 + generadorAzar.nextInt(2);
                int cambiosRealizados = 0;

                while (cambiosRealizados < numeroCambios) {
                    int posicionLetra = generadorAzar.nextInt(mensajeActual.length);
                    char nuevaLetra = letraAleatoria(generadorAzar);

                    while (nuevaLetra == mensajeActual[posicionLetra]) {
                        nuevaLetra = letraAleatoria(generadorAzar);
                    }

                    mensajeActual[posicionLetra] = nuevaLetra;
                    cambiosRealizados = cambiosRealizados + 1;
                }
            }

            indiceNino = indiceNino + 1;
        }

        String mensajeOriginalTexto = new String(mensajeOriginal);
        String mensajeFinalTexto = new String(mensajeActual);
        int diferenciasLetras = contarDiferencias(mensajeOriginal, mensajeActual);

        System.out.println("Mensaje original:  " + mensajeOriginalTexto);
        System.out.println("Mensaje final:     " + mensajeFinalTexto);
        System.out.println("Letras distintas entre original y final: " + diferenciasLetras);
    }

    private static char[] generarMensajeAleatorio(int longitud, Random generadorAzar) {
        char[] letras = new char[longitud];
        int indice = 0;
        while (indice < longitud) {
            letras[indice] = letraAleatoria(generadorAzar);
            indice = indice + 1;
        }
        return letras;
    }

    private static char letraAleatoria(Random generadorAzar) {
        int desplazamiento = generadorAzar.nextInt(26);
        char letra = (char) ('A' + desplazamiento);
        return letra;
    }

    private static char[] copiarArreglo(char[] arregloOrigen) {
        char[] copia = new char[arregloOrigen.length];
        int indice = 0;
        while (indice < arregloOrigen.length) {
            copia[indice] = arregloOrigen[indice];
            indice = indice + 1;
        }
        return copia;
    }

    private static int contarDiferencias(char[] arregloA, char[] arregloB) {
        int diferenciasLetras = 0;
        int indice = 0;
        while (indice < arregloA.length) {
            if (arregloA[indice] != arregloB[indice]) {
                diferenciasLetras = diferenciasLetras + 1;
            }
            indice = indice + 1;
        }
        return diferenciasLetras;
    }
}
