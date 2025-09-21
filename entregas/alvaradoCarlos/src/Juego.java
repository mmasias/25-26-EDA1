public class Juego {

    private Monitora monitora1;
    private Monitora monitora2;
    private Niño[] niños;
    private int duracionEnHoras;
    private Pizarra[] pizarrines;
    private Pizarra pizarra;

    public Juego(int duracionEnHoras) {
        this.duracionEnHoras = duracionEnHoras;
        this.monitora1 = new Monitora("Lydia", 0);
        this.monitora2 = new Monitora("Aisha", 0);
        this.pizarra = new Pizarra();
        this.pizarrines = new Pizarra[5];
    }

    public void iniciar() {

        int minutosTotales = convertirHorasAMinutos(duracionEnHoras);

        for (int minuto = 0; minuto < minutosTotales; minuto++) {
            monitora1.recibeNiños(minuto);

            if (monitora1.cantidadDeNiños() >= 5) {
                niños = monitora1.entregaNiños(monitora2);
                if (monitora2.cantidadDeNiños() == 5) {
                    monitora2.entregaPizarrin(pizarrines, niños);
                    if (todosTienenPizarrin()) {
                        monitora2.limpiaPizarra(pizarra);
                        monitora2.pideLimpiarPizarrines(pizarrines, niños);

                        String mensaje = monitora2.escribeMensaje(pizarra);

                        niños[0].recibeMensaje(mensaje, pizarrines[0]);
                        for (int i = 0; i < niños.length - 1; i++) {
                            niños[i].muestraMensaje(mensaje, niños[i + 1]);
                            niños[i + 1].recibeMensaje(mensaje, pizarrines[i + 1]);
                        }

                        niños[niños.length - 1].escribeEnPizarra(pizarra, mensaje);
                        monitora2.terminaJuegoActual();
                        System.out.println("Un juego terminó en el minuto " + minuto);
                    }
                }
            }
        }

        System.out.println("¡Tiempo terminado!");
    }

    private int convertirHorasAMinutos(int duracionEnHoras) {
        return duracionEnHoras * 60;
    }

    private boolean todosTienenPizarrin() {
        if (niños == null)
            return false;
        for (Niño niño : niños) {
            if (niño == null || !niño.tienePizarrin())
                return false;
        }
        return true;
    }

}
