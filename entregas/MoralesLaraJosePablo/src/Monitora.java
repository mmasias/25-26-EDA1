public class Monitora {
    private static final int MAX_NIÑOS = 100;
    
    private String nombre;
    private Niño[] cola;
    private int inicio;
    private int fin;
    private int tamaño;
    
    public Monitora(String nombre) {
        this.nombre = nombre;
        this.cola = new Niño[MAX_NIÑOS];
        this.inicio = 0;
        this.fin = 0;
        this.tamaño = 0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void agregarNiño(Niño niño) {
        if (tamaño < MAX_NIÑOS) {
            cola[fin] = niño;
            fin = (fin + 1) % MAX_NIÑOS;
            tamaño++;
        }
    }
    
    public Niño sacarNiño() {
        if (tamaño == 0) {
            return null;
        }
        Niño niño = cola[inicio];
        cola[inicio] = null;
        inicio = (inicio + 1) % MAX_NIÑOS;
        tamaño--;
        return niño;
    }
    
    public Niño obtenerNiño(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        int posicionReal = (inicio + indice) % MAX_NIÑOS;
        return cola[posicionReal];
    }
    
    public int getCantidadNiños() {
        return tamaño;
    }
    
    public boolean estaVacia() {
        return tamaño == 0;
    }
    
    public void transferirTodosA(Monitora destino) {
        while (!estaVacia()) {
            Niño niño = sacarNiño();
            destino.agregarNiño(niño);
        }
    }
    
    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + ", monitora de esta ludoteca");
    }
    
    public double calcularEdadPromedio() {
        if (tamaño == 0) {
            return 0.0;
        }
        
        int sumaEdades = 0;
        for (int i = 0; i < tamaño; i++) {
            Niño niño = obtenerNiño(i);
            sumaEdades += niño.getEdad();
        }
        
        return (double) sumaEdades / tamaño;
    }
    
    public int contarNiñosPorEdad(int edadMinima) {
        int contador = 0;
        for (int i = 0; i < tamaño; i++) {
            Niño niño = obtenerNiño(i);
            if (niño.getEdad() >= edadMinima) {
                contador++;
            }
        }
        return contador;
    }
    
    public void mostrarNiños() {
        if (estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + tamaño);
            for (int i = 0; i < tamaño; i++) {
                Niño niño = obtenerNiño(i);
                System.out.println("  - " + niño.toString());
            }
        }
    }
}