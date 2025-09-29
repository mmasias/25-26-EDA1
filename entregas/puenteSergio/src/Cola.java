import java.util.StringJoiner;


public class Cola {
private final Niño[] contenedor;
private final int capacidad;
private int inicio = 0;
private int fin = 0;
private int cantidad = 0;


public Cola(int capacidad) {
this.capacidad = capacidad;
this.contenedor = new Niño[capacidad];
}


public void addNiño(Niño n) {
if (cantidad >= capacidad) {
System.out.println("ERROR: Cola llena. No se puede añadir " + n.getNombre());
return;
}
contenedor[fin] = n;
fin = (fin + 1) % capacidad;
cantidad++;
}


public Niño removeNiño() {
if (cantidad == 0) return null;
Niño salida = contenedor[inicio];
contenedor[inicio] = null;
inicio = (inicio + 1) % capacidad;
cantidad--;
return salida;
}


public boolean hayNiños() { return cantidad > 0; }
public int size() { return cantidad; }


public Niño getNiño(int posicion) {
if (posicion < 0 || posicion >= cantidad) return null;
int idx = (inicio + posicion) % capacidad;
return contenedor[idx];
}


public void listarNiños() {
StringJoiner sj = new StringJoiner(" / ");
for (int i = 0; i < cantidad; i++) {
Niño n = getNiño(i);
if (n != null) sj.add(n.getNombre());
}
System.out.println(sj.toString());
}
}

