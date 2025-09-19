public class JuegoTelefono {
    public static void main(String[] args) {
        
        Fila fila = new Fila(8);

        
        Mensaje mensaje = Mensaje.generar(10);

        
        fila.jugar(mensaje);
    }
}