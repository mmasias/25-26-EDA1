import java.util.Scanner;

public class Ludoteca {
    private Monitora monitoraLydia = new Monitora("Lydia");
    private Monitora monitoraAisha = new Monitora("Aisha");
    private Monitora monitoraDalsy = new Monitora("Dalsy");

    private Pizarra pizarraDeSalida = new Pizarra();
    private Scanner lectorTeclado = new Scanner(System.in);

    public void ejecutar() {
        int opcionSeleccionada;
        do {
            pizarraDeSalida.mostrarMenu();
            opcionSeleccionada = leerEnteroDesdeConsola();
            procesarOpcionDelMenu(opcionSeleccionada);
            if (opcionSeleccionada != 0) {
                System.out.println();
                System.out.println("(Pulse ENTER para continuar...)");
                lectorTeclado.nextLine();
            }
        } while (opcionSeleccionada != 0);
    }

    private void procesarOpcionDelMenu(int opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case 1: ejecutarOpcionLlegada(); break;
            case 2: ejecutarOpcionIntentoInicio(); break;
            case 3: ejecutarOpcionPresentacionesGenerales(); break;
            case 4: ejecutarOpcionPresentacionesPorEdad(); break;
            case 5: ejecutarOpcionPresentacionesPorInicial(); break;
            case 6: pizarraDeSalida.presentarPrimeros(monitoraAisha, Pizarra.CANTIDAD_PRIMEROS_O_ULTIMOS); break;
            case 7: pizarraDeSalida.presentarUltimos(monitoraAisha, Pizarra.CANTIDAD_PRIMEROS_O_ULTIMOS); break;
            case 8: pizarraDeSalida.conteoAsistencia(monitoraLydia, monitoraAisha, monitoraDalsy); break;
            case 9: pizarraDeSalida.edadPromedioAisha(monitoraAisha); break;
            case 10: pizarraDeSalida.verificarJuegoRana(monitoraAisha); break;
            case 11: ejecutarOpcionSepararMenores(); break;
            case 12: ejecutarOpcionEmergencia(); break;
            case 13: pizarraDeSalida.mostrarEstado(monitoraLydia, monitoraAisha, monitoraDalsy); break;
            case 0: System.out.println("Saliendo..."); break;
            default: System.out.println("Opción no válida");
        }
    }

    private void ejecutarOpcionLlegada() {
        System.out.print("Nombre: ");
        String nombreIntroducidoPorUsuario = lectorTeclado.nextLine();
        System.out.print("Edad: ");
        int edadIntroducidaPorUsuario = leerEnteroDesdeConsolola();

        Nino nuevoNino = new Nino(nombreIntroducidoPorUsuario, edadIntroducidaPorUsuario);
        monitoraLydia.encolar(nuevoNino);
        pizarraDeSalida.mostrarLlegada(nombreIntroducidoPorUsuario, edadIntroducidaPorUsuario);
    }

    private void ejecutarOpcionIntentoInicio() {
        int cantidadTotalAsistentes = monitoraLydia.contar() + monitoraAisha.contar() + monitoraDalsy.contar();
        if (cantidadTotalAsistentes < Pizarra.UMBRAL_MINIMO_PARA_INTENTO || monitoraLydia.estaVacia()) {
            pizarraDeSalida.mostrarInsuficientesParaTransferir();
            return;
        }
        pizarraDeSalida.anunciarTransferenciaLydiaAAisha();
        monitoraLydia.imprimirListadoDetallado();
        monitoraLydia.transferirTodoA(monitoraAisha);
    }

    private void ejecutarOpcionPresentacionesGenerales() {
        if (monitoraAisha.contar() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        pizarraDeSalida.presentacionesGenerales(monitoraAisha);
    }

    private void ejecutarOpcionPresentacionesPorEdad() {
        if (monitoraAisha.contar() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.print("Edad mínima: ");
        int edadMinimaParaPresentarse = leerEnteroDesdeConsola();
        pizarraDeSalida.presentacionesPorEdad(monitoraAisha, edadMinimaParaPresentarse);
    }

    private void ejecutarOpcionPresentacionesPorInicial() {
        if (monitoraAisha.contar() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.print("Letra inicial: ");
        char letraInicialBuscada = leerPrimeraLetraNoEspacio();
        pizarraDeSalida.presentacionesPorInicial(monitoraAisha, letraInicialBuscada);
    }

    private void ejecutarOpcionSepararMenores() {
        if (monitoraAisha.contar() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        monitoraAisha.separarMenoresHacia(monitoraDalsy, Pizarra.EDAD_MINIMA_JUEGO_RANA);
        pizarraDeSalida.mostrarSeparacion(monitoraAisha, monitoraDalsy);
    }

    private void ejecutarOpcionEmergencia() {
        int cantidadTransferidaDesdeDalsy = monitoraDalsy.transferirTodoA(monitoraLydia);
        int cantidadTransferidaDesdeAisha = monitoraAisha.transferirTodoA(monitoraLydia);
        pizarraDeSalida.emergenciaTrasladoTotal(cantidadTransferidaDesdeDalsy + cantidadTransferidaDesdeAisha, monitoraLydia);
    }

    private int leerEnteroDesdeConsola() {
        String texto = lectorTeclado.nextLine();
        int indice = 0;
        int longitud = texto.length();
        while (indice < longitud && texto.charAt(indice) == ' ') indice = indice + 1;
        int signo = 1;
        if (indice < longitud && texto.charAt(indice) == '-') { signo = -1; indice = indice + 1; }
        int valor = 0;
        while (indice < longitud) {
            char caracter = texto.charAt(indice);
            if (caracter >= '0' && caracter <= '9') valor = valor * 10 + (caracter - '0');
            indice = indice + 1;
        }
        return valor * signo;
    }

    private char leerPrimeraLetraNoEspacio() {
        String texto = lectorTeclado.nextLine();
        int indice = 0;
        int longitud = texto.length();
        while (indice < longitud && texto.charAt(indice) == ' ') indice = indice + 1;
        if (indice < longitud) return texto.charAt(indice);
        return ' ';
    }
}