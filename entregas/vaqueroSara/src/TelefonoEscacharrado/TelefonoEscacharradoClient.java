package EDA1.TelefonoEscacharrado;

public class TelefonoEscacharradoClient {
    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();

        ludoteca.lleganNinos();
        
        ludoteca.iniciarJuego();

        ludoteca.getResultados();
    }
}
