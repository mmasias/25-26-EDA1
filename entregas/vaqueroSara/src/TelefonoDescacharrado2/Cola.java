package TelefonoDescacharrado2;

public class Cola {
    private Niño primero;
    private Niño ultimo;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void encolar(Niño n) {
        if (estaVacia()) {
            primero = ultimo = n;
        } else {
            ultimo.setSiguiente(n);
            ultimo = n;
        }
    }

    public Niño desencolar() {
        if (estaVacia()) return null;
        Niño temp = primero;
        primero = primero.getSiguiente();
        if (primero == null) ultimo = null;
        temp.setSiguiente(null);
        return temp;
    }

    public int contar() {
        int c = 0;
        Niño aux = primero;
        while (aux != null) {
            c++;
            aux = aux.getSiguiente();
        }
        return c;
    }

    public Niño getPrimero() {
        return primero;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  Cola vacía");
            return;
        }
        Niño aux = primero;
        while (aux != null) {
            System.out.println("  - " + aux.getNombre() + " (" + aux.getEdad() + " años)");
            aux = aux.getSiguiente();
        }
    }

    public double promedioEdad() {
        if (estaVacia()) return -1;
        int suma = 0;
        int n = 0;
        Niño aux = primero;
        while (aux != null) {
            suma += aux.getEdad();
            n++;
            aux = aux.getSiguiente();
        }
        return (double) suma / n;
    }
}
