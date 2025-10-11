
public class Niño {

    private String nombre;
    private int edad;

    private Monitora monitora;
    private Niño siguienteNiño;

    private String pizarrin;
    private boolean haJugado;

    private static final String ABECEDARIO = "abcdefghijklmnopqrstuvwxyz";

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void lee(String mensaje) {
        anotar(mensaje);
    }

    public void anotar(String mensaje) {
        pizarrin = alterar(mensaje);
    }

    private String alterar(String mensaje) {
        char[] caracteres = mensaje.toCharArray();
        int errores = 1 + azar(2);

        for (int i = 0; i < errores; i++) {
            int posicion = azar(caracteres.length);
            char original = caracteres[posicion];
            char reemplazo;

            do {
                reemplazo = ABECEDARIO.charAt(azar(ABECEDARIO.length()));
            } while (Character.toLowerCase(original) == reemplazo);

            caracteres[posicion] = Character.isUpperCase(original)
                    ? Character.toUpperCase(reemplazo)
                    : reemplazo;
        }
        return new String(caracteres);
    }

    private int azar(int limite) {
        return (int) (Math.random() * limite);
    }

    public void limpiarPizarrin() {
        pizarrin = "";
        siguienteNiño.limpiarPizarrin();
    }

    public void jugar() {
        if (!haJugado) {
            enseñar(pizarrin, siguienteNiño);
            haJugado = true;
        } else
            if (siguienteNiño != null) {
                siguienteNiño.jugar();
            } else {
                Console.imprimirln(pizarrin);
                monitora.terminarJuego();
            }
    }

    private void enseñar(String pizarrin, Niño niño) {
        niño.lee(pizarrin);
    }

    public void recibe(Niño niño) {
        if (siguienteNiño == null)
            siguienteNiño = niño;
        else
            siguienteNiño.recibe(niño);
    }

    public void estaCon(Monitora monitora) {
        this.monitora = monitora;
    }

}
