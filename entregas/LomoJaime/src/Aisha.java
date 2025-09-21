import java.util.*;

public class Aisha {
    private Queue<Child> fila = new LinkedList<>();

    public void recibirChild(Child c) {
        fila.add(c);
        System.out.println("Aisha recibe a " + c.getNombre() + " y lo coloca al final de la fila");
    }

    public boolean filaCompleta() {
        return fila.size() >= 6;
    }

    public int tamañoFila() {
        return fila.size();
    }

    public void limpiarPizarraDelSalon() {
        System.out.println("Se limpia la pizarra del salon");
    }

    public void limpiarPizarrines() {
        for (Child c : fila) {
            c.limpiarPizarrin();
        }
        System.out.println("Todos los childs limpian su pizarrin");
    }

    public String escribirMensajeInicial() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        String letras = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 10; i++) {
            sb.append(letras.charAt(rand.nextInt(letras.length())));
        }
        return sb.toString();
    }

    public String pasarMensaje(String mensaje) {
        String mensajeActual = mensaje;
        List<Child> lista = new ArrayList<>(fila);
        for (Child c : lista) {
            mensajeActual = c.recibirYEscribir(mensajeActual);
        }
        return mensajeActual;
    }

    public void escribirMensajeFinalEnSalon(String mensaje) {
        System.out.println("Ultimo child escribe en la pizarra del salon: " + mensaje);
    }

    public void recibirNuevosDeLydia(Lydia lydia) {
        while (fila.size() < 6 && lydia.tieneEsperando()) {
            recibirChild(lydia.darChild());
        }
    }
}
