import java.io.Console;
import java.util.Random;

class Niño {
    private static int contador = 0;
    private final int id;
    private final String nombre;
    private Pizarra pizarrin;
    private int erroresIntroducidos = 0;

    public Niño(String nombre) {
        this.id = ++contador;
        this.nombre = nombre;
        this.pizarrin = new Pizarra();
    }

    public void recibirPizarrin(Pizarra pizarra) {
        this.pizarrin = pizarra != null ? pizarra : new Pizarra();
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void recibirMensaje(String mensaje, Random rnd) {
        String modificado = modificarMensaje(mensaje, rnd);
        pizarrin.escribirMensaje(modificado);
        new Console().writeln("[" + nombre + "] recibe [" + mensaje + "] y escribe [" + pizarrin.leerMensaje() + "]");
        erroresIntroducidos = calcularDiferencia(mensaje, modificado);
    }

    private String modificarMensaje(String mensaje, Random rnd) {
        if (mensaje == null || mensaje.length() == 0) return "";
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
        int errores = rnd.nextInt(2) + 1;
        String resultado = mensaje.toLowerCase(); // Normalizar a minúsculas para consistencia
        for (int i = 0; i < errores; i++) {
            if (resultado.length() > 0) {
                int indice = rnd.nextInt(resultado.length());
                char letra = ALFABETO.charAt(rnd.nextInt(ALFABETO.length()));
                resultado = resultado.substring(0, indice) + letra + resultado.substring(indice + 1);
            }
        }
        return resultado;
    }

    private int calcularDiferencia(String original, String modificado) {
        int diff = 0;
        for (int i = 0; i < Math.min(original.length(), modificado.length()); i++) {
            if (original.charAt(i) != modificado.charAt(i)) diff++;
        }
        return diff + Math.abs(original.length() - modificado.length());
    }

    public String mostrarMensaje() {
        return pizarrin.leerMensaje();
    }

    public void limpiarPizarrin() {
        pizarrin.limpiar();
        erroresIntroducidos = 0;
    }

    public int getErroresIntroducidos() {
        return erroresIntroducidos;
    }

    @Override
    public String toString() {
        return "Niño{id=" + id + ", nombre='" + nombre + "'}";
    }
}