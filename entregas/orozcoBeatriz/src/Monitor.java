public abstract class Monitor {
    protected String nombre;

    public Monitor(String nombre) { 
        this.nombre = nombre; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
}