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
            System.out.println("Llegan " + llegadas + " niños. La profe Lydia les saluda"); 
        } else if (tiempo < 20) {
            llegadas = (Math.random() < 0.5) ? 1 : 0; 
            System.out.println("Llegan " + llegadas + " niños. La profe Lydia les saluda");
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

    }

    public void prepararNinos() {
        for (int i = 0; i < numNinos; i++) {
            ninosParticipando[i].hacerFila();
            ninosParticipando[i].borrarPizarrin();
        }
    }

    public void jugar() {
        profesora.setMensajeOriginal();
        for (int i = 0; i < numNinos; i++) {
            ninosParticipando[i].escribirMensaje();
        }
    }

    public void getResultados() {
       
    }

    tiempo++;

}
