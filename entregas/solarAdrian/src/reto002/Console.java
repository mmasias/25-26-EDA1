package reto002;

import java.util.Scanner;

public class Console {
    private Scanner sc = new Scanner(System.in);

    public int readInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            sc.next(); 
            System.out.print("Ingrese un número válido: ");
        }
        int val = sc.nextInt();
        sc.nextLine(); 
        return val;
    }

    public String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}
