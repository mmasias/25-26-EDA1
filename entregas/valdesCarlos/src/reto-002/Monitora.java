
import java.util.Scanner;

public class Monitora {
    private String nombre;
    private Ninio[] cola;
    private int cantidad;
    private Ninio[] respaldoOriginal; // para mantener referencia si se separan y luego volver

    public Monitora(String nombre) {
        this.nombre = nombre;
        cola = new Ninio[200]; // capacidad suficiente
        cantidad = 0;
    }

    // --- Operaciones genéricas ---
    public void recibirNinio(Ninio n) {
        if (cantidad >= cola.length) {
            System.out.println("Cola llena. No se puede agregar a " + n.getNombre());
            return;
        }
        cola[cantidad++] = n;
        System.out.println(n.getNombre() + " pasa a la cola de " + nombre);
    }

    public int getCantidadNinios() {
        return cantidad;
    }

    public Ninio[] transferirNinios() {
        Ninio[] copia = new Ninio[cantidad];
        for (int i = 0; i < cantidad; i++) copia[i] = cola[i];
        cantidad = 0;
        cola = new Ninio[200];
        return copia;
    }

    public void recibirTransferencia(Ninio[] ninios) {
        if (ninios == null) return;
        for (int i = 0; i < ninios.length; i++) {
            Ninio n = ninios[i];
            if (n != null) {
                recibirNinio(n);
            }
        }
    }

    public void recibirTransferencia(java.util.List<Ninio> lista) {
        if (lista == null) return;
        for (Ninio n : lista) {
            if (n != null) recibirNinio(n);
        }
    }

    public void mostrarEstado() {
        System.out.println(nombre.toUpperCase() + ":");
        if (cantidad == 0) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cantidad);
            for (int i = 0; i < cantidad; i++) {
                System.out.println("  - " + cola[i]);
            }
        }
        System.out.println();
    }

    // ==== Métodos específicos del comportamiento de Aisha (se usan sobre la instancia aisha) ====

    // Presentación general
    public void presentacionGeneral() {
        if (!esAisha()) {
            System.out.println("Este método debe invocarse sobre Aisha.");
            return;
        }
        if (cantidad == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (int i = 0; i < cantidad; i++) {
            Ninio n = cola[i];
            System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
        }
    }

    public void presentacionPorEdad() {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        if (cantidad == 0) { System.out.println("No hay niños en la cola de Aisha"); return; }
        Scanner sc = new Scanner(System.in);
        System.out.print("Edad mínima: ");
        int edad;
        try {
            edad = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Edad inválida.");
            return;
        }
        System.out.println("Aisha pide que se presenten los mayores de " + edad + " años:\n");
        for (int i = 0; i < cantidad; i++) {
            Ninio n = cola[i];
            if (n.getEdad() > edad) {
                System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
            }
        }
    }

    public void presentacionPorInicial() {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        if (cantidad == 0) { System.out.println("No hay niños en la cola de Aisha"); return; }
        Scanner sc = new Scanner(System.in);
        System.out.print("Letra inicial: ");
        String line = sc.nextLine();
        if (line.isEmpty()) { System.out.println("Letra inválida"); return; }
        char letra = Character.toUpperCase(line.charAt(0));
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (int i = 0; i < cantidad; i++) {
            Ninio n = cola[i];
            if (Character.toUpperCase(n.getNombre().charAt(0)) == letra) {
                System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre());
            }
        }
    }

    public void presentarPrimerosCinco() {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        int upto = Math.min(5, cantidad);
        for (int i = 0; i < upto; i++) {
            System.out.println(cola[i].getNombre() + ": Hola, soy " + cola[i].getNombre());
        }
    }

    public void presentarUltimosCinco() {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        int start = Math.max(0, cantidad - 5);
        for (int i = start; i < cantidad; i++) {
            System.out.println(cola[i].getNombre() + ": Hola, soy " + cola[i].getNombre());
        }
    }

    public void mostrarEdadPromedio() {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        if (cantidad == 0) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double suma = 0;
        for (int i = 0; i < cantidad; i++) suma += cola[i].getEdad();
        double prom = suma / cantidad;
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.2f años%n", prom);
    }

    public void intentoJuegoRana() {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        if (cantidad == 0) { System.out.println("No hay niños en la cola de Aisha"); return; }
        int mayores5 = 0;
        for (int i = 0; i < cantidad; i++) if (cola[i].getEdad() >= 5) mayores5++;
        System.out.println("Verificando condiciones para el juego de la rana...");
        System.out.println("Total de niños: " + cantidad);
        System.out.println("Niños de 5 años o más: " + mayores5);
        if (mayores5 > cantidad / 2) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarParaJuego(Monitora dalsy) {
        if (!esAisha()) { System.out.println("Este método debe invocarse sobre Aisha."); return; }
        System.out.println("Separando niños para el juego de la rana...");
        // respaldo del orden original
        respaldoOriginal = new Ninio[cantidad];
        for (int i = 0; i < cantidad; i++) respaldoOriginal[i] = cola[i];

        Ninio[] menores = new Ninio[cantidad];
        Ninio[] mayores = new Ninio[cantidad];
        int cMen = 0, cMay = 0;
        for (int i = 0; i < cantidad; i++) {
            if (cola[i].getEdad() < 5) menores[cMen++] = cola[i];
            else mayores[cMay++] = cola[i];
        }
        // pasar menores a dalsy
        Ninio[] realMenores = new Ninio[cMen];
        for (int i = 0; i < cMen; i++) realMenores[i] = menores[i];
        dalsy.recibirTransferencia(realMenores);

        // dejar en Aisha solo los mayores
        cola = new Ninio[200];
        cantidad = 0;
        for (int i = 0; i < cMay; i++) cola[cantidad++] = mayores[i];

        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (int i = 0; i < cMen; i++) System.out.println("- " + realMenores[i]);
        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        for (int i = 0; i < cMay; i++) System.out.println("- " + mayores[i]);
        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    // ==== Métodos útiles para el juego del teléfono descacharrado ====

    public void limpiarPizarrines() {
        for (int i = 0; i < cantidad; i++) {
            cola[i].limpiarPizarrin();
        }
    }

    // Primer niño copia el mensaje que escribe Aisha
    public void copiarMensajeAlPrimerNinio(String mensaje) {
        if (cantidad == 0) return;
        cola[0].copiarMensaje(mensaje);
    }

    // Leer mensaje del niño i (su pizarrín)
    public String leerMensajeDeNinio(int i) {
        if (i < 0 || i >= cantidad) return "";
        return cola[i].leerMensaje();
    }

    // Copiar mensaje del niño src al niño dst (aplica distorsiones en la copia de dst)
    public void copiarMensajeDeNinioA(int srcIndex, int dstIndex) {
        if (srcIndex < 0 || srcIndex >= cantidad) return;
        if (dstIndex < 0 || dstIndex >= cantidad) return;
        String recibido = cola[srcIndex].leerMensaje();
        cola[dstIndex].copiarMensaje(recibido);
    }

    private boolean esAisha() {
        return "Aisha".equalsIgnoreCase(nombre);
    }
}
