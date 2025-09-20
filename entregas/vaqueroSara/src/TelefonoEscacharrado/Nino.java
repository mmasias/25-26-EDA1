public class Nino {

    public void hacerFila() {
        System.out.println("El niño se pone en la fila.");
    }

    public void borrarPizarrin() {
        System.out.println("El niño limpia su pizarrín.");
    }

    public String escribirMensaje(String mensajeAnterior) {
        char[] chars = mensajeAnterior.toCharArray();
        int cambios = (int) (Math.random() * 3);
        for (int c = 0; c < cambios; c++) {
            if (chars.length > 0) {
                int idx = (int) (Math.random() * chars.length);
                char nuevaLetra = (char) ('a' + (int) (Math.random() * 26));
                chars[idx] = nuevaLetra;
            }
        }
        String mensajeModificado = new String(chars);
        System.out.println("El niño escribe: " + mensajeModificado);
        return mensajeModificado;
    }

}
