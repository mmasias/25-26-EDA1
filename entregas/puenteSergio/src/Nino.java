public class Nino {
    private String nombre;
    private int edad;
    private Pizarra pizarrin;

    public Nino(String nombre, int edad) {
        this.nombre = nombre != null ? nombre.trim() : "";
        this.edad = Math.max(0, edad);
    }

    public void recibirPizarrin(Pizarra pizarra) {
        this.pizarrin = pizarra;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void recibirMensaje(String mensaje) {
        if (pizarrin == null) return;
        pizarrin.escribirMensaje(modificarMensaje(mensaje));
        System.out.println("[" + nombre + "] recibe [" + mensaje + "] y ha escrito [" + pizarrin.leerMensaje() + "]");
    }

    private String modificarMensaje(String mensaje) {
        if (mensaje == null || mensaje.isEmpty()) return "";
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
        int errores = (int) (Math.random() * 2) + 1; // 1 o 2 errores
        StringBuilder sb = new StringBuilder(mensaje);
        for (int i = 0; i < errores; i++) {
            if (sb.length() == 0) break;
            int idx = (int) (Math.random() * sb.length());
            char r = ALFABETO.charAt((int) (Math.random() * ALFABETO.length()));
            sb.setCharAt(idx, r);
        }
        return sb.toString();
    }

    public String presentarse() {
        return "Hola, soy " + nombre + " y tengo " + edad + " años";
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
