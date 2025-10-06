package reto002;

import java.util.Scanner;

public class Console {
    private Scanner sc = new Scanner(System.in);

    public String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public int readInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.print("Por favor ingrese un número válido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); 
        return valor;
    }
}
