class Niño {
    private static int contador = 0;
    private final int id;
    private final String nombre;
    private final int edad;

    public Niño(String nombre, int edad) {
        this.id = ++contador;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Niño{id=" + id + ", nombre='" + nombre + "', edad=" + edad + "}";
    }
}