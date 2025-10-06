public class Monitora {
    private String nombre;
    private Ninio[] cola;   
    private int cantidad;   

    public Monitora(String nombre) {
        this.nombre = nombre;
        this.cola = new Ninio[0];   
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Ninio[] getCola() {
        return cola;
    }

    public void recibirNinio(Ninio n) {
        
        Ninio[] nuevaCola = new Ninio[cantidad + 1];

        for (int i = 0; i < cantidad; i++) {
            nuevaCola[i] = cola[i];
        }
      
        nuevaCola[cantidad] = n;

        cola = nuevaCola;
        cantidad++;

        System.out.println(nombre + " recibe a " + n.toString());
    }

    public void mostrarCola() {
        if (cantidad == 0) {
            System.out.println("Cola vacía");
        } else {
            System.out.println("Niños en cola: " + cantidad);
            for (int i = 0; i < cantidad; i++) {
                System.out.println("  - " + cola[i].toString());
            }
        }
    }

    public void transferirA(Monitora destino) {
        for (int i = 0; i < cantidad; i++) {
            destino.recibirNinio(cola[i]);
        }
       
        cola = new Ninio[0];
        cantidad = 0;
    }

    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + ", monitora de esta ludoteca");
    }
}
