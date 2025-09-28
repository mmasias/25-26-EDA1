public class Monitora {

    private String nombre;
    private Monitora organizadora;
    private Ludoteca ludoteca;

    public Monitora(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setOrganizadora(Monitora organizadora) {
        this.organizadora = organizadora;
    }

    public void abrirLudoteca(Ludoteca ludoteca) {
        this.ludoteca = ludoteca;
    }

    public void recibirNino(Nino nino) {
        if (organizadora != null && this != organizadora) {
            System.out.println(nombre + " recibe al " + nino.getNombre() + " y se lo pasa a " + organizadora.getNombre() + ".");
            organizadora.recibirNino(nino);
        } else if (ludoteca != null) {
            System.out.println(nombre + " coloca al " + nino.getNombre() + " en la fila y le da un pizarrín.");
            ludoteca.addNino(nino);
        } else {
            System.out.println(nombre + " no puede procesar al " + nino.getNombre() + " (no hay organizadora ni ludoteca).");
        }
    }

    public void escribirMensaje(Pizarra pizarra, String mensaje) {
        System.out.println(nombre + " escribe en " + pizarra.getNombre() + ": " + mensaje);
        pizarra.escribir(mensaje);
    }
}
