class Monitor {
    private static final int CAPACIDAD_MAXIMA = 50;
    private static final int CANTIDAD_PRESENTACION_LISTAS = 5;
    private final String nombre;
    private final Niño[] niños = new Niño[CAPACIDAD_MAXIMA];
    private int cantidad = 0;

    public Monitor(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
    public boolean tieneNiños() { return cantidad > 0; }
    public int getCantidad() { return cantidad; }

    public void recibirNiño(Niño nino) {
        if (cantidad < CAPACIDAD_MAXIMA) niños[cantidad++] = nino;
    }

    public Niño[] getNiños() { return niños; }

    public void mostrarNiños() {
        if (cantidad == 0) {
            System.out.println("  Cola vacía");
            return;
        }
        System.out.println("  Niños en cola: " + cantidad);
        for (int i = 0; i < cantidad; i++)
            System.out.println("  - " + niños[i].getNombre() + " (" + niños[i].getEdad() + " años)");
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
        for (int i = 0; i < cantidad; i++)
            suma += niños[i].getEdad();
        return (double) suma / cantidad;
    }

    public int contarMayoresDe(int edadMinima) {
        int contador = 0;
        for (int i = 0; i < cantidad; i++)
            if (niños[i].getEdad() >= edadMinima) contador++;
        return contador;
    }

    public void mostrarPrimerosCinco() {
        for (int i = 0; i < Math.min(CANTIDAD_PRESENTACION_LISTAS, cantidad); i++)
            niños[i].presentarseNombre();
        if (cantidad == 0) System.out.println("  (Sin niños para mostrar)");
    }

    public void mostrarUltimosCinco() {
        for (int i = Math.max(0, cantidad - CANTIDAD_PRESENTACION_LISTAS); i < cantidad; i++)
            niños[i].presentarseNombre();
        if (cantidad == 0) System.out.println("  (Sin niños para mostrar)");
    }

    public Niño getNiñoEn(int indice) {
        return (indice >= 0 && indice < cantidad) ? niños[indice] : null;
    }

    public Niño removerEn(int indice) {
        if (indice < 0 || indice >= cantidad) return null;
        Niño eliminado = niños[indice];
        for (int i = indice; i < cantidad - 1; i++)
            niños[i] = niños[i + 1];
        niños[--cantidad] = null;
        return eliminado;
    }
}
