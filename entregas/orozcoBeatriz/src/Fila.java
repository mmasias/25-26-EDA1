public class Fila {
    private Nino[] lista = new Nino[0];

    public void agregar(Nino n) {
        Nino[] nuevo = new Nino[lista.length + 1];
        for (int i = 0; i < lista.length; i++) {
            nuevo[i] = lista[i];
        }
        nuevo[lista.length] = n;
        lista = nuevo;
    }

    public Nino get(int i) {
        return lista[i];
    }

    public int size() {
        return lista.length;
    }

    public Nino[] getLista() {
        return lista;
    }

     public Nino[] vaciar() {
        Nino[] copia = lista;
        lista = new Nino[0];
        return copia;
    }
}
