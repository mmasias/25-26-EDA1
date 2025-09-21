public class Nino extends Persona {
    private final int minutoDeLlegada;

    public Nino(String nombreRecibido, int minutoRecibido) {
        super(nombreRecibido);
        this.minutoDeLlegada = minutoRecibido;
    }

    public String escribirMensaje(String mensajeRecibido) {
        String mensajeEscrito;
        mensajeEscrito = Mensaje.deformarMensaje(mensajeRecibido);
        return mensajeEscrito;
    }

    public void escribirEnPizarra(String mensajeRecibido) {
        System.out.println("El ultimo nino escribe en pizarra: " + mensajeRecibido);
    }
}
