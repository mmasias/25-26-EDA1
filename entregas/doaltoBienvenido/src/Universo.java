public class Universo {

    public static void main(String[] args) {
        Mundo mundo = new Mundo(new Ludoteca(), 120);
        mundo.ejecutarSimulacion();
    }
}
