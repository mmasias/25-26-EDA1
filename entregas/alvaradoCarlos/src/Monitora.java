import java.util.Arrays;

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

        Niño[] grupoAEnviar = extraerGrupo(cantidadDeNiños);

        Niño[] grupoRecibido = monitora.recibeNiñosParaJugar(grupoAEnviar);

        System.out.println(grupoRecibido.length);
        reacomodarNiños(grupoRecibido.length);
        cantidadDeNiños -= grupoRecibido.length;

        System.out.println(nombre + " transfiere sus niños a " + monitora.nombre());
        mostrarGrupo(grupoRecibido);

        return grupoRecibido;
    }

    private Niño[] extraerGrupo(int tamaño) {
        Niño[] grupo = new Niño[tamaño];
        for (int i = 0; i < tamaño; i++) {
            grupo[i] = niños[i];
        }
        return grupo;
    }

    private boolean haySuficientesNinos() {
        if (cantidadDeNiños < 5) {
            System.out.println("No hay suficientes niños para iniciar el juego.");
            System.out.println("Se necesitan al menos 5 niños.\n");
            return false;
        }
        return true;
    }

    public String nombre() {
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

    public Niño[] recibeNiñosParaJugar(Niño[] grupo) {
        int recibidos = 0;
        Niño[] grupoRecibido = new Niño[grupo.length];

        for (int i = 0; i < grupo.length; i++) {
            if (grupo[i] != null) {
                niños[cantidadDeNiños++] = grupo[i];
                grupoRecibido[recibidos++] = grupo[i];
                System.out.println(nombre + " recibió al niño " + grupo[i].nombre());
            }
        }

        return grupoRecibido;
    }

    private void reacomodarNiños(int tamaño) {
        for (int i = tamaño; i < cantidadDeNiños; i++) {
            niños[i - tamaño] = niños[i];
        }
        for (int i = cantidadDeNiños - tamaño; i < cantidadDeNiños; i++) {
            niños[i] = null;
        }
    }

    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + ", monitora de esta ludoteca");
    }

    public void prepararCadenaPresentaciones() {
        for (int i = 0; i < cantidadDeNiños - 1; i++) {
            niños[i].siguienteNiño(niños[i + 1]);
        }
        if (cantidadDeNiños > 0) {
            niños[cantidadDeNiños - 1].siguienteNiño(null);
        }
    }

    public void pideAlPrimerNiñoPresentarse() {
        if (cantidadDeNiños == 0) {
            System.out.println("No hay niños en la cola de " + nombre + " para presentarse.");
            return;
        }

        niños[0].presentarse();
    }

    public void pedirCincoPrimerosPresentarse() {
        int limite = Math.min(5, cantidadDeNiños);

        for (int i = 0; i < limite; i++) {
            niños[i].presentarse();
        }
    }

    public void pedirCincoUltimosPresentarse() {
        int inicio = Math.max(0, cantidadDeNiños - 5);

        for (int i = inicio; i < cantidadDeNiños; i++) {
            niños[i].presentarse();
        }
    }

    public void pedirNiñosMayoresDe5Presentarse() {
        boolean hayNiños = false;

        for (int i = 0; i < cantidadDeNiños; i++) {
            Niño niño = niños[i];
            if (niño.edad() > 5) {
                niño.presentarse();
                hayNiños = true;
            }
        }

        if (!hayNiños) {
            System.out.println("No hay niños mayores de 5 años en la cola.\n");
        }
    }

    public void pedirNiñosPorLetra(char letra) {
        boolean hayNiños = false;

        for (int i = 0; i < cantidadDeNiños; i++) {
            Niño niño = niños[i];
            if (niño.nombre().toUpperCase().charAt(0) == Character.toUpperCase(letra)) {
                niño.presentarse();
                hayNiños = true;
            }
        }

        if (!hayNiños) {
            System.out.println("No hay niños cuyos nombres empiecen con '" + letra + "'.\n");
        }
    }

    public double decirEdadPromedio() {
        if (cantidadDeNiños == 0) {
            return 0.0;
        }

        int suma = 0;
        for (int i = 0; i < cantidadDeNiños; i++) {
            suma += niños[i].edad();
        }
        return (double) suma / cantidadDeNiños;
    }

    public Niño[] separarNiñosMenoresDe5(Monitora dalsy) {
        if (cantidadDeNiños == 0) {
            System.out.println("No hay niños en la cola de " + nombre + "\n");
            return new Niño[0];
        }

        Niño[] menoresDe5 = new Niño[cantidadDeNiños];
        Niño[] mayoresOIgual5 = new Niño[cantidadDeNiños];
        int idxMenores = 0;
        int idxMayores = 0;

        for (int i = 0; i < cantidadDeNiños; i++) {
            Niño niño = niños[i];
            if (niño.edad() < 5) {
                menoresDe5[idxMenores++] = niño;
            } else {
                mayoresOIgual5[idxMayores++] = niño;
            }
        }

        System.out.println("Niños menores de 5 años pasan a " + dalsy.nombre() + ":");
        for (int i = 0; i < idxMenores; i++) {
            dalsy.recibeNiño(menoresDe5[i]);
            System.out.println("- " + menoresDe5[i].nombre() + " (" + menoresDe5[i].edad() + " años)");
        }
        System.out.println();

        reorganizarCola(mayoresOIgual5, idxMayores);

        System.out.println("Niños que se quedan con " + nombre + " para jugar:");
        for (int i = 0; i < cantidadDeNiños; i++) {
            System.out.println("- " + niños[i].nombre() + " (" + niños[i].edad() + " años)");
        }
        System.out.println();

        return menoresDe5;
    }

    public void reorganizarCola(Niño[] nuevosNiños, int cantidad) {
        for (int i = 0; i < cantidadDeNiños; i++) {
            niños[i] = null;
        }
        cantidadDeNiños = 0;

        for (int i = 0; i < cantidad; i++) {
            if (nuevosNiños[i] != null) {
                niños[cantidadDeNiños++] = nuevosNiños[i];
            }
        }
    }

    public Niño[] entregaTodosNiñosA(Monitora otraMonitora) {
        if (cantidadDeNiños == 0)
            return new Niño[0];

        Niño[] grupo = Arrays.copyOf(niños, cantidadDeNiños);

        for (int i = 0; i < cantidadDeNiños; i++) {
            otraMonitora.recibeNiño(niños[i]);
        }

        for (int i = 0; i < cantidadDeNiños; i++) {
            niños[i] = null;
        }
        int transferidos = cantidadDeNiños;
        cantidadDeNiños = 0;

        System.out.println(nombre + " transfiere TODOS sus niños a " + otraMonitora.nombre + " INMEDIATAMENTE");

        return Arrays.copyOf(grupo, transferidos);
    }

    public void mostrarEstado() {
        System.out.println(nombre.toUpperCase() + ":");

        if (cantidadDeNiños == 0) {
            System.out.println("  Cola vacía\n");
            return;
        }

        System.out.println("  Niños en cola: " + cantidadDeNiños);
        for (int i = 0; i < cantidadDeNiños; i++) {
            Niño niño = niños[i];
            System.out.println("  - " + niño.nombre() + " (" + niño.edad() + " años)");
        }
        System.out.println();
    }

}
