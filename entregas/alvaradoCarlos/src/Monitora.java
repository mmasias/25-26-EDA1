import java.util.Random;

public class Monitora {

    private String nombre;
    private int cantidadActual;
    private Niño[] niños;
    private Random random;

    public Monitora(String nombre, int maxNiños) {
        this.nombre = nombre;
        this.cantidadActual = 0;
        this.niños = new Niño[maxNiños];
        this.random = new Random();
    }

    public void recibeNiños(int minuto) {
        int recibidos = niñosRecibidosPorMinuto(minuto);

        for (int i = 0; i < recibidos && cantidadActual < niños.length; i++) {
            niños[cantidadActual] = new Niño();
            cantidadActual++;
            System.out.println("Minuto " + minuto + ": llegó un niño");
        }
    }

    private int niñosRecibidosPorMinuto(int minuto) {
        if (minuto < 10) {
            return random.nextInt(3);
        } else if (minuto < 30) {
            if (minuto % 3 == 0 && random.nextBoolean()) {
                return 1;
            }
            return 0;
        } else {
            return 0;
        }
    }

    public int cantidadDeNiños() {
        return cantidadActual;
    }

    public String nombre() {
        return nombre;
    }

    public Niño[] entregaNiños(Monitora aisha) {
        aisha.recibeNiñosParaJugar(niños);

        int tamaño = calcularTamañoGrupo(aisha);
        Niño[] grupo = extraerGrupo(tamaño);
        reacomodarNiños(tamaño);

        cantidadActual -= tamaño;

        System.out.println("Se entregó un grupo de " + grupo.length + " niños a " + aisha.nombre());

        return grupo;
    }

    private int calcularTamañoGrupo(Monitora aisha) {
        return Math.min(aisha.cantidadDeNiños(), cantidadActual);
    }

    private Niño[] extraerGrupo(int tamaño) {
        Niño[] grupo = new Niño[tamaño];
        for (int i = 0; i < tamaño; i++) {
            grupo[i] = niños[i];
        }
        return grupo;
    }

    private void reacomodarNiños(int tamaño) {
        for (int i = tamaño; i < cantidadActual; i++) {
            niños[i - tamaño] = niños[i];
        }
        for (int i = cantidadActual - tamaño; i < cantidadActual; i++) {
            niños[i] = null;
        }
    }

    public void entregaPizarrin(Pizarra[] pizarrines, Niño[] niños) {
        int cantidad = Math.min(pizarrines.length, cantidadActual);

        for (int i = 0; i < cantidad; i++) {
            niños[i].recibirPizarrin(pizarrines[i]);
            niños[i].marcarParticipacion();
            System.out.println("Niño " + i + " recibio un pizarrín de " + nombre);
        }
    }

    public void limpiaPizarra(Pizarra pizarra) {
        pizarra.limpiar();
        System.out.println(nombre() + " limpió la pizarra del salón");
    }

    public void pideLimpiarPizarrines(Pizarra[] pizarrines, Niño[] niños) {
        for (int i = 0; i < niños.length; i++) {
            if (niños[i] != null && niños[i].tienePizarrin()) {
                niños[i].limpiarPizarrin();
                System.out.println("Niño " + i + " limpió su pizarrín");
            }
        }
    }

    public Mensaje escribeMensaje(Pizarra pizarra) {
        String[] mensajes = { "¡Hola niños!", "Vamos a jugar", "Atentos al mensaje" };
        String mensaje = mensajes[new Random().nextInt(mensajes.length)];

        pizarra.escribir(mensaje);
        System.out.println(nombre() + " escribió en la pizarra: " + mensaje);

        return new Mensaje(mensaje, 1);
    }

    public void terminaJuegoActual() {
        for (int i = 0; i < cantidadActual; i++) {
            Niño niño = niños[i];
            if (niño != null && niño.participoEnJuego()) {
                System.out.println("Niño " + i + " ha terminado el juego con " + nombre());
                niños[i] = null;
            }
        }

        int niñosSinJugar = 0;
        for (int i = 0; i < cantidadActual; i++) {
            if (niños[i] != null) {
                niños[niñosSinJugar++] = niños[i];
            }
        }
        cantidadActual = niñosSinJugar;

        System.out.println(nombre() + " terminó el juego y liberó a los niños que jugaron");
    }

    private void recibeNiñosParaJugar(Niño[] grupo) {
        for (int i = 0; i < grupo.length; i++) {
            if (cantidadActual < niños.length) {
                niños[cantidadActual] = grupo[i];
                cantidadActual++;
                System.out.println(nombre + " recibió al niño " + i + " de golpe");
            } else {
                System.out.println(nombre + " no tiene espacio para el niño " + i);
            }
        }
    }

    public boolean haJugado(Niño niño) {
        for (int i = 0; i < cantidadActual; i++) {
            if (niños[i] != null && niños[i].equals(niño) && niños[i].participoEnJuego()) {
                return true;
            }
        }
        return false;
    }

}
