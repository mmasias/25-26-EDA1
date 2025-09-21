public class Niño {

    private Pizarra pizarrin;

    public Niño() {
        this.pizarrin = null;
    }

    public int recibeMensaje(String mensaje, Pizarra pizarrin) {
        if (this.pizarrin != null) {
            this.pizarrin.escribir(mensaje);
            System.out.println("El niño recibió y escribió el mensaje: " + mensaje);
        }
        return 1;
    }

    public int muestraMensaje(String mensaje, Niño siguiente) {
        if (siguiente != null) {

            siguiente.recibeMensaje(mensaje, siguiente.pizarrin);
            System.out.println("El niño pasó el mensaje al siguiente niño");
        } else {
            System.out.println("No hay siguiente niño para pasar el mensaje");
        }
        return 1; 
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

}
