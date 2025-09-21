package reto001;

import java.util.*;

public class Ludoteca {
    private Llegada llegada = new Llegada();
    private Fila fila = new Fila();
    private Random rand = new Random();

    private String generarMensaje() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append((char) ('A' + rand.nextInt(26)));
        }
        return sb.toString();
    }

    public void simular(int minutosTotales) {
        int minuto = 1;
        int juegosRealizados = 0;

        while (minuto <= minutosTotales) {
            List<Niño> nuevos = llegada.generarLlegadas(minuto);
            fila.encolar(nuevos);

            if (fila.tamaño() >= 6) {
                List<Niño> jugadores = fila.vaciarFila();
                String mensajeOriginal = generarMensaje();
                Juego juego = new Juego(jugadores, mensajeOriginal);

                juego.jugar(rand);
                int duracion = juego.getDuracion();

                List<Niño> espera = new ArrayList<>();
                for (int m = 1; m < duracion; m++) {
                    int minutoDurante = minuto + m;
                    if (minutoDurante > minutosTotales) break;
                    List<Niño> nuevosDurante = llegada.generarLlegadas(minutoDurante);
                    espera.addAll(nuevosDurante);
                }

                minuto += duracion;
                fila.encolar(espera);

                juegosRealizados++;
            } else {
                minuto++;
            }
        }
    }

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        ludoteca.simular(120);
    }
}
