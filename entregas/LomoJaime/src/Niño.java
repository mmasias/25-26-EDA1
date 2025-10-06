class Niño {
    private String nombre;
    private int edad;
    private Pizarra pizarrin;

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
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
        if (pizarrin == null) {
            pizarrin = new Pizarra();
        }
        pizarrin.escribirMensaje(modificarMensaje(mensaje));
    }

    private String modificarMensaje(String mensaje) {
        java.util.Random random = new java.util.Random();
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

        int errores = random.nextInt(2) + 1;
        for (int i = 0; i < errores; i++) {
            if (mensaje.length() > 0) {
                int indiceAleatorio = random.nextInt(mensaje.length());
                char letraAleatoria = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
                mensaje = mensaje.substring(0, indiceAleatorio) + letraAleatoria + mensaje.substring(indiceAleatorio + 1);
            }
        }
        return mensaje;
    }

    public String mostrarMensaje() {
        return pizarrin != null ? pizarrin.leerMensaje() : "";
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) {
            pizarrin.limpiar();
        }
    }
}