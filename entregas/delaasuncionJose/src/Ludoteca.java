class Ludoteca {
    private Monitora lydia = new Monitora("Lydia");
    private Monitora aisha = new Monitora("Aisha");
    private Monitora dalsy = new Monitora("Dalsy");
    private long contadorLlegada = 0;
    private Nino[] ultimaSeparacion = new Nino[0];

    public Monitora getLydia() { return lydia; }
    public Monitora getAisha() { return aisha; }
    public Monitora getDalsy() { return dalsy; }

    public Nino simularLlegada(String nombre, int edad) {
        Nino n = new Nino(nombre, edad, ++contadorLlegada);
        lydia.recibirNino(n);
        return n;
    }

    public Nino[] intentarInicioJuego(int minimo) {
        if (lydia.contar() < minimo) return new Nino[0];
        return lydia.transferirTodosA(aisha);
    }

    public String presentacionesGenerales() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (Nino n : aisha.listar()) {
            sb.append(n.getNombre()).append(": Hola, soy ").append(n.getNombre())
              .append(" y tengo ").append(n.getEdad()).append(" años\n");
        }
        return sb.toString();
    }

    public String presentacionesPorEdad(int edadMin) {
        StringBuilder sb = new StringBuilder();
        sb.append("Aisha pide que se presenten los mayores de ").append(edadMin).append(" años:\n");
        for (Nino n : aisha.listarMayoresQue(edadMin)) {
            sb.append(n.getNombre()).append(": Hola, soy ").append(n.getNombre())
              .append(" y tengo ").append(n.getEdad()).append(" años\n");
        }
        return sb.toString();
    }

    public String presentacionesPorInicial(char inicial) {
        StringBuilder sb = new StringBuilder();
        sb.append("Aisha pide que se presenten los niños cuyo nombre empieza con '").append(inicial).append("':\n");
        for (Nino n : aisha.listarPorInicial(inicial)) {
            sb.append(n.getNombre()).append(": Hola, soy ").append(n.getNombre()).append("\n");
        }
        return sb.toString();
    }

    public String primerosCinco() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aisha pide que se presenten los primeros 5 niños:\n");
        Nino[] lista = aisha.primerosN(5);
        for (int i = 0; i < lista.length; i++) sb.append("[Niño").append(i+1).append("]: Hola, soy ").append(lista[i].getNombre()).append("\n");
        return sb.toString();
    }

    public String ultimosCinco() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aisha pide que se presenten los últimos 5 niños:\n");
        Nino[] lista = aisha.ultimosN(5);
        for (int i = 0; i < lista.length; i++) sb.append("[Niño-").append(lista.length - i).append("]: Hola, soy ").append(lista[i].getNombre()).append("\n");
        return sb.toString();
    }

    public String conteoAsistencia() {
        StringBuilder sb = new StringBuilder();
        sb.append("CONTEO DE ASISTENCIA:\n");
        int x = lydia.contar();
        int y = aisha.contar();
        int z = dalsy.contar();
        sb.append("Lydia tiene ").append(x).append(" niños en cola\n");
        sb.append("Aisha tiene ").append(y).append(" niños en cola\n");
        sb.append("Dalsy tiene ").append(z).append(" niños en cola\n");
        sb.append("Total: ").append(x+y+z).append(" niños\n");
        return sb.toString();
    }

    public String edadPromedioAisha() {
        if (aisha.contar() == 0) return "No hay niños en la cola de Aisha";
        double prom = aisha.edadPromedio();
        return "Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", prom) + " años";
    }

    public String intentoJuegoRana() {
        StringBuilder sb = new StringBuilder();
        sb.append("Verificando condiciones para el juego de la rana...\n");
        Nino[] lista = aisha.listar();
        int total = lista.length;
        int mayores = 0;
        for (Nino n : lista) if (n.getEdad() >= 5) mayores++;
        sb.append("Total de niños: ").append(total).append("\n");
        sb.append("Niños de 5 años o más: ").append(mayores).append("\n");
        if (total > 0 && mayores * 2 > total) {
            sb.append("¡Más de la mitad cumplen la condición!\n");
            sb.append("¡Pueden jugar al juego de la rana!\n");
        } else {
            sb.append("No hay suficientes niños mayores de 5 años\n");
            sb.append("No pueden jugar todavía\n");
        }
        return sb.toString();
    }

    public String separarMenoresParaJuego(int edadLimite) {
        StringBuilder sb = new StringBuilder();
        sb.append("Separando niños para el juego de la rana...\n");
        Nino[] menores = aisha.separarMenoresQue(edadLimite);
        ultimaSeparacion = menores;
        for (Nino n : menores) dalsy.recibirNino(n);
        sb.append("Niños menores de ").append(edadLimite).append(" años pasan a Dalsy:\n");
        for (Nino n : menores) sb.append("- ").append(n).append("\n");
        sb.append("\nNiños que se quedan con Aisha para jugar:\n");
        for (Nino n : aisha.listar()) sb.append("- ").append(n).append("\n");
        sb.append("\nNOTA: Al terminar el juego, los niños volverán a su posición original\n");
        return sb.toString();
    }

    public void restaurarUltimaSeparacion() {
        if (ultimaSeparacion == null || ultimaSeparacion.length == 0) return;
        aisha.insertarAlFinal(ultimaSeparacion);
        ultimaSeparacion = new Nino[0];
    }

    public String alarmaIncendios() {
        StringBuilder sb = new StringBuilder();
        sb.append("¡ALARMA CONTRA INCENDIOS! \n");
        sb.append("PROTOCOLO DE EMERGENCIA ACTIVADO \n");
        Nino[] desdeDalsy = dalsy.transferirTodosA(lydia);
        Nino[] desdeAisha = aisha.transferirTodosA(lydia);
        int transferidos = desdeDalsy.length + desdeAisha.length;
        sb.append("Dalsy transfiere TODOS sus niños a Lydia INMEDIATAMENTE \n");
        sb.append("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE \n");
        sb.append(transferidos).append(" niños transferidos \n");
        sb.append("Lydia ahora tiene ").append(lydia.contar()).append(" niños listos para evacuar en orden \n");
        return sb.toString();
    }

    public String mostrarEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("======================================== \n");
        sb.append("        ESTADO ACTUAL\n");
        sb.append("========================================\n");

        // Lydia
        sb.append("LYDIA: \n");
        if (lydia.estaVacia()) {
            sb.append("  Cola vacía\n");
        } else {
            sb.append("  Niños en cola: ").append(lydia.contar()).append("\n");
            Nino[] l = lydia.listar();
            for (int i = 0; i < l.length; i++) sb.append("  - ").append(l[i]).append("\n");
            sb.append("\n");
        }

        // Aisha
        sb.append("AISHA:\n");
        if (aisha.estaVacia()) {
            sb.append("  Cola vacía\n");
        } else {
            sb.append("  Niños en cola: ").append(aisha.contar()).append("\n");
            Nino[] l = aisha.listar();
            for (int i = 0; i < l.length; i++) sb.append("  - ").append(l[i]).append("\n");
            sb.append("\n");
        }

        // Dalsy
        sb.append("DALSY:\n");
        if (dalsy.estaVacia()) {
            sb.append("  Cola vacía\n");
        } else {
            sb.append("  Niños en cola: ").append(dalsy.contar()).append("\n");
            Nino[] l = dalsy.listar();
            for (int i = 0; i < l.length; i++) sb.append("  - ").append(l[i]).append("\n");
            sb.append("\n");
        }

        sb.append("======================================== \n");
        return sb.toString();
    }
}