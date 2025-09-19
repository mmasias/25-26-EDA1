class Niño {
	String pizarrin;
	int posicion;

	public Niño(String pizarrin, int posicion) {
		this.pizarrin = pizarrin;
		this.posicion = posicion;
	}

	void recibirMensaje(String mensaje) {
		this.pizarrin = mensaje;
	}

    void cambiarMensaje(String nuevoMensaje) {
        this.pizarrin = nuevoMensaje;
    }
    
}
