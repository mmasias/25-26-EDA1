class Monitor {
    String nombre;
    private Cola colaNiños;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
    }

    public void recibeNiño(Niño n) { colaNiños.addNiño(n); }
    public boolean tieneNiños() { return colaNiños.hayNiños(); }
    public boolean puedeJugar() { return colaNiños.size() >= 5; }
    public int numeroNiños() { return colaNiños.size(); }
    public Niño[] getTodosNiños() { return colaNiños.getTodos(); }

    public void mostrarListaNiños() {
        System.out.print("> " + nombre + " --> ");
        for (Niño n : getTodosNiños()) System.out.print(n.getNombre() + " / ");
        System.out.println();
    }

    public void transferirNiños(Monitor destino) {
        while (colaNiños.hayNiños()) destino.recibeNiño(colaNiños.removeNiño());
    }

    public void presentacionesGenerales() { for (Niño n : getTodosNiños()) n.presentarse(); }
    public void presentacionesEdadMin(int edad) { for (Niño n : getTodosNiños()) if (n.getEdad() >= edad) n.presentarse(); }
    public void presentacionesInicial(char letra) { for (Niño n : getTodosNiños()) if (Character.toUpperCase(n.getNombre().charAt(0)) == Character.toUpperCase(letra)) n.presentarseNombre(); }
    public void primerosCinco() { Niño[] todos = getTodosNiños(); for (int i = 0; i < Math.min(5, todos.length); i++) todos[i].presentarseNombre(); }
    public void ultimosCinco() { Niño[] todos = getTodosNiños(); for (int i = Math.max(0, todos.length - 5); i < todos.length; i++) todos[i].presentarseNombre(); }

    public double promedioEdad() {
        Niño[] todos = getTodosNiños();
        if (todos.length == 0) return 0;
        double suma = 0;
        for (Niño n : todos) suma += n.getEdad();
        return suma / todos.length;
    }
}
