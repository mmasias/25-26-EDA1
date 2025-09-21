public class Launcher {
    public static void main(String[] args) {
        telefonoDescacharrado juego = new telefonoDescacharrado(5);

        juego.agregarNiño(new Niño("Ana", "quitarvocales"));
        juego.agregarNiño(new Niño("Luis", "sustituirsimbolos"));
        juego.agregarNiño(new Niño("Marta", "eliminarpalabra"));
        juego.agregarNiño(new Niño("Pedro", "quitarvocales"));
        juego.agregarNiño(new Niño("Sofía", "sustituirsimbolos"));

        juego.jugarVariasRondas(3);
    }
}
