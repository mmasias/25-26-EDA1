public class Monitor {
    private final String nombre;
    private final Cola colaNiños;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
    }

    public void recibeNiño(Niño niño) {
        colaNiños.addNiño(niño);
    }

    public void entregarTodos(Monitor destino) {
        while (colaNiños.hayNiños()) {
            Niño niño = colaNiños.removeNiño();
            destino.recibeNiño(niño);
            System.out.println("- " + niño.getNombre());
        }
    }

    public int cantidadNiños() {
        return colaNiños.size();
    }

    public double edadPromedio() {
        if (colaNiños.size() == 0)
            return 0;
        double total = 0;
        for (int i = 0; i < colaNiños.size(); i++) {
            total += colaNiños.getNiño(i).getEdad();
        }
        return total / colaNiños.size();
    }

    public int contarMayoresDe5() {
        int cont = 0;
        for (int i = 0; i < colaNiños.size(); i++) {
            if (colaNiños.getNiño(i).getEdad() >= 5)
                cont++;
        }
        return cont;
    }

    public void presentacionGeneral() {
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
        }
    }

    public void presentacionMayores(int edadMinima) {
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años:");
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (n.getEdad() > edadMinima)
                System.out
                        .println(n.getNombre() + ": Hola, soy " + n.getNombre() + " y tengo " + n.getEdad() + " años");
        }
    }

    public void presentacionPorInicial(char letra) {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            if (Character.toUpperCase(n.getNombre().charAt(0)) == letra)
                System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre());
        }
    }

    public void presentacionPrimeros(int cantidad) {
        System.out.println("Aisha pide que se presenten los primeros " + cantidad + " niños:");
        for (int i = 0; i < Math.min(cantidad, colaNiños.size()); i++) {
            Niño n = colaNiños.getNiño(i);
            System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre());
        }
    }

    public void presentacionUltimos(int cantidad) {
        System.out.println("Aisha pide que se presenten los últimos " + cantidad + " niños:");
        int total = colaNiños.size();
        for (int i = Math.max(0, total - cantidad); i < total; i++) {
            Niño n = colaNiños.getNiño(i);
            System.out.println(n.getNombre() + ": Hola, soy " + n.getNombre());
        }
    }

    public void separarMenoresDe5(Monitor destino) {
        for (int i = 0; i < colaNiños.size();) {
            Niño n = colaNiños.getNiño(i);
            if (n.getEdad() < 5) {
                destino.recibeNiño(colaNiños.removeNiñoPorPosicion(i));
                System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
            } else {
                i++;
            }
        }
    }

    public void mostrarCola() {
        System.out.println(nombre.toUpperCase() + ":");
        if (!colaNiños.hayNiños()) {
            System.out.println("  Cola vacía\n");
            return;
        }
        System.out.println("  Niños en cola: " + colaNiños.size());
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño n = colaNiños.getNiño(i);
            System.out.println("  - " + n.getNombre() + " (" + n.getEdad() + " años)");
        }
        System.out.println();
    }
}
