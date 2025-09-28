public class Escenario {

    private static final int DURACION_MINUTOS = 120;
    private static final int MINUTOS_LLEGADAS = 30;

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        Monitora aisha = new Monitora("Aisha");
        Monitora lydia = new Monitora("Lydia");

        ludoteca.setReceptora(lydia);
        ludoteca.setOrganizadora(aisha);

        System.out.println("Simulación: la ludoteca abre y funcionan " + DURACION_MINUTOS + " minutos.");

        int minuto = 0;
        int contadorNinos = 0;

        while (minuto < DURACION_MINUTOS) {
            int llegadas = calcularLlegadas(minuto);
            for (int i = 0; i < llegadas; i++) {
                contadorNinos++;
                Nino n = new Nino("Nino" + contadorNinos);
                lydia.recibirNino(n);
            }

            if (!ludoteca.isJuegoEnCurso() && ludoteca.numeroDeNinos() >= Ludoteca.MIN_NINOS_PARA_JUEGO) {
                Nino[] participantes = ludoteca.getFilaArray();
                int duracionJuego = participantes.length + 2;

                System.out.println("\n==> Se inicia un juego con " + participantes.length + " niños. Duración prevista: " + duracionJuego + " minutos (minuto actual: " + minuto + ").\n");

                ludoteca.setJuegoEnCurso(true);

                for (int t = 0; t < duracionJuego; t++) {
                    minuto++;
                    if (minuto >= DURACION_MINUTOS) {
                        continue;
                    }
                    if (minuto < MINUTOS_LLEGADAS) {
                        int llegadasDurante = calcularLlegadas(minuto);
                        for (int j = 0; j < llegadasDurante; j++) {
                            contadorNinos++;
                            Nino nn = new Nino("Nino" + contadorNinos);
                            lydia.recibirNino(nn);
                        }
                    }
                }

                Juego juego = new Juego(ludoteca);
                juego.iniciar(participantes);

                ludoteca.setJuegoEnCurso(false);
                ludoteca.transferirNinosDesdeReceptora();

                System.out.println("\n==> Juego finalizado. Minuto actual: " + minuto + ".\n");

                continue;
            }

            minuto++;
        }

        System.out.println("Fin de la simulación. Llegaron en total " + contadorNinos + " niños a la ludoteca.");
    }

    private static int calcularLlegadas(int minuto) {
        if (minuto >= 0 && minuto <= 9) {
            return (int) Math.floor(Math.random() * 3);
        }
        if (minuto >= 10 && minuto <= 29) {
            if ((minuto - 10) % 3 == 0) {
                return (Math.random() < 0.5) ? 1 : 0;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
