public class Ludoteca {

    private static final int DURACION_TOTAL_MINUTOS = 120; 
    private static final int MIN_NINOS_PARA_JUGAR = 5;

    private final Tiempo tiempo = new Tiempo();
    private final Lydia lydia = new Lydia();
    private final Aisha aisha = new Aisha();
    private final Pizarra pizarra = new Pizarra();
    private final Juego juego = new Juego();

    public void ejecutarSimulacion() {
        System.out.println("Inicia la ludoteca (simulación " + DURACION_TOTAL_MINUTOS + " minutos)");
        while (tiempo.totalMinutos() < DURACION_TOTAL_MINUTOS) {
            System.out.println("----- Minuto " + tiempo + " -----");

            
            lydia.generarLlegadas(tiempo);

           
            if (!juego.estaIniciado()) {
                while (lydia.tieneEsperando() && aisha.getFila().tamaño() <= MIN_NINOS_PARA_JUGAR) {
                    aisha.pedirNino(lydia);
                }
            }

            
            if (!juego.estaIniciado() && aisha.getFila().tamaño() > MIN_NINOS_PARA_JUGAR) {
                System.out.println("Fila completa (> " + MIN_NINOS_PARA_JUGAR + "): Aisha inicia un juego");
                Nino[] participantes = aisha.sentarNinosParaJuego(); 
                aisha.limpiarPizarra(pizarra);
                aisha.pedirLimpiarPizarrines();
                juego.iniciar(participantes.length);

                
                aisha.escribirPalabraInicial();
                aisha.mostrarPizarrinAlPrimerNino(participantes[0]);
                juego.siguientePaso();

               
                procesarJuegoConParticipantes(participantes);
            }

           
            lydia.imprimirLista();
            aisha.imprimirLista();

            tiempo.pasarMinuto();
            System.out.println();
        }
        System.out.println("La ludoteca cierra. Simulación finalizada.");
    }


    private void procesarJuegoConParticipantes(Nino[] participantes) {
        while (juego.estaIniciado()) {

            if (juego.getPosicion() >= 0 && juego.getPosicion() < participantes.length - 1) {
                int idx = juego.getPosicion();
                System.out.println("Niño " + idx + " muestra al siguiente");
                
                String mensajeTransmitido = participantes[idx].mostrarPizarrin();
                participantes[idx + 1].recibirMensaje(mensajeTransmitido);
                System.out.println("Mensaje transmitido: " + mensajeTransmitido);
                juego.siguientePaso();
            } else if (juego.getPosicion() == participantes.length - 1) {
                System.out.println("Último niño va a la pizarra y escribe");
                String mensajeFinal = participantes[juego.getPosicion()].mostrarPizarrin();
                pizarra.setMensaje(mensajeFinal);
                pizarra.imprimir();
                juego.terminar();

                
                aisha.vaciarFila();

                
                while (lydia.tieneEsperando() && aisha.getFila().tamaño() <= MIN_NINOS_PARA_JUGAR) {
                    aisha.pedirNino(lydia);
                }
            } else {
                
                juego.terminar();
            }

         
            tiempo.pasarMinuto();

            
            if (tiempo.totalMinutos() >= DURACION_TOTAL_MINUTOS) {
                System.out.println("Se alcanzó el cierre durante un juego; la ludoteca cierra ahora.");
                juego.terminar();
                break;
            }
        }
    }
}
