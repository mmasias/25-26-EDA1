public class Mensaje {
    private static final int LONGITUD_ALFABETO = 26;
    private static final int PROB_CAMBIO_UNA_LETRA_PORCENTAJE = 50;
    private static final int PROB_CAMBIO_DOS_LETRAS_PORCENTAJE = 20;

    public static String generarMensajeAleatorio(int longitud) {
        char[] caracteres;
        caracteres = new char[longitud];

        int indice;
        indice = 0;
        while (indice < longitud) {
            int desplazamiento;
            desplazamiento = Utilidades.numeroEnteroAleatorio(0, LONGITUD_ALFABETO - 1);
            char letra;
            letra = (char) ('a' + desplazamiento);
            caracteres[indice] = letra;
            indice = indice + 1;
        }

        String resultado;
        resultado = new String(caracteres);
        return resultado;
    }

    public static String deformarMensaje(String mensajeOriginal) {
        String mensajeResultado;
        mensajeResultado = mensajeOriginal;

        boolean cambiaUnaLetra;
        cambiaUnaLetra = Utilidades.ocurreConPorcentaje(PROB_CAMBIO_UNA_LETRA_PORCENTAJE);

        boolean cambiaDosLetras;
        cambiaDosLetras = Utilidades.ocurreConPorcentaje(PROB_CAMBIO_DOS_LETRAS_PORCENTAJE);

        int cantidadCambios;
        cantidadCambios = 0;

        if (cambiaDosLetras) {
            cantidadCambios = 2;
        } else {
            if (cambiaUnaLetra) {
                cantidadCambios = 1;
            } else {
                cantidadCambios = 0;
            }
        }

        if (cantidadCambios > 0) {
            char[] arreglo;
            arreglo = mensajeResultado.toCharArray();

            int cambiosRealizados;
            cambiosRealizados = 0;

            while (cambiosRealizados < cantidadCambios) {
                int posicion;
                posicion = Utilidades.numeroEnteroAleatorio(0, arreglo.length - 1);

                char letraNueva;
                letraNueva = arreglo[posicion];

                boolean sigueIgual;
                sigueIgual = true;

                while (sigueIgual) {
                    int desplazamiento;
                    desplazamiento = Utilidades.numeroEnteroAleatorio(0, LONGITUD_ALFABETO - 1);
                    char candidata;
                    candidata = (char) ('a' + desplazamiento);
                    if (candidata != arreglo[posicion]) {
                        letraNueva = candidata;
                        sigueIgual = false;
                    } else {
                        sigueIgual = true;
                    }
                }

                arreglo[posicion] = letraNueva;
                cambiosRealizados = cambiosRealizados + 1;
            }

            mensajeResultado = new String(arreglo);
        }

        return mensajeResultado;
    }

    public static int contarDiferencias(String a, String b) {
        int diferencias;
        diferencias = 0;

        int longitud;
        longitud = a.length();

        int indice;
        indice = 0;
        while (indice < longitud) {
            char ca;
            ca = a.charAt(indice);
            char cb;
            cb = b.charAt(indice);
            if (ca != cb) {
                diferencias = diferencias + 1;
            }
            indice = indice + 1;
        }

        int resultado;
        resultado = diferencias;
        return resultado;
    }
}

