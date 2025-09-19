import java.util.Random;

class Mensaje {
    String texto;
    Random random = new Random();

    public Mensaje(String texto) {
        this.texto = texto;
    }
    
    public static Mensaje generar(int longitud) {
        Random random = new Random();
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        return new Mensaje(sb.toString());
    }

    
    public String deformar() {
        StringBuilder deformado = new StringBuilder(this.texto);
        int cambios = random.nextInt(3); // 0, 1 o 2 cambios
        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(texto.length());
            char letraRandom = (char) ('A' + random.nextInt(26));
            deformado.setCharAt(pos, letraRandom);
        }
        this.texto = deformado.toString();
        return this.texto;
    }

    public String getTexto() {
        return this.texto;
    }
}