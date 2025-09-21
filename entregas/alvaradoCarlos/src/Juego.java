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
        int minuto = 0;

        while (minuto < minutosTotales) {

            monitora1.recibeNiños(minuto);

            while (monitora1.cantidadDeNiños() >= 5) {
                Niño[] grupo = monitora1.entregaNiños();

                monitora2.recibeNiñosParaJugar(grupo);

                monitora2.entregaPizarrin(pizarrines, grupo);

                if (todosTienenPizarrin(grupo)) {

                    monitora2.limpiaPizarra(pizarra);
                    monitora2.pideLimpiarPizarrines(pizarrines, grupo);

                    Mensaje resultado = monitora2.escribeMensaje(pizarra);
                    System.out.println("En el minuto " + minuto);
                    String mensaje = resultado.texto;

                    String mensajeActual = mensaje;
                    for (int i = 0; i < grupo.length - 1; i++) {
                        mensajeActual = grupo[i].muestraMensaje(mensajeActual, grupo[i + 1]);
                    }
                    grupo[grupo.length - 1].escribeEnPizarra(pizarra, mensajeActual);

                    monitora2.terminaJuegoActual();
                    System.out.println("✅ Juego completado en el minuto " + minuto);
                }
            }

            minuto++;
        }

        System.out.println("¡Tiempo terminado! con tiempo de " + minuto + " minutos");
    }

    private int convertirHorasAMinutos(int duracionEnHoras) {
        return duracionEnHoras * 60;
    }

    private boolean todosTienenPizarrin(Niño[] grupo) {
        for (Niño niño : grupo) {
            if (!niño.tienePizarrin())
                return false;
        }
        return true;
    }
}
