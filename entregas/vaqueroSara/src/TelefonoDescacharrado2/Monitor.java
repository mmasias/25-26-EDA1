package TelefonoDescacharrado2;

public class Monitor {
    private String nombre;
    private Cola cola;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.cola = new Cola();
    }

    public void agregarNiño(Niño n) {
        cola.encolar(n);
    }

    public int contarNiños() {
        return cola.contar();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }

    public Niño sacarNiño() {
        return cola.desencolar();
    }

    public void mostrarEstado() {
        System.out.println(nombre.toUpperCase() + ":");
        if (cola.estaVacia()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cola.contar());
            cola.mostrar();
        }
        System.out.println();
    }

    public void presentarTodos() {
        if (cola.estaVacia()) {
            System.out.println("Aisha: No tengo niños en la cola.");
            return;
        }
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        Niño aux = cola.getPrimero();
        while (aux != null) {
            aux.presentarse();
            aux = aux.getSiguiente();
        }
    }

    public void presentarMayoresDe(int edadMinima) {
        if (cola.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años:\n");
        Niño aux = cola.getPrimero();
        while (aux != null) {
            if (aux.getEdad() > edadMinima) aux.presentarse();
            aux = aux.getSiguiente();
        }
    }

    public void presentarPorInicial(char letra) {
        if (cola.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        letra = Character.toUpperCase(letra);
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        Niño aux = cola.getPrimero();
        while (aux != null) {
            if (Character.toUpperCase(aux.getNombre().charAt(0)) == letra)
                System.out.println("Hola, soy " + aux.getNombre());
            aux = aux.getSiguiente();
        }
    }

    public void presentarPrimeros(int n) {
        if (cola.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los primeros " + n + " niños:\n");
        Niño aux = cola.getPrimero();
        int cont = 0;
        while (aux != null && cont < n) {
            System.out.println("Hola, soy " + aux.getNombre());
            aux = aux.getSiguiente();
            cont++;
        }
    }

    public void presentarUltimos(int n) {
        if (cola.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        int total = cola.contar();
        int desde = Math.max(total - n, 0);
        int i = 0;
        System.out.println("Aisha pide que se presenten los últimos " + n + " niños:\n");
        Niño aux = cola.getPrimero();
        while (aux != null) {
            if (i >= desde) System.out.println("Hola, soy " + aux.getNombre());
            i++;
            aux = aux.getSiguiente();
        }
    }

    public void mostrarPromedioEdad() {
        if (cola.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double prom = cola.promedioEdad();
        System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", prom);
    }

    public int contarMayoresIgual(int edad) {
        int c = 0;
        Niño aux = cola.getPrimero();
        while (aux != null) {
            if (aux.getEdad() >= edad) c++;
            aux = aux.getSiguiente();
        }
        return c;
    }

    public void simularSeparacionMenores(int edad, Monitor dalsy) {
        if (cola.estaVacia()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }

        System.out.println("Separando niños para el juego de la rana...");
        System.out.println("Niños menores de " + edad + " años pasan a Dalsy:");

        Cola nuevaCola = new Cola();
        Niño aux = cola.getPrimero();
        while (aux != null) {
            Niño siguiente = aux.getSiguiente();
            if (aux.getEdad() < edad) {
                dalsy.agregarNiño(aux);
                System.out.println("- " + aux.getNombre() + " (" + aux.getEdad() + " años)");
            } else {
                nuevaCola.encolar(aux);
            }
            aux.setSiguiente(null);
            aux = siguiente;
        }
        this.cola = nuevaCola;

        System.out.println("\nNiños que se quedan con Aisha para jugar:");
        cola.mostrar();
        System.out.println("\nNOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public int transferirTodosAMonitor(Monitor destino) {
        int cont = 0;
        while (!cola.estaVacia()) {
            Niño n = cola.desencolar();
            destino.agregarNiño(n);
            cont++;
        }
        return cont;
    }
}
