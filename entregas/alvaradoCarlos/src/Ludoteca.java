public class Ludoteca {

    private static final int CAPACIDAD_MAXIMA_DE_AISHA = 5;
    private static final int CAPACIDAD_MAXIMA_DE_LYDIA = 10;
    private static final int DURACION_EN_HORAS = 8;
    private Monitora lydia;
    private Monitora aisha;
    private Pizarra[] pizarrines;
    private Pizarra pizarra;
    private int duracionEnHoras;
    private String nombre;

    public Ludoteca(String nombre) {
        this.nombre = nombre;
        this.duracionEnHoras = DURACION_EN_HORAS;
        this.lydia = new Monitora("Lydia", CAPACIDAD_MAXIMA_DE_LYDIA);
        this.aisha = new Monitora("Aisha", CAPACIDAD_MAXIMA_DE_AISHA);
        this.pizarra = new Pizarra();
        this.pizarrines = new Pizarra[CAPACIDAD_MAXIMA_DE_AISHA];

        for (int i = 0; i < CAPACIDAD_MAXIMA_DE_AISHA; i++) {
            pizarrines[i] = new Pizarra();
        }
    }

    public void iniciar() {
        int minutosTotales = convertirHorasAMinutos(duracionEnHoras);
        int minuto = 0;

        while (minuto < minutosTotales) {

            lydia.recibeNiños(minuto);

            while (lydia.cantidadDeNiños() >= 5) {
                Niño[] grupo = lydia.entregaNiños(aisha);

                aisha.entregaPizarrin(pizarrines, grupo);

                if (todosTienenPizarrin(grupo)) {
                    iniciarJuego(grupo, minuto);
                }
            }

            minuto++;
        }

        System.out.println("¡Tiempo terminado! con tiempo de " + minuto + " minutos");
    }

    private void iniciarJuego(Niño[] grupo, int minuto) {
        preperarJuego(grupo);
        String mensaje = generarMensaje();
        procesarMensajes(grupo, mensaje);
        finalizarJuego(minuto);
    }

    private void finalizarJuego(int minuto) {
        aisha.terminaJuegoActual();
        System.out.println("✅ Juego completado en el minuto " + minuto);
    }

    private void procesarMensajes(Niño[] grupo, String mensaje) {
        String mensajeActual = mensaje;
        for (int i = 0; i < grupo.length - 1; i++) {
            mensajeActual = grupo[i].muestraMensaje(mensajeActual, grupo[i + 1]);
        }
        grupo[grupo.length - 1].escribeEnPizarra(pizarra, mensajeActual);
    }

    private String generarMensaje() {
        Mensaje resultado = aisha.escribeMensaje(pizarra);
        return resultado.texto;
    }

    private void preperarJuego(Niño[] grupo) {
        aisha.limpiaPizarra(pizarra);
        aisha.pideLimpiarPizarrines(pizarrines, grupo);
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

    public String nombreLudoteca() {
        return nombre;
    }
}
