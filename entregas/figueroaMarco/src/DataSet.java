import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private List<Integer> list;
    
    public DataSet(int capacity) {
        this.list = new ArrayList<>(capacity);
    }
    
    public DataSet() {
        this.list = new ArrayList<>();
    }
    
    public void get(int index) {
        if (index < 0 || index >= list.size()) {
            System.out.println("Índice fuera de rango: " + index);
            return;
        }
        System.out.println(list.get(index));
    }
    
    public void set(int index, int value) {
        if (index < 0 || index >= list.size()) {
            System.out.println("Índice fuera de rango: " + index);
            return;
        }
        list.set(index, value);
    }
    
    public void add(int value) {
        list.add(value);
    }
    
    public void add(int index, int value) {
        if (index < 0 || index > list.size()) {
            System.out.println("Índice fuera de rango: " + index);
            return;
        }
        list.add(index, value);
    }
    
    public void remove(int index) {
        if (index < 0 || index >= list.size()) {
            System.out.println("Índice fuera de rango: " + index);
            return;
        }
        list.remove(index);
    }
    
    public void size() {
        System.out.println(list.size());
    }
    
    public void isEmpty() {
        System.out.println(list.isEmpty());
    }
    
    public void getBefore(int index) {
        if (index <= 0 || index >= list.size()) {
            System.out.println("No hay elemento anterior para el índice: " + index);
            return;
        }
        System.out.println(index - 1);
    }
    
    public void getAfter(int index) {
        if (index < 0 || index >= list.size() - 1) {
            System.out.println("No hay elemento posterior para el índice: " + index);
            return;
        }
        System.out.println(index + 1);
    }
    
    public void display() {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

