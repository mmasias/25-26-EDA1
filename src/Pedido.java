public class Pedido {
    public enum Plato {
        BEBIDA("Bebida", 1, 2),
        CAFE("Café", 2, 3),
        COLACAO("Colacao", 2, 4),
        BOCADILLO("Bocadillo", 3, 5),
        ENSALADA("Ensalada", 5, 8);

        public final String nombre;
        public final int min;
        public final int max;

        Plato(String nombre, int min, int max) {
            this.nombre = nombre;
            this.min = min;
            this.max = max;
        }

        public int tiempoAleatorio(java.util.Random r) {
            return min + r.nextInt(max - min + 1);
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    private static int NEXT_ID = 1;
    public final int id;
    public final Plato plato;
    public final int tiempoInicial;
    public int tiempoRestante;
    public final int llegada;
    public Integer inicio = null;
    public Integer fin = null;

    public Pedido(Plato plato, int tiempo, int llegada) {
        this.id = NEXT_ID++;
        this.plato = plato;
        this.tiempoInicial = tiempo;
        this.tiempoRestante = tiempo;
        this.llegada = llegada;
    }

    public String mostrarEstado() {
        return plato + " - " + tiempoRestante + " min restantes";
    }

    @Override
    public String toString() {
        return plato + " (" + tiempoInicial + " min)";
    }
}
