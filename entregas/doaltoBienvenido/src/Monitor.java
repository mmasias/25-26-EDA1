class Monitor {
    private static final int CAPACIDAD_MAXIMA = 50;
    private static final int CANTIDAD_PRESENTACION_LISTAS = 5;
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

    public void recibirNiño(Niño nino) {
        if (cantidad < CAPACIDAD_MAXIMA) {
            niños[cantidad++] = nino;
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
        for (int indice = 0; indice < cantidad; indice++) {
            System.out.println("  - " + niños[indice].getNombre() + " (" + niños[indice].getEdad() + " años)");
        }
    }

    public void transferirNiños(Monitor destino) {
        for (int indice = 0; indice < cantidad; indice++) {
            destino.recibirNiño(niños[indice]);
            niños[indice] = null;
        }
        cantidad = 0;
    }

    public double edadPromedio() {
        if (cantidad == 0) return 0;
        int sumaEdades = 0;
        int totalContados = 0;
        for (int indice = 0; indice < cantidad; indice++) {
            if (niños[indice] != null) { 
                sumaEdades += niños[indice].getEdad(); 
                totalContados++; 
            }
        }
        return totalContados == 0 ? 0 : (double) sumaEdades / totalContados;
    }

    public int contarMayoresDe(int edadMinima) {
        int contador = 0;
        for (int indice = 0; indice < cantidad; indice++) {
            if (niños[indice] != null && niños[indice].getEdad() >= edadMinima) contador++;
        }
        return contador;
    }

    public void mostrarPrimerosCinco() {
        int limite = Math.min(CANTIDAD_PRESENTACION_LISTAS, cantidad);
        for (int indice = 0; indice < limite; indice++) {
            niños[indice].presentarseNombre();
        }
        if (limite == 0) {
            System.out.println("  (Sin niños para mostrar)");
        }
    }

    public void mostrarUltimosCinco() {
        int inicio = Math.max(0, cantidad - CANTIDAD_PRESENTACION_LISTAS);
        for (int indice = inicio; indice < cantidad; indice++) {
            niños[indice].presentarseNombre();
        }
        if (cantidad == 0) {
            System.out.println("  (Sin niños para mostrar)");
        }
    }

    public Niño getNiñoEn(int indice) {
        if (indice < 0 || indice >= cantidad) return null;
        return niños[indice];
    }

    public Niño removerEn(int indice) {
        if (indice < 0 || indice >= cantidad) return null;
        Niño eliminado = niños[indice];
        for (int i = indice; i < cantidad - 1; i++) {
            niños[i] = niños[i + 1];
        }
        niños[cantidad - 1] = null;
        cantidad--;
        return eliminado;
    }
}
