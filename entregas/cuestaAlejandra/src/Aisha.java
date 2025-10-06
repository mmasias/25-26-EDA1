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

    public List<Nino> separarMenores() {
        return ninos.stream().filter(n -> n.edad < 5).collect(Collectors.toList());
    }

    public List<Nino> mayoresParaJugar() {
        return ninos.stream().filter(n -> n.edad >= 5).collect(Collectors.toList());
    }

    public void evacuar(List<Nino> ninosDalsy, List<Nino> ninosAisha, Lidia lidia) {
    System.out.println("\n🔔 Aisha: ¡Atención chicos, suena la alarma de incendio!");
    System.out.println("Aisha: Tranquilos, es solo un ejercicio. Dalsy y yo os sacaremos fuera.");
    
    System.out.println("\nDalsy: Yo voy primero con los más pequeños. Vamos de la mano:");
    for (Nino n : ninosDalsy) {
        System.out.println("👉 " + n.nombre + " sale con Dalsy.");
    }

    System.out.println("\nAisha: Ahora salimos los mayores, en fila y sin correr:");
    for (Nino n : ninosAisha) {
        System.out.println("👉 " + n.nombre + " sale con Aisha.");
    }

    // Todos fuera, Lidia pasa lista
    List<Nino> todos = new ArrayList<>();
    todos.addAll(ninosDalsy);
    todos.addAll(ninosAisha);

    System.out.println("\nLidia: Muy bien, ya estáis todos fuera.");
    lidia.pasarListaEficiente(todos);

    System.out.println("\n🚨 Lidia: Voy a revisar que no falte nadie...");
    lidia.pasarListaFuera(todos);

    System.out.println("\n✅ Aisha: ¡Perfecto! Era solo un simulacro. Podemos volver a jugar.");
}

