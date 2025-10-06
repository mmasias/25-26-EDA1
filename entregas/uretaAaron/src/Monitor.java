import java.util.*;

public class Monitor {
    private String nombre;
    private Cola cola;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.cola = new Cola();
    }

    public String getNombre() {
        return nombre;
    }

    public void recibir(Nino n) {
        cola.enqueue(n);
    }

    public void recibirTodos(Collection<Nino> niños) {
        cola.enqueueAll(niños);
    }

    public void transferirTodoAMonitor(Monitor destino) {
        this.cola.transferAllTo(destino.cola);
    }

    public int contar() {
        return cola.size();
    }

    public List<Nino> getLista() {
        return cola.toList();
    }

    public List<Nino> primeros(int n) {
        return cola.getFirstN(n);
    }

    public List<Nino> ultimos(int n) {
        return cola.getLastN(n);
    }

    public List<Nino> filtrarPorEdadMayorQue(int edad) {
        return cola.filter(nn -> nn.getEdad() > edad);
    }

    public List<Nino> filtrarPorInicial(char letra) {
        char L = Character.toLowerCase(letra);
        return cola.filter(nn -> {
            String nom = nn.getNombre();
            return !nom.isEmpty() && Character.toLowerCase(nom.charAt(0)) == L;
        });
    }

    public void vaciar() {
        cola.clear();
    }

    public double edadPromedio() {
        List<Nino> list = cola.toList();
        if (list.isEmpty()) return 0.0;
        double s = 0;
        for (Nino n : list) s += n.getEdad();
        return s / list.size();
    }
}
