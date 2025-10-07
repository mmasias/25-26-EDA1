class JuegoRana {
    public void jugar(List<Nino> ninos) {
        System.out.println("\n🎯 Juego de la rana con los mayores de 5 años:");
        ninos.forEach(n -> System.out.println(n.nombre + " lanza su ficha a la rana..."));
    }
}