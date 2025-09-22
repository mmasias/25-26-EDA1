import java.util.List;

public class Aisha {
    public void jugarConNiños(List<Niño> niños, Pizarra pizarraPrincipal) {
        System.out.println("Aisha observa el resultado final:");
        pizarraPrincipal.mostrar();
        System.out.println("Comparación de pizarras:");
        for (Niño n : niños) {
            System.out.println(n.getNombre() + ": " + n.getLetras());
        }
    }
}