
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
            tiempo.imprimir();
            lydia.recibeNiño(tiempo);
            lydia.imprimirLista();
            aisha.imprimirLista();

            if (!aisha.getFila().estaCompleto()) {
                System.out.println("Aisha pide niño a Lydia porque fila no completa");
                aisha.pideNinho(lydia);
            } else if (!juego.estaIniciado()) {
                System.out.println("Fila completa, inicia juego");
                juego.inicia();
                ninhos = aisha.sientaNinhos();
                aisha.limpia(pizarra);
                aisha.pideLimpiarPizarrines();
            } else {
                if (juego.getPosicion() == -1) {
                    System.out.println("Inicio del juego: Aisha escribe palabra y muestra al primer niño");
                    aisha.escribePalabra();
                    aisha.muestraPizarrin(ninhos[0]);
                    juego.siguiente();
                } else if (juego.getPosicion() < ninhos.length - 1) {
                    System.out.println("Niño " + juego.getPosicion() + " muestra al siguiente");
                    ninhos[juego.getPosicion()].muestraPizarrin(ninhos[juego.getPosicion() + 1]);
                    juego.siguiente();
                } else {
                    System.out.println("Último niño escribe en pizarra");
                    ninhos[juego.getPosicion()].escribe(pizarra);
                    pizarra.imprimir();
                    System.out.println("Tiempo: " + tiempo.horas() + ":" + String.format("%02d", tiempo.minutos()));
                    juego.termina();
                    System.out.println("Juego termina, vacía fila de Aisha y pasa esperando");
                    aisha.getFila().vaciar();
                    while (lydia.tieneEsperando() && !aisha.getFila().estaCompleto()) {
                        aisha.pideNinho(lydia);
                    }
                }
            }
            System.out.println("=======");
            tiempo.pasarMinuto();
        }
    }

    private static boolean estaAbierto(Tiempo tiempo) {
        return tiempo.horas() < TIEMPO_ABIERTO_HORAS;
    }

}