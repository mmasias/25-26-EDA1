public class Mundo {
    private Ludoteca ludoteca;
    private int tiempoTotal;

    public Mundo(Ludoteca unaLudoteca, int tiempoApertura) {
        ludoteca = unaLudoteca;
        tiempoTotal = tiempoApertura;
    }

    public void iniciarSimulacion() {
        for (int minuto = 0; minuto < tiempoTotal; minuto++) {
            System.out.println("=".repeat(30));
            System.out.println("Minuto " + minuto);

            if (llegaNiño(minuto)) {
                Niño niño = generarNiño();
                ludoteca.recibirNiño(niño);
            }

            ludoteca.actualizar();
            ludoteca.verEstado();
        }
    }

    private boolean llegaNiño(int minuto) {
        return Math.random() > ((minuto < 10) ? 0.3 : (minuto < 30) ? 0.6 : 0.8);
    }

    private Niño generarNiño() {
        String[] nombres = {
            "Andrés", "Pablo", "Diego", "Aníbal", "Umut", "Javier",
            "Fernando", "Iker", "Mario", "Adrián", "Paula", "Veronika",
            "Eduardo", "Hugo", "Miguel", "Santiago", "Juan", "Daniel",
            "Álvaro", "Sergio", "Aurelio", "Raúl", "José", "Óscar"
        };
        String nombre = nombres[(int) (Math.random() * nombres.length)];
        System.out.println("Llega " + nombre);
        return new Niño(nombre);
    }

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        Mundo mundo = new Mundo(ludoteca, 40); // 40 minutos de simulación
        mundo.iniciarSimulacion();
    }
}
