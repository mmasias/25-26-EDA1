public abstract class Monitora extends Persona {
    protected ColaNinos cola;

    public Monitora(String nombre, int capacidadCola) {
        super(nombre);
        this.cola = new ColaNinos(capacidadCola);
    }

    public ColaNinos getCola() {
        ColaNinos referencia;
        referencia = this.cola;
        return referencia;
    }

    public String presentacion() {
        String texto;
        texto = "Hola, soy " + this.getNombre() + ", monitora de esta ludoteca";
        return texto;
    }
}
