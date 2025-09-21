import java.util.*;

public class Lydia {
    private Queue<Child> esperando = new LinkedList<>();
    private Random random = new Random();
    private List<String> nombresDisponibles = Arrays.asList(
        "Ana","Luis","Marcos","Lucia","Pedro","Sara",
        "Carlos","Elena","Marta","Ivan","Paula","David",
        "Sofia","Mario","Laura","Alberto","Raul","Nerea"
    );
    private int indiceNombre = 0;

    public void recibirNiños(int tiempo) {
        if (tiempo < 10) {
            int llegadas = random.nextInt(3); 
            for (int i = 0; i < llegadas && indiceNombre < nombresDisponibles.size(); i++) {
                esperarNuevoChild(tiempo);
            }
        } else if (tiempo < 30) {
            if (tiempo % 3 == 0 && random.nextBoolean() && indiceNombre < nombresDisponibles.size()) {
                esperarNuevoChild(tiempo);
            }
        }
    }

    private void esperarNuevoChild(int tiempo) {
        Child nuevo = new Child(nombresDisponibles.get(indiceNombre++));
        esperando.add(nuevo);
        System.out.println("-> Minuto " + tiempo + ": Llega child " + nuevo.getNombre());
    }

    public boolean tieneEsperando() {
        return !esperando.isEmpty();
    }

    public Child darChild() {
        return esperando.poll();
    }
}
