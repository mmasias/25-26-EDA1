class Ludoteca {
    private final Monitor lydia = new Monitor("Lydia");
    private final Monitor aisha = new Monitor("Aisha");
    private final Monitor dalsy = new Monitor("Dalsy");

    public Monitor getLydia() { return lydia; }
    public Monitor getAisha() { return aisha; }
    public Monitor getDalsy() { return dalsy; }

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
