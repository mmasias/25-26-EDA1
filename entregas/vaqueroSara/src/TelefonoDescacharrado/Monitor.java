public class Monitor {
    private String nombre;
    private Cola colaNinos;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNinos = new Cola();
    }

    public void recibirNino(Nino n) {
        colaNinos.add(n);
    }

    public boolean tieneNinos() {
        return !colaNinos.isEmpty();
    }

    public void limpiarPizarras() {
        for (int i = 0; i < colaNinos.size(); i++) {
            colaNinos.get(i).borrarPizarrin();
        }
        System.out.println(nombre + " limpia pizarras de los niños.");
    }

    public Cola getCola() {
        return colaNinos;
    }

    public String getNombre() {
        return nombre;
    }
}
