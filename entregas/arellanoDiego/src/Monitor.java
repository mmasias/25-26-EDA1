import java.util.*;
import java.util.function.Predicate;

public class Monitor {
    private static final int CAPACIDAD_MAXIMA = 20;
    private String nombre;
    private Nino[] ninos;
    private int inicio;
    private int fin;
    private int cantidad;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.ninos = new Nino[CAPACIDAD_MAXIMA];
        this.inicio = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    public boolean recibeNino(Nino n) {
        if (cantidad >= CAPACIDAD_MAXIMA) {
            System.out.println("ERROR: " + nombre + " no puede recibir más niños. Cola llena.");
            return false;
        }
        ninos[fin] = n;
        fin = (fin + 1) % CAPACIDAD_MAXIMA;
        cantidad++;
        return true;
    }

    public boolean recibeNino(Nino n, Pizarra p) {
        n.recibirPizarrin(p);
        return recibeNino(n);
    }

    public boolean tieneNiños() {
        return cantidad > 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean puedeJugar() {
        return cantidad >= 5;
    }

    public List<Nino> getNinosAsList() {
        List<Nino> list = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            int idx = (inicio + i) % CAPACIDAD_MAXIMA;
            list.add(ninos[idx]);
        }
        return list;
    }

    private Nino sacarNiño() {
        if (cantidad == 0) return null;
        Nino out = ninos[inicio];
        ninos[inicio] = null;
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return out;
    }

    public List<Nino> transferAllTo(Monitor otro) {
        List<Nino> movidos = new ArrayList<>();
        while (tieneNiños()) {
            Nino n = sacarNiño();
            otro.recibeNino(n, new Pizarra());
            movidos.add(n);
        }
        return movidos;
    }

 
    public List<Nino> transferIf(Predicate<Nino> criterio, Monitor otro) {
        List<Nino> actuales = getNinosAsList();
        List<Nino> quedan = new ArrayList<>();
        List<Nino> movidos = new ArrayList<>();

        for (Nino n : actuales) {
            if (criterio.test(n)) {
                otro.recibeNino(n, new Pizarra());
                movidos.add(n);
            } else {
                quedan.add(n);
            }
        }

        Arrays.fill(ninos, null);
        inicio = 0;
        fin = 0;
        cantidad = 0;
        for (Nino q : quedan) {
            recibeNino(q);
        }

        return movidos;
    }

    public List<Nino> getPrimerosN(int n) {
        List<Nino> todos = getNinosAsList();
        int to = Math.min(n, todos.size());
        return new ArrayList<>(todos.subList(0, to));
    }

    public List<Nino> getUltimosN(int n) {
        List<Nino> todos = getNinosAsList();
        int size = todos.size();
        int start = Math.max(0, size - n);
        return new ArrayList<>(todos.subList(start, size));
    }

    public double edadPromedio() {
        if (cantidad == 0) return 0.0;
        double sum = 0;
        for (Nino n : getNinosAsList()) sum += n.getEdad();
        return sum / cantidad;
    }

    public int contarEdadMayorOIgual(int edad) {
        int cnt = 0;
        for (Nino n : getNinosAsList()) if (n.getEdad() >= edad) cnt++;
        return cnt;
    }
}
