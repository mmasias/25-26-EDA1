
public abstract class Monitor {

    private String nombre;
    private Cola colaNiños;

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
