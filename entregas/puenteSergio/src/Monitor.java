public class Monitor {


public void iniciarJuego(Pizarra pizarraSalon) {
if (jugando) return;
jugando = true;
indiceTurno = 0;
pizarraSalonEnJuego = pizarraSalon;
limpiarPizarrines();
String inicial = generarMensajeInicial(Configuracion.LONGITUD_MENSAJE);
Niño primer = cola.getNiño(0);
if (primer != null) {
primer.recibirMensaje(inicial);
System.out.println(nombre + " muestra el mensaje inicial al primer niño: " + inicial);
}
}


public void jugarUnPaso() {
if (!jugando) return;
int tam = cola.size();
if (tam == 0) { terminarJuego(); return; }
if (indiceTurno < 0 || indiceTurno >= tam) { terminarJuego(); return; }


Niño actual = cola.getNiño(indiceTurno);
if (indiceTurno + 1 < tam) {
Niño siguiente = cola.getNiño(indiceTurno + 1);
if (actual != null && siguiente != null) {
String paraSiguiente = actual.mostrarMensaje();
siguiente.recibirMensaje(paraSiguiente);
System.out.println("[" + nombre + "] " + actual.getNombre() + " -> " + siguiente.getNombre()
+ " (texto mostrado: " + paraSiguiente + ")");
}
indiceTurno++;
} else {
if (actual != null) {
String texto = actual.mostrarMensaje();
pizarraSalonEnJuego.escribirMensaje(texto);
System.out.println(actual.getNombre() + " corre y escribe en la pizarra del salón: " + texto);
}
terminarJuego();
}
}


private void terminarJuego() {
jugando = false;
indiceTurno = 0;
pizarraSalonEnJuego = null;
System.out.println(nombre + " ha terminado la partida.");
}


private void limpiarPizarrines() {
for (int i = 0; i < cola.size(); i++) {
Niño n = cola.getNiño(i);
if (n != null) n.limpiarPizarrin();
}
}


private String generarMensajeInicial(int longitud) {
StringBuilder sb = new StringBuilder();
String alfab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
Random r = new Random();
for (int i = 0; i < longitud; i++) sb.append(alfab.charAt(r.nextInt(alfab.length())));
return sb.toString();
}


public void mostrarListaNiños() { System.out.print("> " + nombre + " --> "); cola.listarNiños(); }
}

