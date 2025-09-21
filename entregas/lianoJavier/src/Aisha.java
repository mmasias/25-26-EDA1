import java.util.Random;

public class Aisha {

    private Fila fila = new Fila();
    private char[] palabraInicial = new char[10];
    private Random random = new Random();

    public Fila getFila() {
        return fila;
    }

    public void pideNinho(Lydia lydia) {
        if (lydia.tieneEsperando()) {
            fila.agregar(lydia.darNinho());
        }
    }

    public void limpia(Pizarra pizarra) {
        pizarra.limpiar();
    }

    public void pideLimpiarPizarrines() {
        for (Ninho n : fila.toArray()) {
            n.limpiarPizarrin();
        }
    }

    public void muestraPizarrin(Ninho ninho) {
        ninho.recibeMensaje(palabraInicial.clone());
    }

    public void escribePalabra() {
        palabraInicial = generarPalabra();
    }

    private char[] generarPalabra() {
        char[] palabra = new char[10];
        for (int i = 0; i < 10; i++) {
            palabra[i] = (char) ('a' + random.nextInt(26));
        }
        return palabra;
    }

    public Ninho[] sientaNinhos() {
        return fila.toArray();
    }

}
