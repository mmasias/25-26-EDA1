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

    public Niño[] entregaNiños() {
        int tamaño = Math.min(5, cantidadActual);
        Niño[] grupo = new Niño[tamaño];

        for (int i = 0; i < tamaño; i++) {
            grupo[i] = niños[i];
        }

        for (int i = tamaño; i < cantidadActual; i++) {
            niños[i - tamaño] = niños[i];
        }

        for (int i = cantidadActual - tamaño; i < cantidadActual; i++) {
            niños[i] = null;
        }

        cantidadActual -= tamaño;

        return grupo;
    }

    public void entregaPizarrin(Pizarra[] pizarrines, Niño[] niños) {
        int cantidad = Math.min(pizarrines.length, cantidadActual);

        for (int i = 0; i < cantidad; i++) {
            niños[i].recibirPizarrin(pizarrines[i]);
            System.out.println("Niño " + i + " recibio un pizarrín de " + nombre());
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

    public String escribeMensaje(Pizarra pizarra) {
        String[] mensajes = {
                "¡Hola niños!",
                "Vamos a jugar juntos",
                "Atentos al mensaje",
                "Escribe con cuidado",
                "Diviértanse mucho"
        };

        String mensaje = mensajes[random.nextInt(mensajes.length)];

        pizarra.escribir(mensaje);
        System.out.println(nombre() + " escribió en la pizarra: " + mensaje);

        return mensaje;
    }

    public void terminaJuegoActual() {
        for (int i = 0; i < cantidadActual; i++) {
            if (niños[i] != null) {
                System.out.println("Niño " + i + " ha terminado el juego con " + nombre());
            }
            niños[i] = null;
        }
        cantidadActual = 0;
        System.out.println(nombre() + " terminó el juego y liberó a todos los niños");
    }

}
