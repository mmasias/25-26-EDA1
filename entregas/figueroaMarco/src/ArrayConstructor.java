import java.util.ArrayList;
import java.util.List;

public class ArrayConstructor {
    
    private static class Node {
        Integer data;
        int before;
        int after;
        
        public Node(Integer data, int before, int after) {
            this.data = data;
            this.before = before;
            this.after = after;
        }
    }
    
    private List<Node> list;
    private int cabeza;
    private int primerLibre;
    private int tamaño;
    
    public ArrayConstructor(int capacidad) {
        this.list = new ArrayList<>(capacidad);
        this.cabeza = -1;
        this.primerLibre = 0;
        this.tamaño = 0;
        
        for (int i = 0; i < capacidad; i++) {
            list.add(new Node(null, -1, i + 1));
        }
        if (capacidad > 0) {
            list.get(capacidad - 1).after = -1;
        }
    }
    
    public ArrayConstructor() {
        this(10);
    }
    
    public DataSet crearArray(int capacity) {
        return new DataSet(capacity);
    }
    
    public DataSet crearArray() {
        return new DataSet();
    }
    
    public Integer[] getArray() {
        Integer[] array = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).data;
        }
        return array;
    }
    
    public int[] getReferencias() {
        int[] referencias = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            referencias[i] = list.get(i).after;
        }
        return referencias;
    }
    
    public int getCabeza() {
        return cabeza;
    }
    
    public int getPrimerLibre() {
        return primerLibre;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public int getCapacidad() {
        return list.size();
    }
    
    public void setCabeza(int cabeza) {
        this.cabeza = cabeza;
    }
    
    public void setPrimerLibre(int primerLibre) {
        this.primerLibre = primerLibre;
    }
    
    public void incrementarTamaño() {
        this.tamaño++;
    }
    
    public void decrementarTamaño() {
        this.tamaño--;
    }
    
    public Integer getData(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index).data;
        }
        return null;
    }
    
    public void setData(int index, Integer data) {
        if (index >= 0 && index < list.size()) {
            list.get(index).data = data;
        }
    }
    
    public int getBefore(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index).before;
        }
        return -1;
    }
    
    public void setBefore(int index, int before) {
        if (index >= 0 && index < list.size()) {
            list.get(index).before = before;
        }
    }
    
    public int getAfter(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index).after;
        }
        return -1;
    }
    
    public void setAfter(int index, int after) {
        if (index >= 0 && index < list.size()) {
            list.get(index).after = after;
        }
    }
    
    public void display() {
        System.out.println("Lista de nodos:");
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            System.out.println("Index " + i + ": data=" + node.data + 
                             ", before=" + node.before + 
                             ", after=" + node.after);
        }
        System.out.println("Cabeza: " + cabeza);
        System.out.println("Primer libre: " + primerLibre);
        System.out.println("Tamaño: " + tamaño);
    }
}
