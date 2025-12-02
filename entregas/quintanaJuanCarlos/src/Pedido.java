
public class Pedido {

    private int identificador;
    private TipoPlato tipoPlato;
    private int duracionTotal;
    private int duracionRestante;
    private int instanteLlegadaMinuto;
    private int tiempoEnEsperaMinutos;

    public Pedido(int identificador, TipoPlato tipoPlato, int duracionTotal, int instanteLlegadaMinuto) {
        this.identificador = identificador;
        this.tipoPlato = tipoPlato;
        this.duracionTotal = duracionTotal;
        this.duracionRestante = duracionTotal;
        this.instanteLlegadaMinuto = instanteLlegadaMinuto;
        this.tiempoEnEsperaMinutos = 0;
    }

    public void decrementarUnMinutoDeDuracionRestante() {
        if (duracionRestante > 0) {
            duracionRestante--;
        }
    }

    public void incrementarUnMinutoDeEspera() {
        tiempoEnEsperaMinutos++;
    }

    public boolean estaTerminado() {
        return duracionRestante <= 0;
    }

    public int identificador() {
        return identificador;
    }

    public TipoPlato tipoPlato() {
        return tipoPlato;
    }

    public int duracionTotal() {
        return duracionTotal;
    }

    public int duracionRestante() {
        return duracionRestante;
    }

    public int instanteLlegada() {
        return instanteLlegadaMinuto;
    }

    public int tiempoEnEspera() {
        return tiempoEnEsperaMinutos;
    }
}
