import java.util.Random;

public class TelefonoDescacharrado {
    private Random random = new Random();

    public String deformarMensaje(String mensaje) {
        String resultado = mensaje;
        int errores = random.nextInt(3);

        for (int i = 0; i < errores; i++) {
            int pos = random.nextInt(resultado.length());
            char nueva = (char) ('A' + random.nextInt(26));
            String nuevo = "";
            for (int j = 0; j < resultado.length(); j++) {
                if (j == pos) {
                    nuevo = nuevo + nueva;
                } else {
                    nuevo = nuevo + resultado.charAt(j);
                }
            }
            resultado = nuevo;
        }
        
        return resultado;
    }
}
