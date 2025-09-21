

class Mensaje {
    private String texto;

    public Mensaje(String texto) {
        this.texto = texto;
    }

    
    public static Mensaje generar(int longitud) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(letras.charAt(i % letras.length()));
        }
        return new Mensaje(sb.toString());
    }

    
    public void deformar(int pos) {
        if (texto.length() == 0) return;

        int posicion = pos % texto.length();
        String antes = texto.substring(0, posicion);
        String despues = "";
        if (posicion < texto.length() - 1) {
            despues = texto.substring(posicion + 1);
        }
        texto = antes + "X" + despues;
    }

    public String getTexto() {
        return this.texto;
    }
}
