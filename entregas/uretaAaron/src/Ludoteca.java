import java.util.*;

public class Ludoteca {
    private Monitor lydia = new Monitor("Lydia");
    private Monitor aisha = new Monitor("Aisha");
    private Monitor dalsy = new Monitor("Dalsy");
    private List<Nino> aishaOriginalSnapshot = null;

    public void llegadaNino(Nino n) {
        System.out.printf("Llega %s (%d años)%n", n.getNombre(), n.getEdad());
        lydia.recibir(n);
        System.out.printf("%s pasa a la cola de Lydia%n", n.getNombre());
    }

    public void intentoInicioJuegoDesdeLydia() {
        if (lydia.contar() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            List<Nino> transferred = lydia.getLista();
            lydia.transferirTodoAMonitor(aisha);
            for (Nino n : transferred) System.out.println("- " + n.toString());
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (Nino n : aisha.getLista())
            System.out.printf("%s: %s%n", n.getNombre(), n.presentacionCompleta());
    }

    public void presentacionesPorEdadMinima(int edadMinima) {
        System.out.printf("Aisha pide que se presenten los mayores de %d años:%n%n", edadMinima);
        List<Nino> list = aisha.filtrarPorEdadMayorQue(edadMinima);
        for (Nino n : list)
            System.out.printf("%s: Hola, soy %s y tengo %d años%n", n.getNombre(), n.getNombre(), n.getEdad());
    }

    public void presentacionesPorInicial(char letra) {
        System.out.printf("Aisha pide que se presenten los niños cuyo nombre empieza con '%c':%n%n", letra);
        List<Nino> list = aisha.filtrarPorInicial(letra);
        for (Nino n : list)
            System.out.printf("%s: Hola, soy %s%n", n.getNombre(), n.getNombre());
    }

    public void primerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        List<Nino> list = aisha.primeros(5);
        int i = 1;
        for (Nino n : list)
            System.out.printf("%d: %s%n", i++, n.presentacionNombre());
    }

    public void ultimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        List<Nino> list = aisha.ultimos(5);
        for (Nino n : list)
            System.out.printf("- %s%n", n.presentacionNombre());
    }

    public void conteoAsistencia() {
        int x = lydia.contar();
        int y = aisha.contar();
        int z = dalsy.contar();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.printf("Lydia tiene %d niños en cola%n", x);
        System.out.printf("Aisha tiene %d niños en cola%n", y);
        System.out.printf("Dalsy tiene %d niños en cola%n", z);
        System.out.printf("Total: %d niños%n", x + y + z);
    }

    public void edadPromedioAisha() {
        if (aisha.contar() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            double avg = aisha.edadPromedio();
            System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", avg);
        }
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.contar();
        int mayores = 0;
        for (Nino n : aisha.getLista()) if (n.getEdad() >= 5) mayores++;
        System.out.printf("Total de niños: %d%n", total);
        System.out.printf("Niños de 5 años o más: %d%n", mayores);
        if (total > 0 && mayores * 2 > total) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarMenoresParaRana() {
        System.out.println("Separando niños para el juego de la rana...");
        List<Nino> actual = aisha.getLista();
        aishaOriginalSnapshot = new ArrayList<>(actual);
        List<Nino> menores = new ArrayList<>();
        List<Nino> mayores = new ArrayList<>();
        for (Nino n : actual) {
            if (n.getEdad() < 5) menores.add(n);
            else mayores.add(n);
        }
        dalsy.recibirTodos(menores);
        aisha.vaciar();
        aisha.recibirTodos(mayores);
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (Nino n : menores) System.out.printf("- %s (%d años)%n", n.getNombre(), n.getEdad());
        System.out.println();
        System.out.println("Niños que se quedan con Aisha para jugar:");
        for (Nino n : mayores) System.out.printf("- %s (%d años)%n", n.getNombre(), n.getEdad());
        System.out.println();
        System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original (snapshot guardado).");
    }

    public void alarmaContraIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        int transferidos = 0;
        int desdeDalsy = dalsy.contar();
        dalsy.transferirTodoAMonitor(lydia);
        transferidos += desdeDalsy;
        int desdeAisha = aisha.contar();
        aisha.transferirTodoAMonitor(lydia);
        transferidos += desdeAisha;
        System.out.printf("%d niños transferidos%n%n", transferidos);
        System.out.printf("Lydia ahora tiene %d niños listos para evacuar en orden%n", lydia.contar());
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        printMonitorState(lydia);
        printMonitorState(aisha);
        printMonitorState(dalsy);
        System.out.println("========================================");
    }

    private void printMonitorState(Monitor m) {
        System.out.println(m.getNombre().toUpperCase() + ":");
        if (m.contar() == 0) {
            System.out.println("  Cola vacía");
        } else {
            System.out.printf("  Niños en cola: %d%n", m.contar());
            for (Nino n : m.getLista())
                System.out.printf("  - %s (%d años)%n", n.getNombre(), n.getEdad());
        }
        System.out.println();
    }

    public int contarAisha() {
        return aisha.contar();
    }

    public int contarLydia() {
        return lydia.contar();
    }

    public int contarDalsy() {
        return dalsy.contar();
    }
}
import java.util.*;

