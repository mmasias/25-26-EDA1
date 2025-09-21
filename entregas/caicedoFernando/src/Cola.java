public class Cola {
    private final Nino[] estructura;
    private int puntoInicio;
    private int puntoFinal;
    private int contadorElementos;

    public Cola() {
        estructura = new Nino[100];
        puntoInicio = 0;
        puntoFinal = 0;
        contadorElementos = 0;
    }

    public void insertarNino(Nino participante) {
        if (contadorElementos < estructura.length) {
            estructura[puntoFinal] = participante;
            puntoFinal = (puntoFinal + 1) % estructura.length;
            contadorElementos++;
        }
    }

    public Nino[] extraerNinos() {
        Nino[] listado = new Nino[contadorElementos];
        for (int i = 0; i < contadorElementos; i++) {
            listado[i] = estructura[(puntoInicio + i) % estructura.length];
        }
        return listado;
    }

    public int obtenerTamano() {
        return contadorElementos;
    }

    public void vaciarEstructura() {
        puntoInicio = 0;
        puntoFinal = 0;
        contadorElementos = 0;
    }

    public String representarCola() {
        String cadena = "";
        Nino[] elementos = extraerNinos();
        for (int i = 0; i < elementos.length; i++) {
            cadena += elementos[i].obtenerNombre();
            if (i < elementos.length - 1)
                cadena += ", ";
        }
        return cadena.isEmpty() ? "Cola vacía" : cadena;
    }
}