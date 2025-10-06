public class Aisha {
    private ColaNinos cola;

    public Aisha() {
        cola = new ColaNinos();
    }

    public void meterEnCola(Nino nino) {
        cola.encolar(nino);
    }

    public ColaNinos getCola() {
        return cola;
    }

    public void presentarse() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
    }

    public void pedirPresentaciones() {
        System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        for (int i = 0; i < cola.getTamaño(); i++) {
            Nino nino = cola.getNino(i);
            System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre() + " y tengo " + nino.getEdad() + " años");
        }
    }

    public void pedirPresentacionesMayoresDe(int edadMinima) {
        System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años:\n");
        for (int i = 0; i < cola.getTamaño(); i++) {
            Nino nino = cola.getNino(i);
            if (nino.getEdad() > edadMinima) {
                System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre() + " y tengo " + nino.getEdad() + " años");
            }
        }
    }

    public void pedirPresentacionesPorLetra(char letra) {
        System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        for (int i = 0; i < cola.getTamaño(); i++) {
            Nino nino = cola.getNino(i);
            if (!nino.getNombre().isEmpty() && Character.toUpperCase(nino.getNombre().charAt(0)) == Character.toUpperCase(letra)) {
                System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre());
            }
        }
    }

    public void pedirPrimerosCinco() {
        System.out.println("Aisha pide que se presenten los primeros 5 niños:\n");
        int limite = Math.min(5, cola.getTamaño());
        for (int i = 0; i < limite; i++) {
            Nino nino = cola.getNino(i);
            System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre());
        }
    }

    public void pedirUltimosCinco() {
        System.out.println("Aisha pide que se presenten los últimos 5 niños:\n");
        int inicio = Math.max(0, cola.getTamaño() - 5);
        for (int i = inicio; i < cola.getTamaño(); i++) {
            Nino nino = cola.getNino(i);
            System.out.println(nino.getNombre() + ": Hola, soy " + nino.getNombre());
        }
    }

    public double calcularEdadPromedio() {
        if (cola.estaVacia()) {
            return 0;
        }
        int suma = 0;
        for (int i = 0; i < cola.getTamaño(); i++) {
            suma += cola.getNino(i).getEdad();
        }
        return (double) suma / cola.getTamaño();
    }

    public boolean verificarJuegoRana() {
        int total = cola.getTamaño();
        int mayores = 0;
        
        for (int i = 0; i < cola.getTamaño(); i++) {
            if (cola.getNino(i).getEdad() >= 5) {
                mayores++;
            }
        }
        
        return mayores > total / 2;
    }

    public int contarMayoresDe5() {
        int count = 0;
        for (int i = 0; i < cola.getTamaño(); i++) {
            if (cola.getNino(i).getEdad() >= 5) {
                count++;
            }
        }
        return count;
    }
}
