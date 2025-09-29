public class Pizarra {
private String mensaje = "";


public void escribirMensaje(String m) { mensaje = (m == null) ? "" : m; }
public String leerMensaje() { return mensaje; }
public void limpiar() { mensaje = ""; }
public boolean estaLimpia() { return mensaje.isEmpty(); }
}

