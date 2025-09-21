public class ColaNinos {
    private Nino[] datos;
    private int frente;
    private int fin;
    private int size;

    public ColaNinos(int capacidad) {
        datos = new Nino[capacidad];
        frente = 0;
        fin = 0;
        size = 0;
    }

    public void encolar(Nino n) {
        if (size == datos.length) {
            Nino[] nuevo = new Nino[datos.length * 2];
            for (int i = 0; i < size; i++) {
                int indice = (frente + i);
                if (indice >= datos.length) {
                    indice = indice - datos.length; 
                }
                nuevo[i] = datos[indice];
            }
            datos = nuevo;
            frente = 0;
            fin = size;
        }
        datos[fin] = n;
        fin++;
        if (fin >= datos.length) fin = 0; 
        size++;
    }

    public int tamano() {
        return size;
    }

    public Nino[] extraerTodos() {
        Nino[] res = new Nino[size];
        for (int i = 0; i < size; i++) {
            int indice = (frente + i);
            if (indice >= datos.length) indice = indice - datos.length;
            res[i] = datos[indice];
        }
        frente = 0;
        fin = 0;
        size = 0;
        return res;
    }

    public void moverATo(ColaNinos otra) {
        for (int i = 0; i < size; i++) {
            int indice = (frente + i);
            if (indice >= datos.length) indice = indice - datos.length;
            otra.encolar(datos[indice]);
        }
        frente = 0;
        fin = 0;
        size = 0;
    }
}