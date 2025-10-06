public class Monitor {

    private static final int MAX_NIÑOS = 50;

    private String nombre;
    private Niño[] niños;
    private int contador;  
    

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.niños = new Niño[MAX_NIÑOS]; 
        this.contador = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibeNiño(Niño niño) {
        if (contador < niños.length) {
            niños[contador] = niño;
            contador++;
        } else {
            System.out.println("No se pueden añadir más niños a " + nombre);
        }
    }

    public void entregaNiños(Monitor otro) {
        for (int i = 0; i < contador; i++) {
            otro.recibeNiño(niños[i]);
        }
        contador = 0; 
    }

    public Niño[] getNiños() {
        Niño[] copia = new Niño[contador];
        for (int i = 0; i < contador; i++) {
            copia[i] = niños[i];
        }
        return copia;
    }

    public int numeroNiños() {
        return contador;
    }

    public void mostrarListaNiños() {
        Console console = new Console();
        if (contador == 0) {
            console.writeln(nombre + " no tiene niños actualmente.");
            return;
        }
        console.writeln(nombre + " tiene a los siguientes niños:");
        for (int i = 0; i < contador; i++) {
            console.writeln("- " + niños[i]);
        }
    }

    public void vaciar() {
        contador = 0;
    }
}