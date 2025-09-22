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

    public void recibirNino(Nino n) {
        if (organizadora != null && this != organizadora) {
            System.out.println(nombre + " recibe al " + n.getNombre() + " y se lo pasa a " + organizadora.getNombre() + ".");
            organizadora.recibirNino(n);
        } else if (ludoteca != null) {
            System.out.println(nombre + " coloca al " + n.getNombre() + " en la fila y le da un pizarrín.");
            ludoteca.addNino(n);
        } else {
            System.out.println(nombre + " no puede procesar al " + n.getNombre() + " (no hay organizadora ni ludoteca).");
        }
    }

    public void escribirMensaje(Pizarra p, String mensaje) {
        System.out.println(nombre + " escribe en " + p.getNombre() + ": " + mensaje);
        p.escribir(mensaje);
    }
}
