
public class Recepcionista extends Monitor {

    public Recepcionista(String nombre) {
        super(nombre);
    }

    public void recibir(Niño niño) {
        this.colaNiños.poner(niño);
    }

    protected void imprimirEstado() {
        Console.imprimir("Recepcionista " + nombre + " tiene " + colaNiños.toArray().length + " niños esperando.");
        Console.imprimirLinea();
        for (Niño niño : colaNiños.toArray()) {
            Console.imprimir(" - " + niño.getNombre());
            Console.imprimirLinea();
        }
    }

    protected void pideNiño(Monitor otroMonitor) {
        if (otroMonitor.getCola().hayNiños()) {
            Niño niño = otroMonitor.getCola().sacar();
            this.colaNiños.poner(niño);
        }
    }

}
