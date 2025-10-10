import java.util.List;

public class Lydia {
    public void recibirNiños(List<Niño> niños) {
        System.out.println("Lydia recibe a los niños:");
        for (Niño n : niños) {
            System.out.println("- " + n.getNombre());
        }
    }
}