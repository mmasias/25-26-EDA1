public class Escenario {

    private static final int DURACION_MINUTOS = 120;
    private static final int MINUTOS_LLEGADAS = 30;

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        Monitora aisha = new Monitora("Aisha");
        Monitora lydia = new Monitora("Lydia");
        Monitora dalsy = new Monitora("Dalsy");

        ludoteca.setReceptora(lydia);
        ludoteca.setOrganizadora(aisha);

        System.out.println("Simulación: la ludoteca abre durante " + DURACION_MINUTOS + " minutos.");

        int minuto = 0;
        int contadorNinos = 0;

        String[] nombresDisponibles = {
            "Lucas", "María", "Hugo", "Sofía", "Leo", "Paula",
            "Mateo", "Lucía", "Daniel", "Valeria", "Pablo", "Carla",
            "Adrián", "Emma", "Álvaro", "Julia", "Diego", "Lara",
            "David", "Marta", "Noa", "Irene", "Celia", "Samuel"
        };

        while (minuto < DURACION_MINUTOS) {

            if (minuto > 60) {
                System.out.println("\n🚨 ¡ALARMA CONTRA INCENDIOS! Se activa el protocolo de emergencia (minuto 60) 🚨");

                if (dalsy != null && aisha != null) {
                    dalsy.pasarNinosAOtra(aisha);
                }

                if (aisha != null && lydia != null) {
                    aisha.pasarNinosAOtra(lydia);
                }

                System.out.println("\n--- Estado tras protocolo de emergencia ---");
                if (lydia != null) lydia.mostrarEstadoCola();
                if (aisha != null) aisha.mostrarEstadoCola();
                if (dalsy != null) dalsy.mostrarEstadoCola();
                break;
            }


            int llegadas = calcularLlegadas(minuto);
            for (int i = 0; i < llegadas && contadorNinos < nombresDisponibles.length; i++) {
                String nombre = nombresDisponibles[contadorNinos];
                Nino n = new Nino(nombre, 3 + (int) (Math.random() * 8));
                lydia.recibirNino(n);
                contadorNinos++;
            }

            Monitora rec = ludoteca.getReceptora();
            int ninosEnCola = (rec != null) ? rec.numeroDeNinosEnCola() : 0;

            if (!ludoteca.isJuegoEnCurso() && ninosEnCola >= Ludoteca.MIN_NINOS_PARA_JUEGO) {
                Nino[] participantes = (rec != null) ? rec.getFilaArray() : new Nino[0];
                int duracionJuego = participantes.length + 2;

                System.out.println("\n==> Se inicia un juego con " + participantes.length + " niños. Duración prevista: " + duracionJuego + " minutos (minuto actual: " + minuto + ").\n");

                ludoteca.setJuegoEnCurso(true);

                Juego juego = new Juego(ludoteca);
                juego.prepararJuegoRana(dalsy);

                for (int t = 0; t < duracionJuego; t++) {
                    minuto++;
                    if (minuto >= DURACION_MINUTOS) break;
                    if (minuto < MINUTOS_LLEGADAS) {
                        int llegadasDurante = calcularLlegadas(minuto);
                        for (int j = 0; j < llegadasDurante && contadorNinos < nombresDisponibles.length; j++) {
                            String nombre = nombresDisponibles[contadorNinos];
                            Nino nn = new Nino(nombre, 3 + (int) (Math.random() * 8));
                            lydia.recibirNino(nn);
                            contadorNinos++;
                        }
                    }
                }

                juego.terminarJuegoRana(dalsy);

                ludoteca.setJuegoEnCurso(false);
                ludoteca.transferirNinosDesdeReceptora();

                System.out.println("\n==> Juego finalizado. Minuto actual: " + minuto + ".\n");

                continue;
            }

            minuto++;
        }


        System.out.println("\nFin de la simulación. Llegaron en total " + contadorNinos + " niños a la ludoteca.\n");

        lydia.mostrarEstadoCola();
        aisha.mostrarEstadoCola();
        dalsy.mostrarEstadoCola();

        lydia.presentarse();
        lydia.pedirPresentacionesTodos();
        lydia.pedirPresentacionesMayoresDe(7);
        dalsy.pedirPresentacionesPorLetra('L');
        aisha.decirNumeroEnCola();
        dalsy.decirEdadPromedio();

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
