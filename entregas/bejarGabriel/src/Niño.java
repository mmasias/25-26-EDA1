class Niño {
    private static int contador = 0;
    private final int id;
    public Niño() {
        this.id = ++contador;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Niño{" + "id=" + id + '}';
    }
}