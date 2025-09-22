public class Lidia {
    private Nino[] retenidos = new Nino[0];

    public void recibirNino(Nino n, boolean juegoEnCurso) {
        if (juegoEnCurso) {
            Nino[] nuevo = new Nino[retenidos.length + 1];
            for (int i = 0; i < retenidos.length; i++) {
                nuevo[i] = retenidos[i];
            }
            nuevo[retenidos.length] = n;
            retenidos = nuevo;
        } else {
            System.out.println("Lidia pasa a " + n.getNombre() + " con Aisha.");
        }
    }

    public Nino[] liberarNinos() {
        Nino[] copia = retenidos;
        retenidos = new Nino[0];
        return copia;
    }
}