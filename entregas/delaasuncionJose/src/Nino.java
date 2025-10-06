class Nino {
    private String nombre;
    private int edad;
    private long llegadaOrden;

    public Nino(String nombre, int edad, long llegadaOrden) {
        this.nombre = nombre;
        this.edad = edad;
        this.llegadaOrden = llegadaOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public long getLlegadaOrden() {
        return llegadaOrden;
    }

    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}