public class Nino extends Persona {
    private int edad;

    public Nino() {
        super();
        this.edad = 0;
    }

    public Nino(String nombre, int edad) {
        super(nombre);
        this.edad = edad;
    }

    public int getEdad() {
        int valor;
        valor = this.edad;
        return valor;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String presentacion() {
        String texto;
        texto = "Hola, soy " + this.getNombre() + " y tengo " + this.getEdad() + " años";
        return texto;
    }

    public String presentacionSimple() {
        String texto;
        texto = "Hola, soy " + this.getNombre();
        return texto;
    }
}
