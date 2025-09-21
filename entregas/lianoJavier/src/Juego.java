public class Juego {

    private boolean iniciado = false;
    private int posicion = -1;

    public boolean estaIniciado() {
        return iniciado;
    }

    public void inicia() {
        iniciado = true;
        posicion = -1;
    }

    public void termina() {
        iniciado = false;
        posicion = -1;
    }

    public int getPosicion() {
        return posicion;
    }

    public void siguiente() {
        posicion++;
    }

}
