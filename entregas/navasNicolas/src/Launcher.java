public class Launcher {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
        Juego juego = new Juego();
        juego.jugar();
    }
}