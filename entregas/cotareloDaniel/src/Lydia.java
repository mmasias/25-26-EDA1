public class Lydia extends Trabajadora {
    private int contadorNinos;

    public Lydia(String nombreRecibido) {
        super(nombreRecibido);
        this.contadorNinos = 0;
    }

    public Nino recibirNino(int minutoLlegada) {
        this.contadorNinos = this.contadorNinos + 1;

        String nombreGenerado;
        nombreGenerado = "Nino_" + this.contadorNinos;

        Nino ninoNuevo;
        ninoNuevo = new Nino(nombreGenerado, minutoLlegada);
        return ninoNuevo;
    }
}
