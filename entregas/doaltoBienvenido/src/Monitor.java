class Monitor {
    private static final int CAPACIDAD_MAXIMA = 50;
    private String nombre;
    private Niño[] niños;
    private int cantidad;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.niños = new Niño[CAPACIDAD_MAXIMA];
        this.cantidad = 0;
    }

    public String getNombre() { 
        return nombre; 
    }

    public boolean tieneNiños() { 
        return cantidad > 0; 
    }

    public int getCantidad() { 
        return cantidad; 
    }

    public void recibirNiño(Niño n) {
        if (cantidad < CAPACIDAD_MAXIMA) {
            niños[cantidad++] = n;
        }
    }

    public Niño[] getNiños() { 
        return niños; 
    }

    public void mostrarNiños() {
        if (cantidad == 0) {
            System.out.println("  Cola vacia");
            return;
        }
        
        System.out.println("  Niños en cola: " + cantidad);
        for (int i = 0; i < cantidad; i++) {
            System.out.println("  - " + niños[i].getNombre() + " (" + niños[i].getEdad() + " años)");
        }
    }

    public void transferirNiños(Monitor destino) {
        for (int i = 0; i < cantidad; i++) {
            destino.recibirNiño(niños[i]);
            niños[i] = null;
        }
        cantidad = 0;
    }

    public double edadPromedio() {
        if (cantidad == 0) return 0;
        int suma = 0;
        for (int i = 0; i < cantidad; i++) suma += niños[i].getEdad();
        return (double) suma / cantidad;
    }

    public int contarMayoresDe(int edad) {
        int count = 0;
        for (int i = 0; i < cantidad; i++) {
            if (niños[i].getEdad() >= edad) count++;
        }
        return count;
    }

    public void mostrarPrimerosCinco() {
        int limite = Math.min(5, cantidad);
        for (int i = 0; i < limite; i++) {
            niños[i].presentarseNombre();
        }
    }

    public void mostrarUltimosCinco() {
        int inicio = Math.max(0, cantidad - 5);
        for (int i = inicio; i < cantidad; i++) {
            niños[i].presentarseNombre();
        }
    }
}
