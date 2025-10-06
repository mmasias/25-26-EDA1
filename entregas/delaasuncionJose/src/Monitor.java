class Monitora {
    private String nombre;
    private Nino[] cola;
    private int size;

    public Monitora(String nombre) {
        this.nombre = nombre;
        this.cola = new Nino[500];
        this.size = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int contar() {
        return size;
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public void recibirNino(Nino n) {
        if (size >= cola.length) {
            Nino[] nuevo = new Nino[cola.length * 2];
            for (int i = 0; i < cola.length; i++)
                nuevo[i] = cola[i];
            cola = nuevo;
        }
        cola[size++] = n;
    }

    public Nino[] listar() {
        Nino[] res = new Nino[size];
        for (int i = 0; i < size; i++)
            res[i] = cola[i];
        return res;
    }

    public Nino[] transferirTodosA(Monitora destino) {
        Nino[] transferidos = listar();
        for (int i = 0; i < size; i++)
            destino.recibirNino(cola[i]);
        size = 0;
        return transferidos;
    }

    public double edadPromedio() {
        if (size == 0)
            return -1;
        int suma = 0;
        for (int i = 0; i < size; i++)
            suma += cola[i].getEdad();
        return (double) suma / size;
    }

    public Nino[] separarMenoresQue(int edadLimite) {
        Nino[] menoresTmp = new Nino[size];
        int m = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (cola[i].getEdad() < edadLimite) {
                menoresTmp[m++] = cola[i];
            } else {
                cola[j++] = cola[i];
            }
        }
        size = j;
        Nino[] menores = new Nino[m];
        for (int i = 0; i < m; i++)
            menores[i] = menoresTmp[i];
        return menores;
    }

    public Nino[] primerosN(int n) {
        int lim = Math.min(n, size);
        Nino[] res = new Nino[lim];
        for (int i = 0; i < lim; i++)
            res[i] = cola[i];
        return res;
    }

    public Nino[] ultimosN(int n) {
        int lim = Math.min(n, size);
        Nino[] res = new Nino[lim];
        int start = size - lim;
        for (int i = 0; i < lim; i++)
            res[i] = cola[start + i];
        return res;
    }

    public Nino[] listarMayoresQue(int edadMin) {
        Nino[] temp = new Nino[size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (cola[i].getEdad() > edadMin)
                temp[k++] = cola[i];
        }
        Nino[] res = new Nino[k];
        for (int i = 0; i < k; i++)
            res[i] = temp[i];
        return res;
    }

    public Nino[] listarPorInicial(char inicial) {
        char lower = Character.toLowerCase(inicial);
        Nino[] temp = new Nino[size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            String nom = cola[i].getNombre();
            if (!nom.isEmpty() && Character.toLowerCase(nom.charAt(0)) == lower)
                temp[k++] = cola[i];
        }
        Nino[] res = new Nino[k];
        for (int i = 0; i < k; i++)
            res[i] = temp[i];
        return res;
    }

    public void insertarAlFinal(Nino[] items) {
        for (int i = 0; items != null && i < items.length; i++)
            recibirNino(items[i]);
    }
}