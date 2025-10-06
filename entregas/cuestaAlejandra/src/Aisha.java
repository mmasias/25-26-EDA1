class Aisha {
    List<Nino> ninos;

    public Aisha(List<Nino> ninos) {
        this.ninos = ninos;
    }

    public void pedirPresentaciones() {
        System.out.println("\nAisha: ¡Hola chicos! Vamos a presentarnos:");
        ninos.forEach(Nino::presentarse);
    }

    public void presentarPorEdad(int edad) {
        System.out.println("\nAisha: Que se presenten los niños de " + edad + " años:");
        ninos.stream().filter(n -> n.edad == edad).forEach(Nino::presentarse);
    }

    public void presentarPorLetra(char letra) {
        System.out.println("\nAisha: Que se presenten los que empiezan por '" + letra + "':");
        ninos.stream().filter(n -> n.nombre.charAt(0) == letra).forEach(Nino::presentarse);
    }

    public void mostrarCincoPrimeros() {
        System.out.println("\nAisha: Los cinco primeros son:");
        ninos.stream().limit(5).forEach(System.out::println);
    }

    public void mostrarCincoUltimos() {
        System.out.println("\nAisha: Los cinco últimos son:");
        ninos.stream().skip(ninos.size() - 5).forEach(System.out::println);
    }
