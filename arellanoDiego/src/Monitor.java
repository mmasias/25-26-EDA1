public class Monitor {
    private final String nombre;
    private final Cola cola = new Cola();

    public Monitor(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
    public Cola getCola() { return cola; }

    public void recibeNiño(Niño n) { cola.add(n); }
    public boolean tieneNiños() { return !cola.estaVacia(); }
    public int cuantos() { return cola.tamaño(); }

    public void entregarNiños(Monitor destino) {
        while (!cola.estaVacia()) {
            Niño n = cola.remove();
            destino.recibirNiñoConPizarrin(n, new Pizarra());
        }
    }

    public void recibirNiñoConPizarrin(Niño n, Pizarra p) {
        n.recibirPizarrin(p);
        recibeNiño(n);
    }
}
