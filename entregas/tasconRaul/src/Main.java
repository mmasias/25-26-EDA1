public class Main {
    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        final int DURACION_MINUTOS = 120;

        for (int minuto = 0; minuto < DURACION_MINUTOS; minuto++) {
            int llegadas = 0;

            if (minuto < 10) {
                llegadas = minuto % 3; 
            } else if (minuto < 30) {
                if ((minuto - 10) % 3 == 0) llegadas = 1;
            }

            for (int i = 0; i < llegadas; i++) {
                Niño n = new Niño("Niño" + minuto + "_" + i);
                ludoteca.recibirNiño(n);
            }

            ludoteca.actualizar();

            if (minuto % 10 == 0) {
                System.out.println("\n--- Estado minuto " + minuto + " ---");
                ludoteca.verEstado();
            }
        }

        System.out.println("\n=== SIMULACIÓN FINALIZADA ===");
        ludoteca.verEstado();
    }
}
