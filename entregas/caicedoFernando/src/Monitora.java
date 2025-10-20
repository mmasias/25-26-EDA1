public class Monitora {
    private final String nombre;
    private final Niño[] colaNiños;
    private int cantidadNiños;
    private static final int CAPACIDAD_MAXIMA = 100;
    private final Console console;
    
    public Monitora(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Niño[CAPACIDAD_MAXIMA];
        this.cantidadNiños = 0;
        this.console = new Console();
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
        console.writeln("\n" + nombre.toUpperCase() + ":");
        if (cantidadNiños == 0) {
            console.writeln("  Cola vacía");
        } else {
            console.writeln("  Niños en cola: " + cantidadNiños);
            for (int i = 0; i < cantidadNiños; i++) {
                console.writeln("  - " + colaNiños[i].obtenerInformacion());
            }
        }
    }
    
    public void presentarse() {
        console.writeln(nombre + ": Hola, soy " + nombre + ", monitora de esta ludoteca\n");
    }
    
    public void pedirPresentacionGeneral() {
        for (int i = 0; i < cantidadNiños; i++) {
            colaNiños[i].presentarse();
        }
    }
    
    public void pedirPresentacionMayoresDe(int edad) {
        console.writeln(nombre + " pide que se presenten los mayores de " + edad + " años:\n");
        for (int i = 0; i < cantidadNiños; i++) {
            if (colaNiños[i].getEdad() > edad) {
                colaNiños[i].presentarse();
            }
        }
    }
    
    public void pedirPresentacionPorInicial(char letra) {
        console.writeln(nombre + " pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (int i = 0; i < cantidadNiños; i++) {
            if (colaNiños[i].getNombre().toUpperCase().charAt(0) == Character.toUpperCase(letra)) {
                colaNiños[i].presentarseSolo();
            }
        }
    }
    
    public void pedirPresentacionPrimerosCinco() {
        final int NIÑOS_POR_PRESENTAR = 5;
        console.writeln(nombre + " pide que se presenten los primeros 5 niños:\n");
        int limiteNiñosPorPresentar = Math.min(NIÑOS_POR_PRESENTAR, cantidadNiños);
        for (int i = 0; i < limiteNiñosPorPresentar; i++) {
            colaNiños[i].presentarseSolo();
        }
    }
    
    public void pedirPresentacionUltimosCinco() {
        console.writeln(nombre + " pide que se presenten los últimos 5 niños:\n");
        int inicio = Math.max(0, cantidadNiños - 5);
        for (int i = inicio; i < cantidadNiños; i++) {
            colaNiños[i].presentarseSolo();
        }
    }
}