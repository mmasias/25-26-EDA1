public class ColaNinos {
    private final Nino[] datos;
    private int indiceInicio;
    private int indiceFin;
    private int cantidad;

    public ColaNinos(int capacidad) {
        this.datos = new Nino[capacidad];
        this.indiceInicio = 0;
        this.indiceFin = 0;
        this.cantidad = 0;
    }
