import java.util.*;

public class Juego {
    private List<Niño> niños;
    private Pizarra pizarraPrincipal;
    private Lydia lydia;
    private Aisha aisha;

    public Juego() {
        lydia = new Lydia();
        aisha = new Aisha();
        niños = new ArrayList<>();
        niños.add(new Niño("Juan"));
        niños.add(new Niño("Ana"));
        niños.add(new Niño("Luis"));
        niños.add(new Niño("Sofia"));
        niños.add(new Niño("Pedro"));
        pizarraPrincipal = new Pizarra();
    }

    public void jugar() {
        lydia.recibirNiños(niños);

        Scanner sc = new Scanner(System.in);
        System.out.println("Primer niño (" + niños.get(0).getNombre() + "), escribe 10 letras:");
        String letras = sc.nextLine().toUpperCase();
        if (letras.length() != 10) {
            System.out.println("Debes escribir exactamente 10 letras.");
            return;
        }
        niños.get(0).escribirLetras(letras);

        for (int i = 1; i < niños.size(); i++) {
            niños.get(i).copiarYModificar(niños.get(i - 1));
        }

        Niño ultimo = niños.get(niños.size() - 1);
        pizarraPrincipal.setLetras(ultimo.getLetras());
        System.out.println(ultimo.getNombre() + " escribe en la pizarra principal:");
        pizarraPrincipal.mostrar();

        aisha.jugarConNiños(niños, pizarraPrincipal);
    }
}