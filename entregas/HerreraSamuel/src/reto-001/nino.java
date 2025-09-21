import java.util.Random;

public class nino {
    private String pizarrin = "";

}

public String  recibirMensaje(String mensaje) {
    String deformado = deforarMensaje(mensaje);
    this.pizarrin += deformado;
    return deformado;
}

private String deforarMensaje(String mensaje){
    Randon rand = new Random();
    char [] letras = mensajse.toCharArray();
    int cambios = rand.NextInt(3);
    for (int i = 0; i < cambios; i++) {
        int pos1 = rand.NextInt(letras.length);
        letras [pos] = (char) ('A' + rand.NextInt(26));
    }
    return new String(letras);
}