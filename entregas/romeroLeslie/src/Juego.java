class Juego {
    private Nino[] participantes;
    private String mensaje;
    private String mensajeOriginal;

    public Juego(Nino[] participantes) {
        this.participantes = participantes;
        this.mensajeOriginal = generarMensaje();
        this.mensaje = mensajeOriginal;
    }

    public String mensajeOriginal() {
        return mensajeOriginal;
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mensaje = "";
        for (int i = 0; i < 10; i++) {
            int indice = (int)(Math.random() * letras.length());
            mensaje = mensaje + letras.charAt(indice);
        }
        return mensaje;
    }

    private String copiarConErrores(String m) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] nuevo = m.toCharArray();

       
        int cambios = (int)(Math.random() * 3); 
        for (int c = 0; c < cambios; c++) {
            int pos = (int)(Math.random() * 10);
            int indiceLetra = (int)(Math.random() * letras.length());
            nuevo[pos] = letras.charAt(indiceLetra);
        }
        return new String(nuevo);
    }

    public String jugar() {
        for (int i = 0; i < participantes.length; i++) {
            mensaje = copiarConErrores(mensaje);
            participantes[i].escribir(mensaje);
        }
        return mensaje;
    }

    public int letrasCambiadas() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (mensaje.charAt(i) != mensajeOriginal.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public int duracion() {
        return 1 + participantes.length + 1;
    }
}