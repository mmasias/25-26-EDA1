public class Juego {

    private Monitora monitora1;
    private Monitora monitora2;
    private Pizarra[] pizarrines;
    private Pizarra pizarra;
    private int duracionEnHoras;

    public Juego(int duracionEnHoras) {
        this.duracionEnHoras = duracionEnHoras;
        this.monitora1 = new Monitora("Lydia", 10);
        this.monitora2 = new Monitora("Aisha", 5);
        this.pizarra = new Pizarra();
        this.pizarrines = new Pizarra[5];

        for (int i = 0; i < 5; i++) {
            pizarrines[i] = new Pizarra();
        }
    }

    public void iniciar() {
        int minutosTotales = convertirHorasAMinutos(duracionEnHoras);

        for (int minuto = 0; minuto < minutosTotales; minuto++) {

            monitora1.recibeNiños(minuto);

            while (monitora1.cantidadDeNiños() >= 5) {
                Niño[] grupo = monitora1.entregaNiños();

                monitora2.entregaPizarrin(pizarrines, grupo);

                if (todosTienenPizarrin(grupo)) {
                    monitora2.limpiaPizarra(pizarra);
                    monitora2.pideLimpiarPizarrines(pizarrines, grupo);

                    String mensaje = monitora2.escribeMensaje(pizarra);

                    grupo[0].recibeMensaje(mensaje, pizarrines[0]);
                    for (int i = 0; i < grupo.length - 1; i++) {
                        grupo[i].muestraMensaje(mensaje, grupo[i + 1]);
                        grupo[i + 1].recibeMensaje(mensaje, pizarrines[i + 1]);
                    }

                    grupo[grupo.length - 1].escribeEnPizarra(pizarra, mensaje);

                    monitora2.terminaJuegoActual();
                    System.out.println("Juego completado en el minuto " + minuto);
                }
            }
        }

        System.out.println("¡Tiempo terminado!");
    }

    private int convertirHorasAMinutos(int duracionEnHoras) {
        return duracionEnHoras * 60;
    }

    private boolean todosTienenPizarrin(Niño[] grupo) {
        for (Niño niño : grupo) {
            if (!niño.tienePizarrin()) return false;
        }
        return true;
    }
}
