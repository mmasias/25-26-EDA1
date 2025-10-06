public class Pizarra {
    public static final int CANTIDAD_PRIMEROS_O_ULTIMOS = 5;
    public static final int EDAD_MINIMA_JUEGO_RANA = 5;
    public static final int UMBRAL_MINIMO_PARA_INTENTO = 5;

    public void mostrarLlegada(String nombreNino, int edadNino) {
        System.out.println("Llega " + nombreNino + " (" + edadNino + " años)");
        System.out.println(nombreNino + " pasa a la cola de Lydia");
    }

    public void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        LUDOTECA - SIMULACIÓN");
        System.out.println("========================================");
        System.out.println();
        System.out.println("1.  Simular llegada de niño");
        System.out.println("2.  Simular intento de inicio de juego");
        System.out.println("3.  Aisha se presenta y pide a los niños que se presenten");
        System.out.println("4.  Aisha pide que se presenten los niños mayores de 5 años");
        System.out.println("5.  Aisha pide que se presenten los niños cuyo nombre empieza por letra");
        System.out.println("6.  Aisha pide que se presenten los cinco primeros niños");
        System.out.println("7.  Aisha pide que se presenten los cinco últimos niños");
        System.out.println("8.  Aisha y Lydia dicen cuántos niños hay en cola");
        System.out.println("9.  Aisha dice la edad promedio de los niños en cola");
        System.out.println("10. Simular intento de inicio del juego de la rana");
        System.out.println("11. Paso de niños menores de 5 años a monitora Dalsy");
        System.out.println("12. Alarma contra incendios y protocolo de emergencia");
        System.out.println("13. Mostrar monitoras y niños");
        System.out.println();
        System.out.println("0.  Salir");
        System.out.println();
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarInsuficientesParaTransferir() {
        System.out.println("No hay suficientes niños para iniciar el juego");
        System.out.println("Se necesitan al menos " + UMBRAL_MINIMO_PARA_INTENTO + " niños");
    }

    public void anunciarTransferenciaLydiaAAisha() {
        System.out.println("Lydia transfiere sus niños a Aisha");
    }

    public void presentacionesGenerales(Monitora monitoraAisha) {
        monitoraAisha.presentacionesGenerales();
    }

    public void presentacionesPorEdad(Monitora monitoraAisha, int edadMinimaExclusiva) {
        monitoraAisha.presentacionesMayoresQue(edadMinimaExclusiva);
    }

    public void presentacionesPorInicial(Monitora monitoraAisha, char letraInicialBuscada) {
        monitoraAisha.presentacionesPorInicial(letraInicialBuscada);
    }

    public void presentarPrimeros(Monitora monitoraAisha, int cantidadSolicitada) {
        monitoraAisha.presentarPrimeros(cantidadSolicitada);
    }

    public void presentarUltimos(Monitora monitoraAisha, int cantidadSolicitada) {
        monitoraAisha.presentarUltimos(cantidadSolicitada);
    }

    public void conteoAsistencia(Monitora monitoraLydia, Monitora monitoraAisha, Monitora monitoraDalsy) {
        int cantidadEnLydia = monitoraLydia.contar();
        int cantidadEnAisha = monitoraAisha.contar();
        int cantidadEnDalsy = monitoraDalsy.contar();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + cantidadEnLydia + " niños en cola");
        System.out.println("Aisha tiene " + cantidadEnAisha + " niños en cola");
        System.out.println("Dalsy tiene " + cantidadEnDalsy + " niños en cola");
        System.out.println("Total: " + (cantidadEnLydia + cantidadEnAisha + cantidadEnDalsy) + " niños");
    }

    public void edadPromedioAisha(Monitora monitoraAisha) {
        int cantidadEnAisha = monitoraAisha.contar();
        if (cantidadEnAisha == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double edadPromedio = monitoraAisha.edadPromedio();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", edadPromedio);
    }

    public void verificarJuegoRana(Monitora monitoraAisha) {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int totalEnAisha = monitoraAisha.contar();
        int cantidadQueCumplen = monitoraAisha.contarMayoresIgualQue(EDAD_MINIMA_JUEGO_RANA);
        System.out.println("Total de niños: " + totalEnAisha);
        System.out.println("Niños de " + EDAD_MINIMA_JUEGO_RANA + " años o más: " + cantidadQueCumplen);
        if (totalEnAisha > 0 && (cantidadQueCumplen * 2 > totalEnAisha)) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de " + EDAD_MINIMA_JUEGO_RANA + " años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void mostrarSeparacion(Monitora monitoraAisha, Monitora monitoraDalsy) {
        System.out.println("Separando niños para el juego de la rana...");
        System.out.println("Niños menores de " + EDAD_MINIMA_JUEGO_RANA + " años pasan a Dalsy:");
        monitoraDalsy.imprimirListadoDetallado();
        System.out.println();
        System.out.println("Niños que se quedan con Aisha para jugar:");
        monitoraAisha.imprimirListadoDetallado();
        System.out.println();
        System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public void emergenciaTrasladoTotal(int totalTransferidos, Monitora monitoraLydia) {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        System.out.println();
        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        System.out.println(totalTransferidos + " niños transferidos");
        System.out.println();
        System.out.println("Lydia ahora tiene " + monitoraLydia.contar() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado(Monitora monitoraLydia, Monitora monitoraAisha, Monitora monitoraDalsy) {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println();
        System.out.println("LYDIA:");
        monitoraLydia.imprimirListadoDetallado();
        System.out.println();
        System.out.println("AISHA:");
        monitoraAisha.imprimirListadoDetallado();
        System.out.println();
        System.out.println("DALSY:");
        monitoraDalsy.imprimirListadoDetallado();
        System.out.println();
        System.out.println("========================================");
    }
}