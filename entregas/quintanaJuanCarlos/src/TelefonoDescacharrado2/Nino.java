import java.util.Random;

public class Nino {
    private final String nombre;
    private Pizarra pizarrin;

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirPizarrin(Pizarra p) {
        this.pizarrin = p;
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) {
            pizarrin.limpiar();
        }
    }

    public String copiarMensaje(String mensajeEntrada, double pCambio1, double pCambio2, Random rng) {
        if (pizarrin == null) {
            this.pizarrin = new Pizarra();
        }
        String resultado = mensajeEntrada;

        double r = rng.nextDouble();
        if (r < pCambio2) {
            resultado = cambiarDosLetras(resultado, rng);
        } else if (r < pCambio2 + pCambio1) {
            resultado = cambiarUnaLetra(resultado, rng);
        }

        pizarrin.escribirMensaje(resultado);
        return resultado;
    }

    private String cambiarUnaLetra(String s, Random rng) {
        if (s.length() == 0) return s;
        int pos = rng.nextInt(s.length());
        char nueva = letraAZDistinta(s.charAt(pos), rng);
        return s.substring(0, pos) + nueva + s.substring(pos + 1);
    }

    private String cambiarDosLetras(String s, Random rng) {
        if (s.length() < 2) return cambiarUnaLetra(s, rng);
        int i = rng.nextInt(s.length());
        int j;
        do { j = rng.nextInt(s.length()); } while (j == i);
        char ni = letraAZDistinta(s.charAt(i), rng);
        char nj = letraAZDistinta(s.charAt(j), rng);
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, ni);
        sb.setCharAt(j, nj);
        return sb.toString();
    }

    private char letraAZDistinta(char actual, Random rng) {
        char c;
        do {
            c = (char) ('A' + rng.nextInt(26));
        } while (c == actual);
        return c;
    }

    public String mostrarMensaje() {
        return (pizarrin == null) ? "" : pizarrin.leerMensaje();
    }
}