public class Ludoteca {
    private Monitor lydia = new Monitor("Lydia");
    private Monitor aisha = new Monitor("Aisha");
    private Monitor dalsy = new Monitor("Dalsy");
    private List<Nino> aishaOriginalSnapshot = null;

    public void llegadaNino(Nino n) {
        System.out.printf("Llega %s (%d años)%n", n.getNombre(), n.getEdad());
        lydia.recibir(n);
        System.out.printf("%s pasa a la cola de Lydia%n", n.getNombre());
    }

    public void intentoInicioJuegoDesdeLydia() {
        if (lydia.contar() >= 5) {
            System.out.println("Lydia transfiere sus niños a Aisha");
            List<Nino> transferred = lydia.getLista();
            lydia.transferirTodoAMonitor(aisha);
            for (Nino n : transferred) System.out.println("- " + n.toString());
        } else {
            System.out.println("No hay suficientes niños para iniciar el juego");
            System.out.println("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionesGenerales() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (Nino n : aisha.getLista())
            System.out.printf("%s: %s%n", n.getNombre(), n.presentacionCompleta());
    }

    public void presentacionesPorEdadMinima(int edadMinima) {
        System.out.printf("Aisha pide que se presenten los mayores de %d años:%n%n", edadMinima);
        List<Nino> list = aisha.filtrarPorEdadMayorQue(edadMinima);
        for (Nino n : list)
            System.out.printf("%s: Hola, soy %s y tengo %d años%n", n.getNombre(), n.getNombre(), n.getEdad());
    }

    public void presentacionesPorInicial(char letra) {
        System.out.printf("Aisha pide que se presenten los niños cuyo nombre empieza con '%c':%n%n", letra);
        List<Nino> list = aisha.filtrarPorInicial(letra);
        for (Nino n : list)
            System.out.printf("%s: Hola, soy %s%n", n.getNombre(), n.getNombre());
    }

    public void primerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:");
        List<Nino> list = aisha.primeros(5);
        int i = 1;
        for (Nino n : list)
            System.out.printf("%d: %s%n", i++, n.presentacionNombre());
    }

    public void ultimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:");
        List<Nino> list = aisha.ultimos(5);
        for (Nino n : list)
            System.out.printf("- %s%n", n.presentacionNombre());
    }

    public void conteoAsistencia() {
        int x = lydia.contar();
        int y = aisha.contar();
        int z = dalsy.contar();
        System.out.println("CONTEO DE ASISTENCIA:");
        System.out.printf("Lydia tiene %d niños en cola%n", x);
        System.out.printf("Aisha tiene %d niños en cola%n", y);
        System.out.printf("Dalsy tiene %d niños en cola%n", z);
        System.out.printf("Total: %d niños%n", x + y + z);
    }

