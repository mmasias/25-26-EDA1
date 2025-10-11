
public class Ludoteca {

    private static final int TIEMPO_ABIERTO = 2;
    private static final int MINUTOS_POR_HORA = 60;

    Tiempo tiempo;

    Monitora lydia = new Monitora("Lydia");
    Monitora aisha = new Monitora("Aisha");
    Monitora dalsy = new Monitora("Dalsy");

    public Ludoteca(Tiempo tiempo) {
        this.tiempo = tiempo;
    }

    public void actualizar() {
        if (aisha.estaJugando())
            aisha.siguienteRonda();
    }

    public void recibe(Niño niño) {
        lydia.recibe(niño);
        if (!aisha.estaJugando()) {
            lydia.pasarNiñosA(aisha);
            if (aisha.puedeJugar())
                aisha.juega();
        }
    }

    public boolean abierta() {
        return tiempo.getMinutosTotales() < TIEMPO_ABIERTO * MINUTOS_POR_HORA;
    }

}
