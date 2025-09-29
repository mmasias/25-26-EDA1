public class Mundo {
private final Ludoteca ludoteca;
private final int duracion;
private final Llegadas llegadas;


public Mundo(int duracion) {
this.ludoteca = new Ludoteca();
this.duracion = duracion;
this.llegadas = new Llegadas(Configuracion.SEMILLA_AZAR);
}


public void iniciarSimulacion() {
for (int minuto = 0; minuto < duracion; minuto++) {
System.out.println("===== Minuto " + minuto + " =====");
if (llegadas.llegaNiño(minuto)) {
Niño n = new Niño("Niño");
System.out.println("Llega " + n.getNombre());
ludoteca.recibirNiño(n);
}
ludoteca.actualizar();
ludoteca.verEstado();

}
}


public static void main(String[] args) {
Mundo mundo = new Mundo(40);
mundo.iniciarSimulacion();
}



static class Configuracion {
static final int DURACION_MINUTOS = 120;
static final int CAPACIDAD_COLA = 20;
static final int MIN_PARA_JUGAR = 5;
static final int LONGITUD_MENSAJE = 10;
static final long SEMILLA_AZAR = 42L;
}


static class Llegadas {
private final Random rng;
Llegadas(long semilla) { this.rng = new Random(semilla); }


boolean llegaNiño(int minuto) {
if (minuto < 10) {
int n = rng.nextInt(3); 
return n > 0; 
}
if (minuto < 30) {
if ((minuto - 10) % 3 == 0) return rng.nextBoolean();
return false;
}
return false;
}
}
}