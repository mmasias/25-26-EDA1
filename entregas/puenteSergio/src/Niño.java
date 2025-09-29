import java.util.Random;


public class Niño {
private static int contador = 1;
private final String nombre;
private Pizarra pizarrin = null;
private final Random rng;


public Niño(String nombre) {
this.nombre = nombre + "#" + (contador++);
this.rng = new Random(System.identityHashCode(this));
}


public String getNombre() { return nombre; }


public void recibirPizarrin(Pizarra p) { this.pizarrin = p; }


public void recibirMensaje(String texto) {
if (pizarrin == null) {
System.out.println(getNombre() + " no tiene pizarrín para escribir.");
return;
}
String modificado = modificarMensaje(texto);
pizarrin.escribirMensaje(modificado);
System.out.println("[" + nombre + "] recibe [" + texto + "] y escribe [" + modificado + "] en su pizarrín");
}


public String mostrarMensaje() { return (pizarrin == null) ? "" : pizarrin.leerMensaje(); }


public void limpiarPizarrin() { if (pizarrin != null) pizarrin.limpiar(); }


private String modificarMensaje(String texto) {
if (texto == null || texto.isEmpty()) return texto;
char[] arr = texto.toCharArray();
int cambios = rng.nextInt(3); // 0..2
String alfab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
for (int i = 0; i < cambios; i++) {
int pos = rng.nextInt(arr.length);
arr[pos] = alfab.charAt(rng.nextInt(alfab.length()));
}
return new String(arr);
}
}