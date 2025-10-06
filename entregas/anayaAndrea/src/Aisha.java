public class Aisha {
    private Nino[] fila;
    private int cantidad;

    public Aisha() {
        fila = new Nino[10];
        cantidad = 0;
    }

    public void recibirNinos(Nino[] nuevos, int numNuevos) {
        for (int i = 0; i < numNuevos; i++) {
            agregar(nuevos[i]);
        }
    }

    private void agregar(Nino nino) {
        if (cantidad == fila.length) {
            crecer();
        }
        fila[cantidad++] = nino;
    }

    private void crecer() {
        Nino[] nueva = new Nino[fila.length * 2];
        System.arraycopy(fila, 0, nueva, 0, fila.length);
        fila = nueva;
    }

    public boolean puedeJugar() {
        return cantidad >= 5;
    }

    public Nino[] getFila() {
        Nino[] copia = new Nino[cantidad];
        System.arraycopy(fila, 0, copia, 0, cantidad);
        return copia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void vaciarFila() {
        cantidad = 0;
    }

    public void presentarse() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
        for (int i = 0; i < cantidad; i++) {
            fila[i].presentarse();
        }
    }

    public void presentarMayoresDe(int edad) {
        System.out.println("Aisha pide que se presenten los mayores de " + edad + " años:\n");
        for (int i = 0; i < cantidad; i++) {
            if (fila[i].getEdad() > edad) fila[i].presentarse();
        }
    }

    public void presentarPorLetra(char letra) {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (int i = 0; i < cantidad; i++) {
            if (Character.toUpperCase(fila[i].getNombre().charAt(0)) == Character.toUpperCase(letra))
                fila[i].presentarseSimple();
        }
    }

    public void presentarPrimerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        for (int i = 0; i < Math.min(5, cantidad); i++) fila[i].presentarseSimple();
    }

    public void presentarUltimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        for (int i = Math.max(0, cantidad - 5); i < cantidad; i++) fila[i].presentarseSimple();
    }

    public double edadPromedio() {
        if (cantidad == 0) return 0;
        int suma = 0;
        for (int i = 0; i < cantidad; i++) suma += fila[i].getEdad();
        return (double) suma / cantidad;
    }
}
