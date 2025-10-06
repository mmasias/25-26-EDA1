import java.util.Random;

public class Simulacion {
}
public static void generarNiñosAleatorios(Lidia lidia, int cantidad) {
    String[] nombres = {"Ana","Luis","Carlos","Alberto","Aisha","Daisy","Miguel","Laura","Sofia","Andres",
                        "Diego","Lucia","Martin","Paula","Alex","Valeria","Nicolas","Isabel","Julian","Emma"};
    Random rand = new Random();
    for (int i = 0; i < cantidad; i++) {
        String nombre = nombres[rand.nextInt(nombres.length)];
        int edad = rand.nextInt(9) + 2;
        Niño n = new Niño(nombre, edad);
        lidia.recibirNiño(n);
        System.out.println("Llega " + n + " a la cola de Lidia");
    }
}
public static void main(String[] args) {
    Ludoteca ludoteca = new Ludoteca();
    generarNiñosAleatorios(ludoteca.getLidia(), 20);
    ludoteca.mostrarEstado();
    ludoteca.getLidia().pasarNiñosAAisha(ludoteca.getAisha());
    ludoteca.getAisha().presentarseConNiños();
    JuegoRana juego = new JuegoRana(ludoteca.getAisha(), ludoteca.getDalsy());
    if (juego.puedeJugar()) {
        juego.iniciarJuego();
        ludoteca.mostrarEstado();
        juego.finalizarJuego();
        ludoteca.mostrarEstado();
    }
}
