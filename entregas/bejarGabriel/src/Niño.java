import java.util.concurrent.atomic.AtomicInteger;

public class Niño {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int id;
    private String pizarrin;

    public Niño() {
        this.id = COUNTER.getAndIncrement();
        this.pizarrin = "";
    }

    public int getId() { return id; }
    public String getPizarrin() { return pizarrin; }
    public void setPizarrin(String texto) { this.pizarrin = texto; }

    @Override
    public String toString() {
        return "Niño#" + id;
    }
}