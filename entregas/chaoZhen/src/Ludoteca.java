import java.util.Random;

public class Ludoteca {
    private final ColaNinos colaNinos = new ColaNinos(200);
    private final Nino[] espera = new Nino[100];
    private int cantidadEspera = 0;

    public void recibirEnCola(Nino nino) {
        colaNinos.agregar(nino);
    }

    public void recibirEnEspera(Nino nino) {
        if (cantidadEspera < espera.length) espera[cantidadEspera++] = nino;
    }

    public boolean haySuficientesParaJugar() {
        return colaNinos.tamaño() > 5;
    }

    public Juego iniciarJuego(Random mensajeAleatorio) {
        Nino[] jugadores = colaNinos.listarNinos();
        int numJugadores = jugadores.length;
        colaNinos.limpiar();
        Mensaje mensaje = Mensaje.mensajeAleatorio(mensajeAleatorio);
        return new Juego(jugadores, numJugadores, mensaje);
    }

    public void moverEsperaACola() {
        for (int i = 0; i < cantidadEspera; i++)
            colaNinos.agregar(espera[i]);
        cantidadEspera = 0;
    }

    public int tamañoCola() { return colaNinos.tamaño(); }
    public int tamañoEspera() { return cantidadEspera; }
}
