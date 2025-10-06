import java.util.Scanner;

public class Aisha {
    private Pizarra pizarra;

    public Aisha() {
        this.pizarra = new Pizarra();
    }

    public void escribirLetrasIniciales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Aisha, escribe 10 letras iniciales:");
        String letras = sc.nextLine().toUpperCase();
        if (letras.length() != 10) {
            System.out.println("Debes escribir exactamente 10 letras.");
            System.exit(1);
        }
        pizarra.setLetras(letras);
    }

    public Pizarra getPizarra() {
        return pizarra;
    }
}