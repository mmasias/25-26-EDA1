public class Nino {

    private String nombre;
    private Pizarra pizarrin;
    private String ultimoMensaje = "";

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirPizarrin(Pizarra pizarrin) {
        this.pizarrin = pizarrin;
    }

    public Pizarra getPizarrin() {
        return pizarrin;
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) pizarrin.limpiar();
    }

    public void recibirMensaje(String mensaje) {
        this.ultimoMensaje = mensaje;
    }

    public String escribirMensajeConProbabilidad() {
        if (ultimoMensaje == null) ultimoMensaje = "";

        double probabilidad = Math.random();
        int cambios = probabilidad < 0.50 ? 0 : (probabilidad < 0.85 ? 1 : 2);

        String mensajeModificado = modificarLetras(ultimoMensaje, cambios);

        if (pizarrin != null) pizarrin.escribir(mensajeModificado);

        System.out.println(nombre + " escribe en su pizarrín: " + mensajeModificado +
                (cambios > 0 ? " (cambió " + cambios + " letra(s))" : ""));

        return mensajeModificado;
    }

    private String modificarLetras(String mensaje, int cambios) {
        if (mensaje == null || mensaje.isEmpty() || cambios == 0) return mensaje;

        char[] arr = mensaje.toCharArray();
        for (int i = 0; i < cambios; i++) {
            int pos = (int) (Math.random() * arr.length);
            char original = arr[pos];
            char nuevo;
            do {
                nuevo = (char) ('A' + (int) (Math.random() * 26));
            } while (nuevo == original);
            arr[pos] = nuevo;
        }
        return new String(arr);
    }
}
