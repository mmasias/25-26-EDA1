import java.util.Random;

public class Nino {

    private String nombre;
    private Pizarrin pizarrin;
    private Random rnd;

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarrin = new Pizarrin();
        this.rnd = new Random();
    }

    public String recibirMensaje(String mensaje) {
        String mensajeCopiado = mensaje;

        int prob = rnd.nextInt(4);
        int errores = 0;
        if (prob == 0) errores = 2;
        else if (prob == 1 || prob == 2) errores = 1;

        if (errores > 0) {
            char[] chars = mensajeCopiado.toCharArray();
            for (int i = 0; i < errores; i++) {
                int pos = rnd.nextInt(chars.length);
                char letraNueva = (char) ('A' + rnd.nextInt(26));
                chars[pos] = letraNueva;
            }
            mensajeCopiado = new String(chars);
        }
        pizarrin.escribir(mensajeCopiado);
        System.out.println(nombre + " recibió: " + mensaje + " → copió: " + mensajeCopiado);

        return mensajeCopiado;
    }

    public Pizarrin getPizarrin() {
        return pizarrin;
    }

    public String getNombre() {
        return nombre;
    }
}