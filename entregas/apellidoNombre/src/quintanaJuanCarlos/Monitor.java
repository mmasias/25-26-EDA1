public class Monitor {
    private final String nombre;
    private final Cola cola;

    public Monitor(String nombre, int capacidadCola) {
        this.nombre = nombre;
        this.cola = new Cola(capacidadCola);
    }

    public String getNombre() { return nombre; }
    public int tamanoCola() { return cola.tamano(); }
    public boolean estaVacia() { return cola.vacia(); }

    public boolean recibir(Nino n) {
        return cola.encolar(n);
    }

    public int transferirTodosA(Monitor destino, StringBuilder listadoTransferidos) {
        int count = 0;
        Nino x;
        while ((x = cola.desencolar()) != null) {
            if (!destino.recibir(x)) {
                cola.encolar(x); 
                break;
            }
            if (listadoTransferidos != null) {
                listadoTransferidos.append("- ").append(x.getNombre())
                        .append(" (").append(x.getEdad()).append(" años)\n");
            }
            count++;
        }
        return count;
    }

    public void listarTodosDetallado(String titulo) {
        System.out.println(titulo);
        if (cola.vacia()) {
            System.out.println("  Cola vacía");
            return;
        }
        System.out.println("  Niños en cola: " + cola.tamano());
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null) {
                System.out.println("  - " + n.getNombre() + " (" + n.getEdad() + " años)");
            }
        }
    }

    public void presentacionesGenerales() {
        if (cola.vacia()) {
            System.out.println("No hay niños en la cola de " + nombre);
            return;
        }
        System.out.println(nombre + ": Hola, soy " + nombre + ", monitora de esta ludoteca");
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null) System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
        }
    }

    public void presentacionesPorEdadMinima(int edadMin) {
        System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:");
        boolean alguno = false;
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null && n.getEdad() >= edadMin) {
                System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
                alguno = true;
            }
        }
        if (!alguno) System.out.println("(Nadie cumple la condición)");
    }

    public void presentacionesPorInicial(char letra) {
        char target = Character.toUpperCase(letra);
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + target + "':");
        boolean alguno = false;
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null && !n.getNombre().isEmpty()) {
                char c = Character.toUpperCase(n.getNombre().charAt(0));
                if (c == target) {
                    System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
                    alguno = true;
                }
            }
        }
        if (!alguno) System.out.println("(Nadie cumple la condición)");
    }

    public void primerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        int limite = Math.min(5, cola.tamano());
        if (limite == 0) {
            System.out.println("(No hay niños en la cola)");
            return;
        }
        for (int i = 0; i < limite; i++) {
            Nino n = cola.get(i);
            System.out.println("[" + (i + 1) + "]: Hola, soy " + n.getNombre());
        }
    }

    public void ultimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        int t = cola.tamano();
        if (t == 0) {
            System.out.println("(No hay niños en la cola)");
            return;
        }
        int inicio = Math.max(0, t - 5);
        for (int i = inicio; i < t; i++) {
            Nino n = cola.get(i);
            if (n != null) {
                String etiqueta = (i == t - 1) ? "Último" : ("-" + (t - i));
                System.out.println("[" + etiqueta + "]: Hola, soy " + n.getNombre());
            }
        }
    }

    public double edadPromedio() {
        if (cola.vacia()) return -1.0;
        int suma = 0;
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null) suma += n.getEdad();
        }
        return ((double) suma) / cola.tamano();
    }

    public int contarMayoresOIgualA(int edadMin) {
        int c = 0;
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null && n.getEdad() >= edadMin) c++;
        }
        return c;
    }

    public Nino[] snapshot() {
        Nino[] copia = new Nino[cola.tamano()];
        for (int i = 0; i < cola.tamano(); i++) copia[i] = cola.get(i);
        return copia;
    }

    public void restaurarDesde(Nino[] copia) {
        cola.vaciar();
        for (int i = 0; i < copia.length; i++) {
            if (copia[i] != null) cola.encolar(copia[i]);
        }
    }
}
