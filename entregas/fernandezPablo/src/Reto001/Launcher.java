public class Launcher {
    public static void main(String[] args) {
        TelefonoDescacharrado juego = new TelefonoDescacharrado(3);

        juego.agregarNiño(new Niño("Ana", "quitarvocales"));
        juego.agregarNiño(new Niño("Luis", "sustituirsimbolos"));
        juego.agregarNiño(new Niño("Marta", "eliminarpalabra"));

        juego.jugarUnaRonda();
    }
}
