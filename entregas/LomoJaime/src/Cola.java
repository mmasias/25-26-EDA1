
import utils.Console;

public class Cola {
    private static final int CAPACIDAD_MAXIMA = 20;
    private Niño[] niños;
    private int inicio;
    private int fin;
    private int cantidad;

    public Cola() {
        niños = new Niño[CAPACIDAD_MAXIMA];
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }

    public void addNiño(Niño niño) {
        if (cantidad >= CAPACIDAD_MAXIMA) {
            new Console().writeln("ERROR: ¡Cola llena! No puedo agregar a " + niño.getNombre());
            return;
        }
        niños[fin] = niño;
        fin = (fin + 1) % CAPACIDAD_MAXIMA;
        cantidad++;
    }

    public Niño removeNiño() {
        if (cantidad == 0) {
            return null;
        }
        Niño saliente = niños[inicio];
        niños[inicio] = null;
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return saliente;
    }

    public boolean hayNiños() {
        return cantidad > 0;
    }

    public int size() {
        return cantidad;
    }

    public void listaNiños() {
        if (cantidad == 0) {
            new Console().write("Cola vacía");
            return;
        }
        int actual = inicio;
        for (int i = 0; i < cantidad; i++) {
            new Console().write(niños[actual].getNombre() + " (" + niños[actual].getEdad() + " años)");
            if (i < cantidad - 1) new Console().write(", ");
            actual = (actual + 1) % CAPACIDAD_MAXIMA;
        }
    }

    public Niño getNiño(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            return null;
        }
        int indice = (inicio + posicion) % CAPACIDAD_MAXIMA;
        return niños[indice];
    }

    public Niño[] getNiñosArray() {
        Niño[] copia = new Niño[cantidad];
        for (int i = 0; i < cantidad; i++) {
            copia[i] = getNiño(i);
        }
        return copia;
    }

    public void reemplazarDesdeArray(Niño[] lista) {
        while (hayNiños()) removeNiño();
        for (Niño n : lista) {
            if (n != null) addNiño(n);
        }
    }
}