    public void edadPromedioAisha() {
        if (aisha.contar() == 0) {
            System.out.println("No hay niños en la cola de Aisha");
        } else {
            double avg = aisha.edadPromedio();
            System.out.printf("Edad promedio de los niños en la cola de Aisha: %.1f años%n", avg);
        }
    }

    public void intentoJuegoRana() {
        System.out.println("Verificando condiciones para el juego de la rana...");
        int total = aisha.contar();
        int mayores = 0;
        for (Nino n : aisha.getLista()) if (n.getEdad() >= 5) mayores++;
        System.out.printf("Total de niños: %d%n", total);
        System.out.printf("Niños de 5 años o más: %d%n", mayores);
        if (total > 0 && mayores * 2 > total) {
            System.out.println("¡Más de la mitad cumplen la condición!");
            System.out.println("¡Pueden jugar al juego de la rana!");
        } else {
            System.out.println("No hay suficientes niños mayores de 5 años");
            System.out.println("No pueden jugar todavía");
        }
    }

    public void separarMenoresParaRana() {
        System.out.println("Separando niños para el juego de la rana...");
        List<Nino> actual = aisha.getLista();
        aishaOriginalSnapshot = new ArrayList<>(actual);
        List<Nino> menores = new ArrayList<>();
        List<Nino> mayores = new ArrayList<>();
        for (Nino n : actual) {
            if (n.getEdad() < 5) menores.add(n);
            else mayores.add(n);
        }
        dalsy.recibirTodos(menores);
        aisha.vaciar();
        aisha.recibirTodos(mayores);
        System.out.println("Niños menores de 5 años pasan a Dalsy:");
        for (Nino n : menores) System.out.printf("- %s (%d años)%n", n.getNombre(), n.getEdad());
        System.out.println();
        System.out.println("Niños que se quedan con Aisha para jugar:");
        for (Nino n : mayores) System.out.printf("- %s (%d años)%n", n.getNombre(), n.getEdad());
        System.out.println();
        System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original (snapshot guardado).");
    }

    public void alarmaContraIncendios() {
        System.out.println("¡ALARMA CONTRA INCENDIOS!");
        System.out.println();
        System.out.println("PROTOCOLO DE EMERGENCIA ACTIVADO");
        int transferidos = 0;
        int desdeDalsy = dalsy.contar();
        dalsy.transferirTodoAMonitor(lydia);
        transferidos += desdeDalsy;
        int desdeAisha = aisha.contar();
        aisha.transferirTodoAMonitor(lydia);
        transferidos += desdeAisha;
        System.out.printf("%d niños transferidos%n%n", transferidos);
        System.out.printf("Lydia ahora tiene %d niños listos para evacuar en orden%n", lydia.contar());
    }

    public void mostrarEstado() {
        System.out.println("========================================");
        System.out.println("        ESTADO ACTUAL");
        System.out.println("========================================");
        printMonitorState(lydia);
        printMonitorState(aisha);
        printMonitorState(dalsy);
        System.out.println("========================================");
    }

    private void printMonitorState(Monitor m) {
        System.out.println(m.getNombre().toUpperCase() + ":");
        if (m.contar() == 0) {
            System.out.println("  Cola vacía");
        } else {
            System.out.printf("  Niños en cola: %d%n", m.contar());
            for (Nino n : m.getLista())
                System.out.printf("  - %s (%d años)%n", n.getNombre(), n.getEdad());
        }
        System.out.println();
    }

    public int contarAisha() {
        return aisha.contar();
    }

    public int contarLydia() {
        return lydia.contar();
    }

    public int contarDalsy() {
        return dalsy.contar();
    }
}
