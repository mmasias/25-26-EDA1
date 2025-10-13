import java.util.Random;

public class Nino {
    static String[] nombres = {"Ana", "Jose", "Pablo", "Luis", "Daniel", "Carla", "Valeria", "Victor", "Roberto"}; 
    String nombre;
    int edad;


public Nino() {
    Random rand = new Random();
    int indice = rand.nextInt(nombres.length);
    nombre = nombres[indice];
    edad = rand.nextInt(8) + 3;

    }
}




