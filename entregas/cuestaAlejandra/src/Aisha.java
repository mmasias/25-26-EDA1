class Aisha {
    List<Nino> ninos;

    public Aisha(List<Nino> ninos) {
        this.ninos = ninos;
    }

    public void pedirPresentaciones() {
        System.out.println("\nAisha: ¡Hola chicos! Vamos a presentarnos:");
        ninos.forEach(Nino::presentarse);
    }