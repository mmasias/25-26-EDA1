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

        aisha.escribirLetrasIniciales();

        niños.get(0).copiarYModificar(aisha.getPizarra());

        for (int i = 1; i < niños.size(); i++) {
            niños.get(i).copiarYModificar(niños.get(i - 1));
        }

        Niño ultimo = niños.get(niños.size() - 1);
        pizarraPrincipal.setLetras(ultimo.getLetras());
        System.out.println(ultimo.getNombre() + " escribe en el pizarrón grande de Aisha:");
        pizarraPrincipal.mostrar();

        System.out.println("\nRecorrido de pizarras:");
        System.out.println("Aisha: " + aisha.getPizarra().getLetras());
        for (Niño n : niños) {
            System.out.println(n.getNombre() + ": " + n.getLetras());
        }
        System.out.println("Pizarrón grande: " + pizarraPrincipal.getLetras());
    }
}