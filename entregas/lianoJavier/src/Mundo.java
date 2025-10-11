public class Mundo {

    private static final String[] NOMBRES = {
            "Alejandro", "Beatriz", "Carlos", "Diana", "Eduardo", "Fernanda", "Gonzalo", "Helena",
            "Ismael", "Julia", "Kevin", "Laura", "Marcos", "Natalia", "Oscar", "Patricia",
            "Quintín", "Raquel", "Sergio", "Tatiana", "Ulises", "Valeria", "Wilmer", "Ximena",
            "Yahir", "Zulema", "Antonio", "Bárbara", "César", "Dolores", "Esteban", "Fabiola",
            "Gabriel", "Hilda", "Iván", "Jimena", "Karla", "Leonardo", "Mauricio", "Noemí",
            "Orlando", "Paola", "Ramiro", "Silvia", "Tomás", "Ursula", "Verónica", "Walter",
            "Xavier", "Yolanda", "Zacarías", "Alma", "Bruno", "Claudia", "Damián", "Elena",
            "Fabio", "Graciela", "Horacio", "Inés", "Joaquín", "Karen", "Luis", "María",
            "Nicolás", "Olga", "Pablo", "Rosa", "Samuel", "Teresa", "Uriel", "Viviana",
            "Wendy", "Xóchitl", "Yanet", "Zoilo", "Adriana", "Benjamín", "Camila", "Diego",
            "Emilia", "Felipe", "Guadalupe", "Hugo", "Irene", "Julio", "Kenia", "Lucas",
            "Magdalena", "Norberto", "Ofelia", "Pedro", "Renata", "Simón", "Tamara", "Uliana",
            "Víctor", "Wilma", "Yessenia", "Zoraida", "Axel", "Bianca", "Cristian", "Daniela",
            "Erick", "Florencia", "Gaspar", "Herminia", "Isadora", "Jazmín", "Kurt", "Liliana",
            "Matías", "Nuria", "Octavio", "Pilar", "Rafael", "Selena", "Thiago", "Ubaldo",
            "Valentín", "Wanda", "Yael", "Zenaida", "Ariadna", "Beto", "Celeste", "Domingo",
            "Elisa", "Fausto", "Gilberto", "Haydee", "Iker", "Jacinta", "Karim", "Lorena",
            "Maximiliano", "Nadia", "Omar", "Quetzal", "Rogelio", "Sonia", "Tadeo", "Unai",
            "Vanessa", "Washington", "Yamila", "Zoé", "Amparo", "Baltasar", "Cintia", "Darío",
            "Estela", "Fermín", "Gema", "Homero", "Itzel", "Julián", "Katherine", "Leandro",
            "Melisa", "Nehemías", "Otilia", "Patricio", "Reina", "Sebastián", "Teófilo", "Ulrich",
            "Valentína", "Wilfredo", "Yuridia", "Zenón", "Anahí", "Bernardo", "Consuelo", "Demetrio",
            "Erika", "Federico", "Gerardo", "Honoria", "Ignacio", "Josué", "Kiara", "Luciana",
            "Maurina", "Nicolasa", "Orfelia", "Priscila", "Roxana", "Santiago", "Trinidad", "Ursino",
            "Violeta", "Wenceslao", "Yolotl", "Zacarias"
    };

    private Tiempo tiempo;

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.simular();
    }

    public Mundo() {
        ludoteca = new Ludoteca(tiempo);
        tiempo = new Tiempo();
    }

    private Ludoteca ludoteca;

    private void simular() {
        while (ludoteca.abierta()) {
            llegadaDeNiños();
            ludoteca.actualizar();
            tiempo.siguiente();
        }
    }

    private void llegadaDeNiños() {
        int minutos = 10;
        if (tiempo.menosOIgualDe(minutos)) lleganEntre0Y2();
        minutos = 30;
        if (tiempo.menosOIgualDe(minutos)) llega1De3Veces();
    }

    private void llega1De3Veces() {
        if (Math.random() >= 0.3) llegaNiño();
    }

    private void lleganEntre0Y2() {
        for (int i = 0; i < random(2, 0); i++) llegaNiño();
    }

    private void llegaNiño() {
        ludoteca.recibe(generarNiño());
    }

    private Niño generarNiño() {
        return new Niño(generarNombre(), generarEdad());
    }

    private int generarEdad() {
        return random(16, 1);
    }

    private String generarNombre() {
        int posicionRandom = random(NOMBRES.length, 0);
        return NOMBRES[posicionRandom];
    }

    private int random(int max, int min) {
        return (int) Math.random() * (max - min + 1) + min;
    }
}
