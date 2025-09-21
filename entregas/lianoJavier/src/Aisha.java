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
        System.out.println("Aisha muestra palabra inicial: " + new String(palabraInicial));
        ninho.recibeMensaje(palabraInicial.clone());
    }

    public void escribePalabra() {
        palabraInicial = generarPalabra();
        System.out.println("Aisha escribe palabra: " + new String(palabraInicial));
    }

    private char[] generarPalabra() {
        return "lossecreto".toCharArray();
    }

    public Ninho[] sientaNinhos() {
        return fila.toArray();
    }

    public void imprimirLista() {
        fila.imprimirLista();
    }

}
