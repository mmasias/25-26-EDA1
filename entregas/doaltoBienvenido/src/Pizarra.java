class Pizarra {
    private String mensaje;

    public Pizarra() { 
        this.mensaje = ""; 
    }

    public void escribirMensaje(String mensaje) { 
        this.mensaje = mensaje; 
    }

    public String leerMensaje() { 
        return mensaje; 
    }

    public void limpiar() { 
        mensaje = ""; 
    }
}
