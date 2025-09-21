import java.util.concurrent.atomic.AtomicInteger;

public class Niño {

    private static final AtomicInteger contadorIds = new AtomicInteger(0);

    private final int id;

    public Niño() {
        this.id = contadorIds.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Niño{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Niño)) return false;
        Niño otro = (Niño) obj;
        return this.id == otro.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
