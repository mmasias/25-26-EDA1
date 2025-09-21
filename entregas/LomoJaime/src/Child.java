import java.util.Random;

public class Child {
    private final String nombre;
    private String pizarrin = "";
    private final Random random = new Random();

    public Child(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void limpiarPizarrin() {
        pizarrin = "";
    }

    public String recibirYEscribir(String mensaje) {
        boolean seEquivoca = random.nextDouble() < 0.2; 
        String resultado;

        if (seEquivoca) {
            char[] chars = mensaje.toCharArray();
            int errores = 1 + random.nextInt(2); 
            for (int i = 0; i < errores; i++) {
                int pos = random.nextInt(chars.length);
                chars[pos] = (char) ('a' + random.nextInt(26));
            }
            resultado = new String(chars);
        } else {
            resultado = mensaje; 
        }

        pizarrin = resultado;
        System.out.println(nombre + " recibe: " + mensaje + " -> escribe: " + pizarrin);
        return pizarrin;
    }
}
