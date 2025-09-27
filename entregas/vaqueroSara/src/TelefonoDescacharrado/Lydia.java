public class Lydia {
    Nino[] ninosConLydia = new Nino[100];
    int numNinosConLydia = 0;

    public void recibirNino(Nino nino) {
        if (numNinosConLydia < ninosConLydia.length) {
            ninosConLydia[numNinosConLydia++] = nino;
        }
    }

    public int pasarNinosACola(Nino[] cola, int numEnCola) {
        for (int i = 0; i < numNinosConLydia; i++) {
            if (numEnCola < cola.length) {
                cola[numEnCola++] = ninosConLydia[i];
            }
        }
        numNinosConLydia = 0;
        return numEnCola;
    }
}