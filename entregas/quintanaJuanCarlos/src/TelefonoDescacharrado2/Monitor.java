import java.util.Random;

public class Monitor {
    private static final int LONGITUD_MENSAJE = 10;
    private static final double P_CAMBIO_1 = 0.30;
    private static final double P_CAMBIO_2 = 0.10;

    private final String nombre;
    private final Cola cola;
    private boolean jugando;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.cola = new Cola(20);
        this.jugando = false;
    }

    public void recibir(Nino nino) {
        if (!cola.encolar(nino)) {
            System.out.println("AVISO: Cola de " + nombre + " llena. No se pudo añadir a " + nino.getNombre());
        }
    }

    public void entregarATransferencia(Monitor destino) {
        int traspasados = 0;
        while (!cola.vacia()) {
            Nino n = cola.desencolar();
            if (!destino.cola.encolar(n)) {
                this.cola.encolar(n);
                break;
            }
            traspasados++;
        }
        if (traspasados > 0) {
            System.out.println("[" + nombre + "→" + destino.nombre + "] Traspasados: " + traspasados);
        }
    }

    public boolean puedeJugar() {
        return !jugando && cola.tamano() > 5;
    }

    public boolean estaJugando() {
        return jugando;
    }

    public int tamanoCola() {
        return cola.tamano();
    }

    public boolean tieneNiños() {
        return !cola.vacia();
    }

    public void mostrarListaNiños() {
        System.out.print("[" + nombre + "] Cola (" + cola.tamano() + "): ");
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null) {
                System.out.print(n.getNombre());
                if (i < cola.tamano() - 1) System.out.print(", ");
            }
        }
        System.out.println();
    }

    public String getUsoMemoria() {
        return cola.tamano() + "/20 (fijo)";
    }

    public int jugar(Pizarra pizarraSalon, int minutoInicio) {
        if (cola.tamano() <= 5) return 0;
        jugando = true;

        pizarraSalon.limpiar();
        limpiarPizarrines();

        String mensaje = generarMensajeInicial(LONGITUD_MENSAJE);
        System.out.println("Mensaje original: " + mensaje);

        int participantes = cola.tamano();
        Random rng = new Random();
        for (int i = 0; i < participantes; i++) {
            Nino n = cola.get(i);
            if (n != null) {
                n.recibirPizarrin(new Pizarra());
                String deformado = n.copiarMensaje(mensaje, P_CAMBIO_1, P_CAMBIO_2, rng);
                mensaje = deformado;
            }
        }

        pizarraSalon.escribirMensaje(mensaje);
        System.out.println("Mensaje final:    " + pizarraSalon.leerMensaje());

        int duracion = participantes + 2;
        cola.vaciar();

        jugando = false;
        return duracion;
    }

    private void limpiarPizarrines() {
        for (int i = 0; i < cola.tamano(); i++) {
            Nino n = cola.get(i);
            if (n != null) {
                n.limpiarPizarrin();
            }
        }
    }

    private String generarMensajeInicial(int len) {
        Random rng = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = (char) ('A' + rng.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
}