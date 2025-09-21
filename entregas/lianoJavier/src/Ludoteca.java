
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

            if (!aisha.getFila().estaCompleto() || !juego.estaIniciado()) {
                aisha.pideNinho(lydia);
            } else {
                if (!juego.estaIniciado()) {
                    juego.inicia();
                    ninhos = aisha.sientaNinhos();
                    aisha.limpia(pizarra);
                    aisha.pideLimpiarPizarrines();
                }

                if (ninhos != null) {
                    if (juego.getPosicion() != (ninhos.length - 1)) {
                        if (juego.getPosicion() == -1) {
                            aisha.escribePalabra();
                            aisha.muestraPizarrin(ninhos[juego.getPosicion() + 1]);
                        } else {
                            ninhos[juego.getPosicion()].muestraPizarrin(ninhos[juego.getPosicion() + 1]);
                        }
                        juego.siguiente();
                    } else {
                        ninhos[juego.getPosicion()].escribe(pizarra);
                        pizarra.imprimir();
                        juego.termina();
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