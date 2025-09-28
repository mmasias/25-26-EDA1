public class Escenario {

    private static final int MAX_NINOS = 100;
    private static final int RETARDO_ENTRE_LLEGADAS_MS = 500;

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        Monitora aisha = new Monitora("Aisha");
        Monitora lydia = new Monitora("Lydia");

        ludoteca.setReceptora(lydia);
        ludoteca.setOrganizadora(aisha);

        System.out.println("Simulación: los niños van llegando automáticamente...");

        int contador = 0;
        while (contador < MAX_NINOS) {
            contador++;
            Nino nino = new Nino("Nino" + contador);
            lydia.recibirNino(nino);

            try {
                Thread.sleep(RETARDO_ENTRE_LLEGADAS_MS);
            } catch (InterruptedException e) {
                System.out.println("Simulación interrumpida.");
                break;
            }
        }

        System.out.println("Fin de la simulación (llegaron " + contador + " niños).");
    }
}
