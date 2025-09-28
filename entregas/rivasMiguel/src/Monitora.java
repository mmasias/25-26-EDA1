public class Monitora {
    private String nombre;
    private Monitora organizadora;
    private Ludoteca ludoteca;

    private Nino[] espera;
    private int esperaCount;

    public Monitora(String nombre) {
        this.nombre = nombre;
        this.espera = new Nino[200];
        this.esperaCount = 0;
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

    public Ludoteca getLudoteca() {
        return ludoteca;
    }

    public void recibirNino(Nino n) {
        if (organizadora != null && this != organizadora) {
            if (organizadora.getLudoteca() != null && organizadora.getLudoteca().isJuegoEnCurso()) {
                if (esperaCount >= espera.length) {
                    Nino[] nuevo = new Nino[espera.length * 2];
                    for (int i = 0; i < espera.length; i++) nuevo[i] = espera[i];
                    espera = nuevo;
                }
                espera[esperaCount++] = n;
                System.out.println(nombre + " recibe al " + n.getNombre() + " pero hay un juego en curso: lo sienta en espera.");
            } else {
                System.out.println(nombre + " recibe al " + n.getNombre() + " y se lo pasa a " + organizadora.getNombre() + ".");
                organizadora.recibirNino(n);
            }
        } else if (ludoteca != null) {
            System.out.println(nombre + " coloca al " + n.getNombre() + " en la fila y le da un pizarrín.");
            ludoteca.addNino(n);
        } else {
            System.out.println(nombre + " no puede procesar al " + n.getNombre() + " (no hay organizadora ni ludoteca).");
        }
    }

    public void pasarNinosAOrganizadora() {
        if (esperaCount == 0) return;
        System.out.println(nombre + " pasa " + esperaCount + " niños de la espera a la organizadora (" + (organizadora != null ? organizadora.getNombre() : "N/A") + ").");
        for (int i = 0; i < esperaCount; i++) {
            Nino n = espera[i];
            if (n != null && organizadora != null) {
                organizadora.recibirNino(n);
            } else if (n != null && ludoteca != null) {
                ludoteca.addNino(n);
            }
            espera[i] = null;
        }
        esperaCount = 0;
    }

    public void escribirMensaje(Pizarra p, String mensaje) {
        System.out.println(nombre + " escribe en " + p.getNombre() + ": " + mensaje);
        p.escribir(mensaje);
    }
}
