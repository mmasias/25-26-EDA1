
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
    
    public void pideNiño(Monitor otroMonitor) {
        if (otroMonitor.getCola().hayNiños() && !this.colaNiños.estaCompleta()) {
            Niño niño = otroMonitor.getCola().sacar();
            this.colaNiños.poner(niño);
        }
    }

}
