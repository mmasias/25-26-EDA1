public class Monitora {
    private String nombre;
    private Niño[] colaNiños;
    private int cantidadNiños;
    private static final int CAPACIDAD_MAXIMA = 100;
    
    public Monitora(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Niño[CAPACIDAD_MAXIMA];
        this.cantidadNiños = 0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void recibirNiño(Niño niño) {
        if (cantidadNiños < CAPACIDAD_MAXIMA) {
            colaNiños[cantidadNiños] = niño;
            cantidadNiños++;
        }
    }
    
    public Niño sacarPrimerNiño() {
        if (cantidadNiños == 0) {
            return null;
        }
        
        Niño primerNiño = colaNiños[0];
        
        for (int i = 0; i < cantidadNiños - 1; i++) {
            colaNiños[i] = colaNiños[i + 1];
        }
        
        colaNiños[cantidadNiños - 1] = null;
        cantidadNiños--;
        
        return primerNiño;
    }
    
    public Niño obtenerNiño(int posicion) {
        if (posicion >= 0 && posicion < cantidadNiños) {
            return colaNiños[posicion];
        }
        return null;
    }
    
    public int getCantidadNiños() {
        return cantidadNiños;
    }
    
    public boolean tieneSuficientesNiños(int minimo) {
        return cantidadNiños >= minimo;
    }
    
    public void transferirTodosLosNiños(Monitora destino) {
        while (cantidadNiños > 0) {
            Niño niño = sacarPrimerNiño();
            destino.recibirNiño(niño);
        }
    }
    
    public double calcularEdadPromedio() {
        if (cantidadNiños == 0) {
            return 0.0;
        }
        
        double suma = 0;
        for (int i = 0; i < cantidadNiños; i++) {
            suma += colaNiños[i].getEdad();
        }
        
        return suma / cantidadNiños;
    }
    
    public int contarNiñosMayoresDe(int edad) {
        int contador = 0;
        for (int i = 0; i < cantidadNiños; i++) {
            if (colaNiños[i].getEdad() >= edad) {
                contador++;
            }
        }
        return contador;
    }
    
    public void mostrarEstado() {
        System.out.println("\n" + nombre.toUpperCase() + ":");
        if (cantidadNiños == 0) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cantidadNiños);
            for (int i = 0; i < cantidadNiños; i++) {
                System.out.println("  - " + colaNiños[i].obtenerInformacion());
            }
        }
    }
    
    public void presentarse() {
        System.out.println(nombre + ": Hola, soy " + nombre + ", monitora de esta ludoteca\n");
    }
    
    public void pedirPresentacionGeneral() {
        for (int i = 0; i < cantidadNiños; i++) {
            colaNiños[i].presentarse();
        }
    }
    
    public void pedirPresentacionMayoresDe(int edad) {
        System.out.println(nombre + " pide que se presenten los mayores de " + edad + " años:\n");
        for (int i = 0; i < cantidadNiños; i++) {
            if (colaNiños[i].getEdad() > edad) {
                colaNiños[i].presentarse();
            }
        }
    }
    
    public void pedirPresentacionPorInicial(char letra) {
        System.out.println(nombre + " pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (int i = 0; i < cantidadNiños; i++) {
            if (colaNiños[i].getNombre().toUpperCase().charAt(0) == Character.toUpperCase(letra)) {
                colaNiños[i].presentarseSolo();
            }
        }
    }
    
    public void pedirPresentacionPrimerosCinco() {
        System.out.println(nombre + " pide que se presenten los primeros 5 niños:\n");
        int limite = Math.min(5, cantidadNiños);
        for (int i = 0; i < limite; i++) {
            colaNiños[i].presentarseSolo();
        }
    }
    
    public void pedirPresentacionUltimosCinco() {
        System.out.println(nombre + " pide que se presenten los últimos 5 niños:\n");
        int inicio = Math.max(0, cantidadNiños - 5);
        for (int i = inicio; i < cantidadNiños; i++) {
            colaNiños[i].presentarseSolo();
        }
    }
}