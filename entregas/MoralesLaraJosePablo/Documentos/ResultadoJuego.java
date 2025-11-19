public class ResultadoJuego {
    private int numeroJuego;
    private String mensajeOriginal;
    private String mensajeFinal;
    private int cantidadNiños;
    private int minutoInicio;
    
    public ResultadoJuego(int numeroJuego, String mensajeOriginal, String mensajeFinal, 
                         int cantidadNiños, int minutoInicio) {
        this.numeroJuego = numeroJuego;
        this.mensajeOriginal = mensajeOriginal;
        this.mensajeFinal = mensajeFinal;
        this.cantidadNiños = cantidadNiños;
        this.minutoInicio = minutoInicio;
    }
    
    public String getMensajeOriginal() { 
        return mensajeOriginal; 
    }
    
    public String getMensajeFinal() { 
        return mensajeFinal; 
    }
    
    public String toString() {
        double precision = 0.0;
        if (mensajeOriginal.length() == mensajeFinal.length()) {
            int correctas = 0;
            for (int i = 0; i < mensajeOriginal.length(); i++) {
                if (mensajeOriginal.charAt(i) == mensajeFinal.charAt(i)) {
                    correctas++;
                }
            }
            precision = (correctas * 100.0) / mensajeOriginal.length();
        }
        
        String precisionStr = String.valueOf(Math.round(precision * 10.0) / 10.0);
        
        return "Juego " + numeroJuego + " (min " + minutoInicio + "): \"" + 
               mensajeOriginal + "\" -> \"" + mensajeFinal + "\" (" + 
               cantidadNinos + " niños, " + precisionStr + "% precision)";
    }
}