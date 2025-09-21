

class Nino {
    String nombre;
    String pizarrin;

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarrin = "";
    }

    public void limpiarPizarrin() {
        this.pizarrin = "";
    }

    
    public void recibirMensaje(Mensaje mensaje, int numero) {
        mensaje.deformar(numero);          
        this.pizarrin = mensaje.getTexto(); 
    }

    public String mostrarMensaje() {
        return this.pizarrin;
    }
}
