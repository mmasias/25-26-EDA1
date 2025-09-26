package evaluaciones.retos;

    
final class Impresora {
    private Impresora() {}
    static void imprimir(String s) { System.out.println(s); }
    static void registrarPartida(int participantes, String orig, String fin) {
        System.out.println(String.format("Partida: participantes=%d original=%s final=%s", participantes, orig, fin));
    }
}
