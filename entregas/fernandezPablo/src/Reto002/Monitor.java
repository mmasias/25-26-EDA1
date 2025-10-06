package Reto002;

import java.util.*;

class Monitor {
    private String nombre;
    private List<Niño> cola;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.cola = new ArrayList<>();
    }

    public void recibeNiño(Niño niño) {
        cola.add(niño);
    }

    public void entregaNiños(Monitor otro) {
        for (Niño n : cola) {
            otro.recibeNiño(n);
        }
        cola.clear();
    }

    public boolean tieneNiños() {
        return !cola.isEmpty();
    }

    public int getCantidadNiños() {
        return cola.size();
    }

    public void presentarNiños() {
        for (Niño n : cola) {
            n.presentarse();
        }
    }

    public void presentarNiñosPorEdad(int edadMinima) {
        for (Niño n : cola) {
            if (n.getEdad() >= edadMinima) n.presentarse();
        }
    }

    public void presentarNiñosPorInicial(char letra) {
        for (Niño n : cola) {
            if (n.getNombre().toUpperCase().charAt(0) == letra) {
                System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
            }
        }
    }

    public void presentarPrimeros(int cantidad) {
        for (int i = 0; i < Math.min(cantidad, cola.size()); i++) {
            Niño n = cola.get(i);
            System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
        }
    }

    public void presentarUltimos(int cantidad) {
        int start = Math.max(0, cola.size() - cantidad);
        for (int i = start; i < cola.size(); i++) {
            Niño n = cola.get(i);
            System.out.println("[" + n.getNombre() + "]: Hola, soy " + n.getNombre());
        }
    }

    public double calcularEdadPromedio() {
        if (cola.isEmpty()) return 0;
        int suma = 0;
        for (Niño n : cola) suma += n.getEdad();
        return (double) suma / cola.size();
    }

    public int contarMayoresDe(int edad) {
        int cont = 0;
        for (Niño n : cola) if (n.getEdad() >= edad) cont++;
        return cont;
    }

    public List<Niño> extraerMenoresDe(int edad) {
        List<Niño> menores = new ArrayList<>();
        Iterator<Niño> it = cola.iterator();
        while (it.hasNext()) {
            Niño n = it.next();
            if (n.getEdad() < edad) {
                menores.add(n);
                it.remove();
            }
        }
        return menores;
    }

    public int transferirTodos(Monitor otro) {
        int cantidad = cola.size();
        otro.cola.addAll(this.cola);
        this.cola.clear();
        return cantidad;
    }

    public void mostrarNiños() {
        for (Niño n : cola) {
            System.out.println("- " + n.getNombre() + " (" + n.getEdad() + " años)");
        }
    }

    public void mostrarEstado() {
        System.out.println(nombre.toUpperCase() + ":");
        if (cola.isEmpty()) {
            System.out.println("  Cola vacía");
        } else {
            System.out.println("  Niños en cola: " + cola.size());
            mostrarNiños();
        }
        System.out.println();
    }
}

