public class TelefonoDescacharrado {
    public static void main(String[] args) {
        new TelefonoDescacharrado().jugar();
    }

    public void jugar() {
        final Simulacion simulacion = new Simulacion(120);
        simulacion.ejecutar();
    }
}

class Simulacion {
    private final Reloj reloj;
    private final Llegadas llegadas;
    private final ColaNinos cola;
    private final Lydia lidia;
    private final Aisha aisha;
    private final Estadisticas estadisticas;

    public Simulacion(int minutos) {
        reloj = new Reloj(minutos);
        llegadas = new Llegadas(reloj);
        cola = new ColaNinos();
        lidia = new Lydia();
        aisha = new Aisha(cola);
        estadisticas = new Estadisticas();
    }

    public void ejecutar() {
        while (reloj.hayTiempo()) {
            final int minuto = reloj.actual();
            final int nuevos = llegadas.generar(minuto);
            for (int i = 0; i < nuevos; i++) {
                final Nino n = new Nino();
                if (cola.juegoEnCurso()) lidia.recibir(n);
                else aisha.recibir(n);
            }

            if (!cola.juegoEnCurso() && cola.tamanio() > Constantes.MIN_PARA_JUGAR) {
                final Juego juego = new Juego(cola.extraerTodos(), reloj.semilla());
                cola.iniciarJuego(juego);
                juego.jugar();
                cola.terminarJuego();
                lidia.vaciarHacia(cola);
                estadisticas.registrar(juego);
                reloj.avanzar(juego.duracion());
            } else {
                reloj.avanzar(1);
            }
        }
        estadisticas.imprimirResumen(reloj.total());
    }
}






