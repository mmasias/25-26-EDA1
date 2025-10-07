class Lidia {
    public List<Nino> recibirNinos() {
        List<Nino> ninos = Arrays.asList(
                new Nino("Maria", 3), new Nino("Luis", 6), new Nino("Aitor", 8), new Nino("Alejandra", 5),
                new Nino("Carmen", 4), new Nino("Daniel", 7), new Nino("Lucía", 9), new Nino("Pablo", 2),
                new Nino("Ismael", 10), new Nino("Marta", 5), new Nino("Alba", 6), new Nino("Iván", 4),
                new Nino("Sara", 8), new Nino("Raúl", 3), new Nino("Nora", 7), new Nino("Mario", 9),
                new Nino("Alex", 5), new Nino("Eva", 6), new Nino("Sergio", 2), new Nino("Paula", 10)
        );

         System.out.println("Lidia: ¡Bienvenidos a la ludoteca! Os voy a pasar con Aisha.");
        return ninos;
    }

   
    public void pasarListaEficiente(List<Nino> ninos) {
        System.out.println("\nLidia: Vamos a pasar lista rápido, todos juntos de la mano.");
        System.out.print("Lidia: Veo a ");
        for (int i = 0; i < ninos.size(); i++) {
            System.out.print(ninos.get(i).nombre);
            if (i < ninos.size() - 1) System.out.print(", ");
        }
        System.out.println(". ¡Muy bien, todos estáis aquí!");
    }

    public void pasarListaFuera(List<Nino> ninos) {
        System.out.println("\n🚨 Lidia: Ya estamos fuera. Voy a comprobar otra vez que estén todos:");
        for (Nino n : ninos) {
            System.out.println("✅ " + n.nombre + " está a salvo.");
        }
        System.out.println("Lidia: ¡Perfecto! Nadie falta. Era solo un simulacro, podemos volver dentro.");
    }
}