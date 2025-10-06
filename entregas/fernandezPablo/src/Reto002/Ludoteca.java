package Reto002;

import utils.Console;
import java.util.*;

public class Ludoteca {

    private Monitor lydia;
    private Monitor aisha;
    private Monitor dalsy;
    private Console console;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        dalsy = new Monitor("Dalsy");
        console = new Console();
    }

    public void simularLlegadaNiño() {
        String nombre = console.readString("Ingrese el nombre del niño: ");
        int edad = console.readInt("Ingrese la edad de " + nombre + ": ");
        Niño niño = new Niño(nombre, edad);
        console.writeln("\nLlega " + nombre + " (" + edad + " años)");
        console.writeln(nombre + " pasa a la cola de Lydia");
        lydia.recibeNiño(niño);
    }

    public void simularInicioDeJuego() {
        if (lydia.tieneNiños() && lydia.getCantidadNiños() >= 5) {
            console.writeln("Lydia transfiere sus niños a Aisha");
            lydia.entregaNiños(aisha);
        } else {
            console.writeln("No hay suficientes niños para iniciar el juego");
            console.writeln("Se necesitan al menos 5 niños");
        }
    }

    public void presentacionesGenerales() {
        console.writeln("Aisha: Hola, soy Aisha, monitora de esta ludoteca\n");
        aisha.presentarNiños();
    }

    public void presentacionesPorEdad() {
        int edadMinima = console.readInt("Ingrese edad mínima: ");
        console.writeln("\nAisha pide que se presenten los mayores de " + edadMinima + " años:\n");
        aisha.presentarNiñosPorEdad(edadMinima);
    }

    public void presentacionesPorInicial() {
        String letra = console.readString("Ingrese la letra inicial: ").toUpperCase();
        console.writeln("\nAisha pide que se presenten los niños cuyo nombre empieza con '" + letra + "':\n");
        aisha.presentarNiñosPorInicial(letra.charAt(0));
    }

    public void primerosCinco() {
        console.writeln("Aisha pide que se presenten los primeros 5 niños:\n");
        aisha.presentarPrimeros(5);
    }

    public void ultimosCinco() {
        console.writeln("Aisha pide que se presenten los últimos 5 niños:\n");
        aisha.presentarUltimos(5);
    }

    public void conteoAsistencia() {
        console.writeln("CONTEO DE ASISTENCIA:");
        console.writeln("Lydia tiene " + lydia.getCantidadNiños() + " niños en cola");
        console.writeln("Aisha tiene " + aisha.getCantidadNiños() + " niños en cola");
        console.writeln("Dalsy tiene " + dalsy.getCantidadNiños() + " niños en cola");
        int total = lydia.getCantidadNiños() + aisha.getCantidadNiños() + dalsy.getCantidadNiños();
        console.writeln("Total: " + total + " niños");
    }

    public void edadPromedio() {
        if (aisha.getCantidadNiños() == 0) {
            console.writeln("No hay niños en la cola de Aisha");
        } else {
            double promedio = aisha.calcularEdadPromedio();
            console.writeln("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", promedio) + " años");
        }
    }

    public void juegoDeLaRana() {
        console.writeln("Verificando condiciones para el juego de la rana...");
        int total = aisha.getCantidadNiños();
        int mayores = aisha.contarMayoresDe(5);
        console.writeln("Total de niños: " + total);
        console.writeln("Niños de 5 años o más: " + mayores);
        if (mayores > total / 2) {
            console.writeln("¡Más de la mitad cumplen la condición!");
            console.writeln("¡Pueden jugar al juego de la rana!");
        } else {
            console.writeln("No hay suficientes niños mayores de 5 años");
            console.writeln("No pueden jugar todavía");
        }
    }

    public void separarMenoresDeCinco() {
        console.writeln("Separando niños para el juego de la rana...");
        List<Niño> menores = aisha.extraerMenoresDe(5);
        if (menores.isEmpty()) {
            console.writeln("No hay niños menores de 5 años para transferir.");
        } else {
            console.writeln("Niños menores de 5 años pasan a Dalsy:");
            for (Niño n : menores) {
                dalsy.recibeNiño(n);
                console.writeln("- " + n.getNombre() + " (" + n.getEdad() + " años)");
            }
        }
        console.writeln("\nNiños que se quedan con Aisha para jugar:");
        aisha.mostrarNiños();
    }

    public void alarmaIncendios() {
        console.writeln("¡ALARMA CONTRA INCENDIOS!\n");
        console.writeln("PROTOCOLO DE EMERGENCIA ACTIVADO\n");
        int totalTransferidos = 0;
        totalTransferidos += dalsy.transferirTodos(lydia);
        totalTransferidos += aisha.transferirTodos(lydia);
        console.writeln(totalTransferidos + " niños transferidos");
        console.writeln("Lydia ahora tiene " + lydia.getCantidadNiños() + " niños listos para evacuar en orden");
    }

    public void mostrarEstado() {
        console.writeln("========================================");
        console.writeln("        ESTADO ACTUAL");
        console.writeln("========================================");
        lydia.mostrarEstado();
        aisha.mostrarEstado();
        dalsy.mostrarEstado();
        console.writeln("========================================");
    }
}
