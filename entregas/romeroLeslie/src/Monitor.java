import utils.Console;

class Monitor {
    private static final int CAPACIDAD_MAXIMA = 20;
    private String nombre;
    private Niño[] colaNiños;
    private int inicio;
    private int fin;
    private int cantidad;

    public Monitor(String nombre) {
        this.nombre = nombre;
        colaNiños = new Niño[CAPACIDAD_MAXIMA];
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }

    public boolean tieneNiños() {
        return cantidad > 0;
    }

    public void recibirNiño(Niño niño) {
        if (cantidad >= CAPACIDAD_MAXIMA) return;
        colaNiños[fin] = niño;
        fin = (fin + 1) % CAPACIDAD_MAXIMA;
        cantidad++;
    }

    public Niño[] obtenerTodosNiños() {
        Niño[] lista = new Niño[cantidad];
        int posicionActual = inicio;
        for (int i = 0; i < cantidad; i++) {
            lista[i] = colaNiños[posicionActual];
            posicionActual = (posicionActual + 1) % CAPACIDAD_MAXIMA;
        }
        return lista;
    }

    public void vaciarCola() {
        for (int i = 0; i < cantidad; i++) {
            colaNiños[i] = null;
        }
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void mostrarListaNiños(Console console) {
        console.writeln(nombre + ":");
        if (cantidad == 0) {
            console.writeln("  Fila vacía");
        } else {
            console.writeln("  Niños en la fila: " + cantidad);
            int posicionActual = inicio;
            for (int i = 0; i < cantidad; i++) {
                console.writeln("  - " + colaNiños[posicionActual].getNombre() + " (" + colaNiños[posicionActual].getEdad() + " años)");
                posicionActual = (posicionActual + 1) % CAPACIDAD_MAXIMA;
            }
        }
        console.writeln();
    }
    public double calcularEdadPromedio() {
        if (cantidad == 0) return 0;
        int suma = 0;
        for (Niño niñoActual : obtenerTodosNiños()) {
            suma += niñoActual.getEdad();
        }
        return (double)suma / cantidad;
    }
    public Niño[] separarMenoresDe(int edadMinima, Monitor otroMonitor) {
        Niño[] quedan = new Niño[cantidad];
        int contadorQuedan = 0;
        Niño[] todos = obtenerTodosNiños();
        vaciarCola();
        for (Niño niñoActual : todos) {
            if (niñoActual.getEdad() < edadMinima) {
                otroMonitor.recibirNiño(niñoActual);
            } else {
                recibirNiño(niñoActual);
                quedan[contadorQuedan] = niñoActual;
                contadorQuedan++;
            }
        }
        Niño[] resultado = new Niño[contadorQuedan];
        for (int i = 0; i < contadorQuedan; i++) {
            resultado[i] = quedan[i];
        }
        return resultado;
    }
    
}