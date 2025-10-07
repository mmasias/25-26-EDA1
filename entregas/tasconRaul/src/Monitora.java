public class Monitora {
    
    public Nino[] obtenerNinos() {
        Nino[] lista = new Nino[cantidad];
        NodoNino actual = primero;
        int i = 0;
        while (actual != null) {
            lista[i++] = actual.nino;
            actual = actual.siguiente;
        }
        return lista;
    }
    private String nombre;
    private NodoNino primero;
    private NodoNino ultimo;
    private int cantidad;

    private static class NodoNino {
        Nino nino;
        NodoNino siguiente;
        NodoNino(Nino nino) {
            this.nino = nino;
            this.siguiente = null;
        }
    }

    public Monitora(String nombre) {
        this.nombre = nombre;
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    
    public void agregarNino(Nino nino) {
        NodoNino nuevo = new NodoNino(nino);
        if (ultimo == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        cantidad++;
    }

    
    public void transferirTodosA(Monitora otra) {
        NodoNino actual = primero;
        while (actual != null) {
            otra.agregarNino(actual.nino);
            actual = actual.siguiente;
        }
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    
    public void mostrarCola() {
        if (cantidad == 0) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cantidad);
            NodoNino actual = primero;
            while (actual != null) {
                Nino nino = actual.nino;
                System.out.println("  - " + nino.getNombre() + " (" + nino.getEdad() + " años)");
                actual = actual.siguiente;
            }
        }
    }
}
