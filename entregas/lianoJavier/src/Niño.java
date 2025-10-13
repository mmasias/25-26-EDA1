
public class Niño {

    private String nombre;
    private Pizarra pizarrin;

    public Niño(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void coger(Pizarra pizarrin) {
        this.pizarrin = pizarrin;
    }

    public void enseñarPizarrin(Niño niño) {
        niño.escribirEnPizarrin(niño.leer(pizarrin));
    }

    public void escribirEnPizarrin(String palabra) {
        pizarrin.escribir(palabra);
    }

    public String leer(Pizarra otroPizarrin) {
        String palabra = otroPizarrin.getTexto();
        palabra = alterarPalabra(palabra);
        return palabra;
    }

    private String alterarPalabra(String palabra) {
        final int MIN_CAMBIOS = 1;
        final int MAX_CAMBIOS = 2;
        final char LETRA_INICIAL = 'a';
        final int NUM_LETRAS = 26;

        char[] letras = palabra.toCharArray();
        java.util.Random rand = new java.util.Random();
        int cambios = obtenerNumeroDeCambios(rand, MIN_CAMBIOS, MAX_CAMBIOS);

        for (int i = 0; i < cambios; i++) {
            int pos = obtenerPosicionAleatoria(rand, letras.length);
            letras[pos] = obtenerLetraAleatoria(rand, LETRA_INICIAL, NUM_LETRAS);
        }
        return new String(letras);
    }

    private int obtenerNumeroDeCambios(java.util.Random rand, int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    private int obtenerPosicionAleatoria(java.util.Random rand, int longitud) {
        return rand.nextInt(longitud);
    }

    private char obtenerLetraAleatoria(java.util.Random rand, char letraInicial, int numLetras) {
        return (char) (letraInicial + rand.nextInt(numLetras));
    }

    public void escribirEnPizarra() {
        Console.imprimir(nombre + " escribe en la pizarra: " + pizarrin.getTexto());
        Console.imprimirLinea();
    }

}
