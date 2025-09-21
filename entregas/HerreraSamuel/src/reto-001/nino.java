import java.util.Random;

public class Nino {
    private String pizarrin = "";

    public String recibirMensaje(String mensaje) {
        String deformado = deformarMensaje(mensaje);
        this.pizarrin = deformado;
        return deformado;
    }

    private String deformarMensaje(String mensaje) {
        Random rand = new Random();
        char[] letras = mensaje.toCharArray();
        int cambios = rand.nextInt(3); // 0,1 o 2 cambios
        for (int i = 0; i < cambios; i++) {
            int pos = rand.nextInt(letras.length);
            letras[pos] = (char) ('A' + rand.nextInt(26));
        }
        return new String(letras);
    }
}
