public class Nino {
    private String nombre;
    private String pizarrin;

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarrin = "";
    }
}
public String getNombre() {
    return nombre;
}
public void limpiarPizarrin() {
    pizarrin = "";
}
