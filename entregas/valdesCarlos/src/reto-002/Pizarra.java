
public class Pizarra {
    private String mensaje;

    public Pizarra() {
        this.mensaje = "";
    }

    public void limpiar() {
        mensaje = "";
    }

    public void escribir(String texto) {
        if (texto == null) texto = "";
        this.mensaje = texto;
    }

    public String leer() {
        return mensaje;
    }

    @Override
    public String toString() {
        return mensaje.isEmpty() ? "[Vacía]" : mensaje;
    }
}
