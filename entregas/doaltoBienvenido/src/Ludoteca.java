class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public Monitor getLydia() { 
        return lydia; 
    }
    public Monitor getAisha() { 
        return aisha; 
    }
    public Monitor getDalsy() { 
        return dalsy; 
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");

        System.out.println("LYDIA:");
        lydia.mostrarNiños();

        System.out.println("AISHA:");
        aisha.mostrarNiños();

        System.out.println("DALSY:");
        dalsy.mostrarNiños();

        System.out.println("========================================");
    }

    public int totalNiños() {
        return lydia.getCantidad() + aisha.getCantidad() + dalsy.getCantidad();
    }
}
