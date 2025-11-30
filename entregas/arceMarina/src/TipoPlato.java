class TipoPlato {
    private String nombre;
    private int min;
    private int max;
    
    private static final Random RANDOM = new Random();

    private static final TipoPlato[] TIPOS_DISPONIBLES = {
        new TipoPlato("Bebida", 1, 2),
        new TipoPlato("Café", 2, 3),
        new TipoPlato("Colacao", 2, 4),
        new TipoPlato("Bocadillo", 3, 5),
        new TipoPlato("Ensalada", 5, 8)
    };

    TipoPlato(String nombre, int min, int max) {
        this.nombre = nombre;
        this.min = min;
        this.max = max;
    }

    int generarTiempoAleatorio() {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    String getNombre() {
        return nombre;
    }

    static TipoPlato obtenerAleatorio() {
        int indice = RANDOM.nextInt(TIPOS_DISPONIBLES.length);
        return TIPOS_DISPONIBLES[indice];
    }
}