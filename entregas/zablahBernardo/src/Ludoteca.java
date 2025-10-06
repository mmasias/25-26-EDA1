import utils.Console;

class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
    }

    public void recibirNiñoEnLydia(Niño niñoNuevo) {
        lydia.recibirNiño(niñoNuevo);
        transferirNiñosLydiaAAisha();
    }

    private void transferirNiñosLydiaAAisha() {
        Niño[] todosNiñosLydia = lydia.obtenerTodosNiños();
        for (Niño niñoActual : todosNiñosLydia) {
            aisha.recibirNiño(niñoActual);
        }
        lydia.vaciarCola();
    }

    public Monitor getAisha() {
        return aisha;
    }

    public Monitor getLydia() {
        return lydia;
    }

    public Monitor getDalsy() {
        return dalsy;
    }

    public void mostrarEstado(Console console) {
        console.writeln("===== ESTADO DE LA LUDOTECA =====");
        lydia.mostrarListaNiños(console);
        aisha.mostrarListaNiños(console);
        dalsy.mostrarListaNiños(console);
        console.writeln("=================================");
    }
}