
public class Ludoteca {

    private static final int TIEMPO_ABIERTO_HORAS = 2;

    public static void abrir() {
        Tiempo tiempo = new Tiempo();

        Lydia lydia = new Lydia();
        Aisha aisha = new Aisha();

        Pizarra pizarra = new Pizarra();

        Juego juego = new Juego();

        Ninho[] ninhos = null;

        while (estaAbierto(tiempo)) {
            lydia.recibeNiño(tiempo);

            if (!aisha.getFila().estaCompleto()) {
                aisha.pideNinho(lydia);
            } else if (!juego.estaIniciado()) {
                juego.inicia();
                ninhos = aisha.sientaNinhos();
                aisha.limpia(pizarra);
                aisha.pideLimpiarPizarrines();
            } else {
                if (juego.getPosicion() == -1) {
                    aisha.escribePalabra();
                    aisha.muestraPizarrin(ninhos[0]);
                    juego.siguiente();
                } else if (juego.getPosicion() < ninhos.length - 1) {
                    ninhos[juego.getPosicion()].muestraPizarrin(ninhos[juego.getPosicion() + 1]);
                    juego.siguiente();
                } else {
                    ninhos[juego.getPosicion()].escribe(pizarra);
                    pizarra.imprimir();
                    juego.termina();
                    while (lydia.tieneEsperando()) {
                        aisha.pideNinho(lydia);
                    }
                }
            }
            tiempo.pasarMinuto();
        }
    }

    private static boolean estaAbierto(Tiempo tiempo) {
        return tiempo.horas() < TIEMPO_ABIERTO_HORAS;
    }

}