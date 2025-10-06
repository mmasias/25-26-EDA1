```java
public class Ludoteca {
    private Lidia lidia;
    private Aisha aisha;
    private Dalsy dalsy;

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

    public void emergencia() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println("\nPROTOCOLO DE EMERGENCIA ACTIVADO");
        int total = dalsy.contarNiños() + aisha.contarNiños();
        dalsy.transferirTodosA(lidia);
        while (!aisha.getCola().isEmpty()) {
            lidia.recibirNiño(aisha.getCola().poll());
        }
        System.out.println(total + " niños transferidos");
        System.out.println("Lidia ahora tiene " + lidia.contarNiños() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        System.out.println("LIDIA:");
        lidia.mostrarCola();
        System.out.println("\nAISHA:");
        aisha.mostrarCola();
        System.out.println("\nDALSY:");
        dalsy.mostrarCola();
        System.out.println("========================================");
    }
}
```
