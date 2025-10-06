
public class Pizarra {
    private String[] mensajes;
    private int cantidad;

    private static final int MAX_MENSAJES = 100;

    public Pizarra() {
        mensajes = new String[MAX_MENSAJES];
        cantidad = 0;
    }

    
    public void agregarMensaje(String msg) {
        if (cantidad < MAX_MENSAJES) {
            mensajes[cantidad] = msg;
            cantidad++;
        } else {
            System.out.println("La pizarra está llena. No se pueden agregar más mensajes.");
        }
    }

    
    public void mostrar() {
        System.out.println("=== PIZARRA DE LA LUDOTECA ===");
        for (int i = 0; i < cantidad; i++) {
            System.out.println(mensajes[i]);
        }
        System.out.println("==============================");
    }

    
    public void limpiar() {
        cantidad = 0;
    }
}
