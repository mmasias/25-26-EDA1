
public class Mundo {
    private static final String VERSION = "v2.0";

    private Ludoteca ludoteca;
    private Tiempo tiempo;

    public Mundo(Ludoteca ludoteca, Tiempo tiempo) {
        this.ludoteca = ludoteca;
        this.tiempo = tiempo;
    }

    public static void main(String[] args) {
        presentacion();

        Ludoteca ludoteca = new Ludoteca();
        final int HORAS = 2, MINUTOS = 0;
        Tiempo tiempo = new Tiempo(HORAS, MINUTOS);
        Mundo mundo = new Mundo(ludoteca, tiempo);

        do { mundo.simular(); } while (!tiempo.terminado());
    }

    private void simular() {
        Console.clear();
        tiempo.imprimir();

        Monitor lydia = new Monitor("Lydia");
        Monitor aisha = new Monitor("Aisha");

        ludoteca.recibir(lydia);
        ludoteca.recibir(aisha);

        if (llegaNiño(tiempo)) {
            Niño niño = new Niño(nombrar());
            Console.imprimir("llega" + Console.espacio() + niño.getNombre());
            ludoteca.recibir(niño);
        }

        ludoteca.actualizar();
        ludoteca.imprimirEstado();

        tiempo.siguiente();
        Console.waitUser();
    }

    private String nombrar() {
        String[] nombres = {
                "Hugo", "Alejandro", "Mateo", "Enzo", "Leo", "Daniel", "Gael", "Manuel",
                "Martín", "Pablo", "Dylan", "Liam", "Thiago", "Luca", "Luka", "Unai",
                "Oliver", "Izan", "Mario", "Miguel", "Lucas", "Rubén", "Adrián", "Sergio",
                "Ángel", "José", "Aritz", "Gonzalo", "Marco", "Nicolás", "Gabriel", "Héctor",
                "Javier", "Noah", "Eric", "Erik", "Antonio", "Asier", "Biel", "Diego",
                "Juan", "Santiago", "Álvaro", "Derek", "Isaac", "Nico", "Rodrigo", "Samuel",
                "Álex", "Adam", "Carlos", "Ian", "Ismael", "Joel", "Julen", "Marc",
                "Marcos", "Pau", "Pedro", "Alan", "Alessandro", "Áxel", "Bruno", "Darío",
                "David", "Iker", "Jesús", "Kai", "Luis", "Nil", "Omar", "Río", "Saúl",
                "Aarón", "Aitor", "Alberto", "Arturo", "Elian", "Eloy", "Ezra", "Fabio",
                "Francisco", "Iago", "Jaime", "Julio", "Max", "Maximiliano", "Miguel Ángel",
                "Neizan", "Raúl", "Theo", "Yael", "Abraham", "Esteban", "Feliciano",
                "Germán", "Isidro", "Leandro", "Alonso", "Andrés", "Ignacio", "Vicente",
                "Sofía", "Julia", "Martina", "Vega", "Lucía", "Gala", "Valentina", "Dafne",
                "Alana", "Paula", "Alma", "Olivia", "Valeria", "María", "Chloe", "Lara",
                "Lola", "Noa", "Emma", "Jimena", "Luna", "Nahia", "Naia", "Aitana",
                "Laia", "Mía", "Carmen", "Claudia", "Daniela", "Leyre", "Leire", "Manuela",
                "Marta", "Sara", "Zoe", "Lía", "Ana", "Candela", "Isabella", "Adhara",
                "Adara", "Alejandra", "Carlota", "Celia", "Inés", "Abril", "Adriana",
                "Ainhoa", "Alba", "Arabia", "Carla", "Clara", "Iris", "Amira", "Aroa",
                "Atenea", "Elena", "Elia", "Giulia", "Irene", "Marina", "Nora", "Triana",
                "Yanira", "Alicia", "Camila", "Coral", "Danae", "Eva", "Eyra", "Gabriela",
                "June", "Laura", "Maia", "Rocío", "Vera", "Adriane", "Aisha", "Alaia",
                "Ariadna", "Aurora", "Azahara", "Bianca", "Blanca", "Carolina", "Cataleya",
                "Elaia", "Elsa", "Estela", "Gaia", "Irati", "Jara", "Macarena", "Mar",
                "Mara", "Nayla", "Nayra", "Victoria", "Ángela", "Aina", "Akira", "Amaia"
        };
        return nombres[(int) (Math.random() * nombres.length)];
    }

    private boolean llegaNiño(Tiempo tiempo) {
        double porcentaje = 0.8;
        if (tiempo.getMinutos() < 10) {
            porcentaje = 0.3;
        } else if (tiempo.getMinutos() < 30) {
            porcentaje = 0.6;
        }
        return Math.random() > porcentaje;
    }

    private static void presentacion() {
        Console.separador("*");
        Console.imprimir("Teléfono Escacharrado");
        Console.imprimir("Versión:" + Console.espacio() + VERSION);
        Console.separador("*");
        Console.waitUser();
    }
}
