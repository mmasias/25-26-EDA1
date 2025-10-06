import java.util.Scanner;

public class SimulacionLudoteca {
    public static void main(String[] args) {
        Scanner entrada;
        entrada = new Scanner(System.in);
        Mundo mundo;
        mundo = new Mundo();
        mundo.ejecutarSimulacion(entrada);
        entrada.close();
    }
}
