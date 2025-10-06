import java.util.*;
import java.util.function.Predicate;

public class Cola {
    private LinkedList<Nino> q = new LinkedList<>();

    public void enqueue(Nino n) {
        q.addLast(n);
    }

    public Nino dequeue() {
        return q.isEmpty() ? null : q.removeFirst();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public List<Nino> toList() {
        return new ArrayList<>(q);
    }

    public void transferAllTo(Cola dest) {
        while (!q.isEmpty()) {
            dest.enqueue(q.removeFirst());
        }
    }

    public void enqueueAll(Collection<Nino> c) {
        for (Nino n : c) enqueue(n);
    }

    public void clear() {
        q.clear();
    }

    public List<Nino> getFirstN(int n) {
        List<Nino> res = new ArrayList<>();
        Iterator<Nino> it = q.iterator();
        while (it.hasNext() && res.size() < n) res.add(it.next());
        return res;
    }

    public List<Nino> getLastN(int n) {
        List<Nino> list = toList();
        int start = Math.max(0, list.size() - n);
        return list.subList(start, list.size());
    }

    public List<Nino> filter(Predicate<Nino> p) {
        List<Nino> res = new ArrayList<>();
        for (Nino ni : q) if (p.test(ni)) res.add(ni);
        return res;
    }
}
