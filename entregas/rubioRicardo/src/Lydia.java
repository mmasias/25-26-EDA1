public class Lydia {

    
    private final ColaCircular esperando = new ColaCircular(200);

    
    public void generarLlegadas(Tiempo tiempo) {
        int t = tiempo.totalMinutos();
        if (t < 10) {
            int cuantos = (int) (Math.random() * 3);
            for (int i = 0; i < cuantos; i++) {
                Nino nuevo = new Nino("Niño" + (t + i + 1), t);
                try { esperando.encolar(nuevo); } catch (IllegalStateException e) { /* cola llena improbable */ }
                System.out.println("Llega " + nuevo + " a Lydia (fase 1) en " + tiempo);
            }
        } else if (t < 30) {
            if ((t - 10) % 3 == 0) {
                if (Math.random() < 0.5) {
                    Nino nuevo = new Nino("Niño" + t, t);
                    try { esperando.encolar(nuevo); } catch (IllegalStateException e) { /* cola llena improbable */ }
                    System.out.println("Llega " + nuevo + " a Lydia (fase 2) en " + tiempo);
                }
            }
        }
    }

    public boolean tieneEsperando() { return !esperando.estaVacia(); }

    public Nino darNino() { return esperando.desencolar(); }

    public void imprimirLista() {
        System.out.print("Lydia: ");
        esperando.imprimirEstado();
    }

    public Nino[] snapshotEsperando() { return esperando.copiarCola(); }
}
