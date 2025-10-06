import java.util.*;
import java.util.function.Predicate;

public class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;

    private List<Nino> backupAishaBeforeSeparation = null;

    public Ludoteca() {
        this.lydia = new Monitor("Lydia");
        this.aisha = new Monitor("Aisha");
        this.dalsy = new Monitor("Dalsy");
    }

    public void recibirNinoEnLydia(Nino n) {
        System.out.println("Llega " + n.getNombre() + " (" + n.getEdad() + " años)");
        System.out.println(n.getNombre() + " pasa a la cola de Lydia");
        lydia.recibeNino(n);
    }

    public void simularIntentoInicioJuegoDesdeLydia() {
        if (lydia.getCantidad() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            List<Nino> movidos = lydia.transferAllTo(aisha);
            for (Nino n : movidos) {
                System.out.println("- " + n.toString());
            }
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    public void aishaPresentacionGeneral() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        List<Nino> lista = aisha.getNinosAsList();
        for (Nino n : lista) {
            System.out.println("[" + n.getNombre() + "]: " + n.presentacionCompleta());
        }
        if (lista.isEmpty()) {
            System.out.println("(No hay niños en la cola de Aisha)");
        }
    }

    public void aishaPresentacionMayoresQue(int edadMin) {
        if (!aisha.tieneNiños()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los mayores de " + edadMin + " años:");
        for (Nino n : aisha.getNinosAsList()) {
            if (n.getEdad() > edadMin) { 
                System.out.println("[" + n.getNombre() + "]: " + n.presentacionCompleta());
            }
        }
    }

    public void aishaPresentacionPorInicial(char letra) {
        if (!aisha.tieneNiños()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':");
        char lower = Character.toLowerCase(letra);
        for (Nino n : aisha.getNinosAsList()) {
            if (Character.toLowerCase(n.getNombre().charAt(0)) == lower) {
                System.out.println("[" + n.getNombre() + "]: " + n.presentacionNombre());
            }
        }
    }

    public void aishaPresentacionPrimerosCinco() {
        if (!aisha.tieneNiños()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        List<Nino> primeros = aisha.getPrimerosN(5);
        for (Nino n : primeros) {
            System.out.println("[" + n.getNombre() + "]: " + n.presentacionNombre());
        }
    }

    public void aishaPresentacionUltimosCinco() {
        if (!aisha.tieneNiños()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        List<Nino> ultimos = aisha.getUltimosN(5);
        for (Nino n : ultimos) {
            System.out.println("[" + n.getNombre() + "]: " + n.presentacionNombre());
        }
    }

    public void conteoAsistencia() {
        int x = lydia.getCantidad();
        int y = aisha.getCantidad();
        int z = dalsy.getCantidad();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.println("Lydia tiene " + x + " niños en cola");
        System.out.println("Aisha tiene " + y + " niños en cola");
        System.out.println("Dalsy tiene " + z + " niños en cola");
        System.out.println("Total: " + (x + y + z) + " niños");
    }

    public void edadPromedioAisha() {
        if (!aisha.tieneNiños()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }
        double prom = aisha.edadPromedio();
        System.out.println("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", prom) + " años");
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.getCantidad();
        int mayores5 = aisha.contarEdadMayorOIgual(5);
        System.out.println("Total de niños: " + total);
        System.out.println("Niños de 5 años o más: " + mayores5);
        if (total > 0 && mayores5 > total / 2.0) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarMenoresDe5ADalsy() {
        if (!aisha.tieneNiños()) {
            System.out.println("No hay niños en la cola de Aisha");
            return;
        }

        System.out.println("Separando niños para el juego de la rana...");
        backupAishaBeforeSeparation = aisha.getNinosAsList();

        List<Nino> movidos = aisha.transferIf(n -> n.getEdad() < 5, dalsy);

        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        if (movidos.isEmpty()) {
            System.out.println("(Ninguno)");
        } else {
            for (Nino n : movidos) {
                System.out.println("- " + n.toString());
            }
        }

        System.out.println();
        System.out.println("Niños que se quedan con Aisha para jugar:");
        List<Nino> quedan = aisha.getNinosAsList();
        if (quedan.isEmpty()) {
            System.out.println("(Ninguno)");
        } else {
            for (Nino n : quedan) {
                System.out.println("- " + n.toString());
            }
        }

        System.out.println();
        System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");
    }

    public void alarmaIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        System.out.println();

        System.out.println("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        List<Nino> movidosDalsy = dalsy.transferAllTo(lydia);

        System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
        List<Nino> movidosAisha = aisha.transferAllTo(lydia);

        int totalTransferidos = movidosDalsy.size() + movidosAisha.size();
        System.out.println(totalTransferidos + " niños transferidos");
        System.out.println();
        System.out.println("Lydia ahora tiene " + lydia.getCantidad() + " niños listos para evacuar en orden");
    }

    public void mostrarEstadoActual() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        mostrarMonitorYLista(lydia);
        mostrarMonitorYLista(aisha);
        mostrarMonitorYLista(dalsy);
        System.out.println("========================================");
    }

    private void mostrarMonitorYLista(Monitor m) {
        System.out.println();
        System.out.println(m.getNombre().toUpperCase() + ":");
        if (m.tieneNiños()) {
            System.out.println("  Niños en cola: " + m.getCantidad());
            for (Nino n : m.getNinosAsList()) {
                System.out.println("  - " + n.toString());
            }
        } else {
            System.out.println("  Cola vacía");
        }
    }

    public Monitor getLydia() { return lydia; }
    public Monitor getAisha() { return aisha; }
    public Monitor getDalsy() { return dalsy; }
}
