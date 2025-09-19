import java.util.Random;

class Nino {
    String nombre;
    String pizarrin;

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarrin = "";
    }

    public void limpiarPizarrin() {
        this.pizarrin = "";
    }

    public void recibirMensaje(Mensaje mensaje) {
        this.pizarrin = mensaje.deformar();
    }

    public String mostrarMensaje() {
        return this.pizarrin;
    }
}