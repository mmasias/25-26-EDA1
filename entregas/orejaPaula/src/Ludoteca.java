public class Ludoteca {
    private Lidia lidia;
    private Aisha aisha;
    private Dalsy dalsy;
}
public Ludoteca() {
    lidia = new Lidia();
    aisha = new Aisha();
    dalsy = new Dalsy();
}
public Lidia getLidia() { return lidia; }
public Aisha getAisha() { return aisha; }
public Dalsy getDalsy() { return dalsy; }

public void conteoAsistencia() {
    int total = lidia.contarNiños() + aisha.contarNiños() + dalsy.contarNiños();
    System.out.println("CONTEO DE ASISTENCIA:");
    System.out.println("Lidia tiene " + lidia.contarNiños() + " niños en cola");
    System.out.println("Aisha tiene " + aisha.contarNiños() + " niños en cola");
    System.out.println("Dalsy tiene " + dalsy.contarNiños() + " niños en cola");
    System.out.println("Total: " + total + " niños");
}
