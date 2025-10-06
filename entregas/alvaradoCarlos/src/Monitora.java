public class Monitora {

    private String nombre;
    private int cantidadDeNiños;
    private Niño[] niños;

    public Monitora(String nombre, int maxNiños) {
        this.nombre = nombre;
        this.cantidadDeNiños = 0;
        this.niños = new Niño[maxNiños];
    }

    public void recibeNiño(Niño niño) {
        for (int i = 0; i < niños.length; i++) {
            if (niños[i] == null) {
                niños[i] = niño;
                System.out.println("Llega " + niño.nombre() + " " + "(" + niño.edad() + " años" + ")");
                System.out.println(niño.nombre() + " pasa a la cola de " + nombre);
                cantidadDeNiños++;
                return;
            }
        }
        System.out.println("No hay espacio para más niños.");
    }

    public Niño[] entregaNiños(Monitora monitora) {
        if (!haySuficientesNinos())
            return new Niño[0];

        int espacioDisponible = espacioDisponible(monitora);
        if (espacioDisponible == 0)
            return new Niño[0];

        Niño[] niñosPosibles = extraerGrupo(cantidadDeNiños);

        Niño[] grupoRecibido = monitora.recibeNiñosParaJugar(niñosPosibles);

        reacomodarNiños(grupoRecibido.length);
        cantidadDeNiños -= grupoRecibido.length;

        System.out.println(nombre + " transfiere sus niños a " + monitora.nombre());
        mostrarGrupo(grupoRecibido);

        return grupoRecibido;
    }

    private boolean haySuficientesNinos() {
        if (cantidadDeNiños < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego.");
            System.out.println("Se necesitan al menos 5 niños.\n");
            return false;
        }
        return true;
    }

    private int espacioDisponible(Monitora monitora) {
    int espacioDisponible = 5 - monitora.cantidadDeNiños();
    if (espacioDisponible <= 0) {
        System.out.println(monitora.nombre() + " no tiene espacio para recibir más niños.");
        return 0;
    }
    return espacioDisponible;
}

    private String nombre() {
        return nombre;
    }

    public int cantidadDeNiños() {
        return cantidadDeNiños;
    }

    private void mostrarGrupo(Niño[] grupo) {
        System.out.println("Niños transferidos:");
        for (Niño niño : grupo) {
            if (niño != null) {
                System.out.println("- " + niño.nombre());
            } else {
                System.out.println("niño nulo");
            }
        }
        System.out.println();
    }

    private Niño[] recibeNiñosParaJugar(Niño[] grupo) {
        int maxRecibir = 5 - cantidadDeNiños;
        int recibidos = 0;

        Niño[] grupoTransferido = new Niño[Math.min(grupo.length, maxRecibir)];

        for (int i = 0; i < grupo.length && recibidos < maxRecibir; i++) {
            if (grupo[i] != null) {
                niños[cantidadDeNiños++] = grupo[i];
                grupoTransferido[recibidos++] = grupo[i];
                System.out.println(nombre + " recibió al niño " + grupo[i].nombre());
            }
        }

        for (int i = recibidos; i < grupoTransferido.length; i++) {
            grupoTransferido[i] = null;
        }

        return grupoTransferido;
    }

    private Niño[] extraerGrupo(int tamaño) {
        Niño[] grupo = new Niño[tamaño];
        for (int i = 0; i < tamaño; i++) {
            grupo[i] = niños[i];
        }
        return grupo;
    }

    private void reacomodarNiños(int tamaño) {
        for (int i = tamaño; i < cantidadDeNiños; i++) {
            niños[i - tamaño] = niños[i];
        }
        for (int i = cantidadDeNiños - tamaño; i < cantidadDeNiños; i++) {
            niños[i] = null;
        }
    }
}
