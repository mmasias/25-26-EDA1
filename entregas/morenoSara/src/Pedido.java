public class Pedido {
    private String tipoPlato;
    private int minutosTotales;
    private int minutosRestantes;
    private int minutoDeLlegada;

    public Pedido(String tipoPlato, int minutosTotales, int minutoDeLlegada) {
        this.tipoPlato = tipoPlato;
        this.minutosTotales = minutosTotales;
        this.minutosRestantes = minutosTotales;
        this.minutoDeLlegada = minutoDeLlegada;
    }

    public String getTipoPlato() { return tipoPlato; }
    public int getMinutosRestantes() { return minutosRestantes; }
    public int getMinutoDeLlegada() { return minutoDeLlegada; }
    public int getMinutosTotales() { return minutosTotales; }

    public void reducirUnMinuto() {
        minutosRestantes--;
    }

    public String toString() {
        return tipoPlato + " (" + minutosRestantes + " min restantes)";
    }
}

