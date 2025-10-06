public class Juego {
    private ColaNinos cola;
    private String pizarraSalon;

    public Juego(ColaNinos cola) {
        this.cola = cola;
        this.pizarraSalon = "";
    }

    public void limpiarPizarras() {
        pizarraSalon = "";
        for (int i = 0; i < cola.getTamaño(); i++) {
            cola.getNino(i).setPizarra("");
        }
    }

    public void jugar(String mensaje) {
        limpiarPizarras();
        cola.getNino(0).setPizarra(mensaje);

        for (int i = 1; i < cola.getTamaño(); i++) {
            String mensajeAnterior = cola.getNino(i - 1).getPizarra();
            cola.getNino(i).setPizarra(mensajeAnterior);
        }

        pizarraSalon = cola.getNino(cola.getTamaño() - 1).getPizarra();
    }

    public String getPizarraSalon() {
        return pizarraSalon;
    }
}
