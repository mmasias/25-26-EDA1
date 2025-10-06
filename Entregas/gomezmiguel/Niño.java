class Niño {
    private final String nombre;
    private final int edad;
    private Pizarra pizarrin;

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public void recibirPizarrin(Pizarra pizarra) { this.pizarrin = pizarra; }
    public void limpiarPizarrin() { if (pizarrin != null) pizarrin.limpiar(); }

    public void presentarse() {
        System.out.println("[" + nombre + "]: Hola, soy " + nombre + " y tengo " + edad + " años");
    }

    public void presentarseNombre() {
        System.out.println("[" + nombre + "]: Hola, soy " + nombre);
    }
}
