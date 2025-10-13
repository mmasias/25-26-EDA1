
public abstract class Monitor {

    protected String nombre;
    protected Cola colaNiños;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
    }

    abstract void imprimirEstado();

    public Cola getCola() {
        return colaNiños;
    }
    
    abstract void pideNiño(Monitor otroMonitor);

}
