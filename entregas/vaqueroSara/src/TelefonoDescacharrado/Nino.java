import java.util.Random;

public class Nino {
    private String nombre;
    private Pizarra pizarrin;

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarrin = new Pizarra();
    }

    public void borrarPizarrin() {
        pizarrin.limpiar();
        System.out.println(nombre + " limpia su pizarrín.");
    }

    public String escribirMensaje(String mensajeAnterior) {
        char[] chars = mensajeAnterior.toCharArray();
        Random rnd = new Random();
        int cambios = rnd.nextInt(3); // 0, 1 o 2 cambios

        for (int c = 0; c < cambios; c++) {
            if (chars.length > 0) {
                int idx = rnd.nextInt(chars.length);
                char nuevaLetra = (char) ('a' + rnd.nextInt(26));
                chars[idx] = nuevaLetra;
            }
        }

        String mensajeModificado = new String(chars);
        pizarrin.escribirMensaje(mensajeModificado);

        System.out.println(nombre + " escribe: " + mensajeModificado);
        return mensajeModificado;
    }

    public String getNombre() {
        return nombre;
    }

    public String leerMensaje() {
        return pizarrin.leerMensaje();
    }
}
