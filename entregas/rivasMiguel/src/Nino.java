public class Nino {
    private String nombre;
    private Pizarra pizarrin;
    private String ultimoRecibido = "";

    private int edad = 0;

    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void presentarse() {
        System.out.println(nombre + " dice: Hola, me llamo " + nombre + " y tengo " + edad + " años.");
    }

    public boolean nombreEmpiezaPor(char letra) {
        if (nombre == null || nombre.length() == 0) return false;
        return Character.toLowerCase(nombre.charAt(0)) == Character.toLowerCase(letra);
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirPizarrin(Pizarra p) {
        this.pizarrin = p;
    }

    public Pizarra getPizarrin() {
        return pizarrin;
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) pizarrin.limpiar();
    }

    public void recibirMensaje(String msg) {
        this.ultimoRecibido = msg;
    }

    public String escribirMensajeConProbabilidad() {
        if (ultimoRecibido == null) ultimoRecibido = "";

        
        double r = Math.random();
        int cambios;
        if (r < 0.50) cambios = 0;
        else if (r < 0.85) cambios = 1;
        else cambios = 2;

        String nuevo = modificarLetras(ultimoRecibido, cambios);
        if (pizarrin != null) pizarrin.escribir(nuevo);
        System.out.println(nombre + " escribe en su pizarrín: " + nuevo + (cambios > 0 ? " (cambió " + cambios + " letra(s))" : ""));
        return nuevo;
    }

    private String modificarLetras(String msg, int cambios) {
        if (msg == null) msg = "";
        if (msg.length() == 0 || cambios == 0) return msg;

        char[] arr = msg.toCharArray();
        int len = arr.length;
        for (int i = 0; i < cambios; i++) {
            int pos = (int)(Math.random() * len);
            char orig = arr[pos];
            char nuevo;
            do {
                nuevo = (char) ('A' + (int)(Math.random() * 26));
            } while (nuevo == orig);
            arr[pos] = nuevo;
        }
        return new String(arr);
    }
}
