import java.util.Random;

public class Niño {

    private Pizarra pizarrin;
    private boolean participoEnJuego;

    public Niño() {
        this.pizarrin = null;
        this.participoEnJuego = false;
    }

    public int recibeMensaje(String mensaje, Pizarra pizarrin) {
        if (this.pizarrin != null) {
            this.pizarrin.escribir(mensaje);
            System.out.println("El niño recibió y escribió el mensaje: " + mensaje);
        }
        return 1;
    }

    public String muestraMensaje(String mensaje, Niño siguiente) {
        String mensajeDeformado = deformarMensaje(mensaje);
        if (siguiente != null) {
            siguiente.recibeMensaje(mensajeDeformado, siguiente.pizarrin);
        }
        System.out.println("El niño pasó el mensaje al siguiente: " + mensajeDeformado);
        return mensajeDeformado;
    }

    public int escribeEnPizarra(Pizarra pizarra, String mensaje) {
        if (pizarra != null) {
            pizarra.escribir(mensaje);
            System.out.println("El último niño escribió el mensaje en la pizarra del salón: " + mensaje);
        }
        return 1;
    }

    public boolean tienePizarrin() {
        return pizarrin != null;
    }

    public void recibirPizarrin(Pizarra pizarrin) {
        this.pizarrin = pizarrin;
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) {
            pizarrin.limpiar();
            System.out.println("El niño limpió su pizarrín");
        }
    }

    public void marcarParticipacion() {
        participoEnJuego = true;
        System.out.println("Marca participacion de Niño");
    }

    public boolean participoEnJuego() {
        return participoEnJuego;
    }

    private String deformarMensaje(String mensaje) {
        Random random = new Random();
        char[] chars = mensaje.toCharArray();

        int cambios = 1 + random.nextInt(2);
        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(chars.length);
            char nuevaLetra = (char) ('a' + random.nextInt(26));
            chars[pos] = nuevaLetra;
        }

        return new String(chars);
    }
}
