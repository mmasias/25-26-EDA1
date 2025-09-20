package EDA1.TelefonoEscacharrado;

public class TelefonoEscacharrado {

    int tiempo = 0;
    int numNinos = 0; 
    boolean juegoIniciado;
    Nino[] ninosParticipando = new Nino[100]; 
    Profesora profesora = new Profesora(); 

    public void lleganNinos() {
        int llegadas;

        if (tiempo < 10) {
            llegadas = (int) (Math.random() * 3); 
        } else if (tiempo < 20) {
            llegadas = (Math.random() < 0.5) ? 1 : 0; 
        } else {
            llegadas = 0; 
        }

        for (int i = 0; i < llegadas; i++) {
            if (numNinos < ninosParticipando.length) {
                ninosParticipando[numNinos] = new Nino();
                numNinos++;
            }
        }

        if (numNinos > 5 && !juegoIniciado) {
            juegoIniciado = true;
            prepararNinos();
        }

        tiempo++;
    }

    public void prepararNinos() {
        for (int i = 0; i < numNinos; i++) {
            ninosParticipando[i].hacerFila();
            ninosParticipando[i].borrarPizarrin();
        }
    }

    public void jugar() {
        profesora.escribirMensaje();
        for (int i = 0; i < numNinos; i++) {
            ninosParticipando[i].escribirMensaje();
        }
    }

}
