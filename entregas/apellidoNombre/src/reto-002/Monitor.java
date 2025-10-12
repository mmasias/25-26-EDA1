public class Monitor {
    private String nombre;

    public Monitor(String nombre)
    {
        this.nombre = nombre;
    }
    public void presentarse(){
        System.out.println("Hola me llamo" + nombre);
        
    } 

    public String getNombre(){
        return nombre;
    }
}

