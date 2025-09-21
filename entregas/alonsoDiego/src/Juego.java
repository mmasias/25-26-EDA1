public class Juego {
    private ColaNiños cola;
    private String pizarraSalon;

    public Juego(ColaNiños cola) {
        this.cola = cola;
        this.pizarraSalon = "";
    }

    public void limpiarPizarras() {
        pizarraSalon = "";
        for (int i = 0; i < cola.getTamaño(); i++) {
            cola.getNiño(i).setPizarra("");
        }
    }

    public void jugar(String mensaje) {
        limpiarPizarras();

        cola.getNiño(0).setPizarra(mensaje);

        for (int i = 1; i < cola.getTamaño(); i++) {
            String mensajeAnterior = cola.getNiño(i - 1).getPizarra();
            cola.getNiño(i).setPizarra(mensajeAnterior);
        }

        pizarraSalon = cola.getNiño(cola.getTamaño() - 1).getPizarra();
    }

    public String getPizarraSalon() {
        return pizarraSalon;
    }
}
