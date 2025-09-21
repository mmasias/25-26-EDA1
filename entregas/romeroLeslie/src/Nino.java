public class Nino {
    private int id;
    private String pizarrin;

    public Nino(int id) {
        this.id = id;
        this.pizarrin = "";
    }

    
    public int id() {
        return id;
    }

    public String pizarrin() {
        return pizarrin;
    }

    public void escribir(String mensaje) {
        pizarrin = mensaje;
    }
}