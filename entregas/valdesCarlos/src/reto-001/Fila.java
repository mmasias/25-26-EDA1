package reto-001;

class Fila {
    Nino[] ninos;

    public Fila(int cantidad) {
        ninos = new Nino[cantidad];
        for (int i = 0; i < cantidad; i++) {
            ninos[i] = new Nino("Nino" + (i + 1));
        }
    }

    public void jugar(Mensaje mensaje) {
        
        for (Nino n : ninos) {
            n.limpiarPizarrin();
        }

        System.out.println("Mensaje original: " + mensaje.getTexto());

        
        for (int i = 0; i < ninos.length; i++) {
            ninos[i].recibirMensaje(mensaje, i);
            System.out.println(ninos[i].nombre + " → " + ninos[i].mostrarMensaje());
        }

        System.out.println("Mensaje final en la pizarra: " + mensaje.getTexto());
    }
}
