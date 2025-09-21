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
public void recibirMensaje(Mensaje mensaje) {
    String texto = mensaje.obtenerTexto();
    char[] chars = texto.toCharArray();
    if (Math.random() < 0.9 ) {
        int pos = (int) (Math.random() * chars.length);
        chars[pos] = (char) ('A' + (int) (Math.random() * 26));
    }
    this.pizarrin = new String(chars);
}
