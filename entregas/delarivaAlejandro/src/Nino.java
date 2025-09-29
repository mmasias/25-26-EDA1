import java.util.Random;

public class Nino {

    private char[] tablero = new char[10];
    private Random rng = new Random();

    public void recibirMensaje(char[] mensaje) {
        tablero = mensaje.clone();
        System.out.println("Niño recibe mensaje: " + new String(mensaje));
    }

    public String obtenerMensaje() {
        return new String(tablero);
    }

    public void mostrar(Nino siguiente) {
        System.out.println("Niño muestra mensaje: " + obtenerMensaje());
        char[] alterado = alterarMensaje();
        System.out.println("Mensaje alterado: " + new String(alterado));
        siguiente.recibirMensaje(alterado);
    }

    private char[] alterarMensaje() {
        char[] copia = tablero.clone();
        int cambios = rng.nextInt(3);
        for (int i = 0; i < cambios; i++) {
            int pos = rng.nextInt(copia.length);
            copia[pos] = (char) ('A' + rng.nextInt(26));
        }
        return copia;
    }

    public void escribir(Pizarra pizarra) {
        pizarra.setMensaje(tablero);
    }

    public void limpiar() {
        tablero = new char[10];
    }
}
