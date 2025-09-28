
public class Recepcionista extends Monitor {

    public Recepcionista(String nombre) {
        super(nombre);
    }

    public void recibir(Niño niño) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recibir'");
    }

    protected void imprimirEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'imprimirEstado'");
    }

    protected void pideNiño(Monitor otroMonitor) {
        if (otroMonitor.getCola().hayNiños()) {
            Niño niño = otroMonitor.getCola().sacar();
            this.colaNiños.poner(niño);
        }
    }

}
