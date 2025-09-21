package reto001;

import java.util.*;

public class Juego {
    private List<Niño> jugadores;
    private String mensajeOriginal;
    private String mensajeFinal;
    private int duracion;

    public Juego(List<Niño> jugadores, String mensajeOriginal) {
        this.jugadores = jugadores;
        this.mensajeOriginal = mensajeOriginal;
    }

    public void jugar(Random rand) {
        String mensaje = mensajeOriginal;
        duracion = 1;

        for (Niño niño : jugadores) {
            duracion++;
            mensaje = niño.copiarMensaje(mensaje, rand);
        }

        duracion++;
        mensajeFinal = mensaje;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getMensajeOriginal() {
        return mensajeOriginal;
    }

    public String getMensajeFinal() {
        return mensajeFinal;
    }

    public int getNumJugadores() {
        return jugadores.size();
    }
}
