package entregas.cabreraJose;

import java.util.Scanner;

public class Reto002 {
  public static final int MAX_QUEUE_SIZE = 200;
  public static final int REQUIRED_MINIMUM_FOR_GAME = 5;

  private final Scanner inputReader = new Scanner(System.in);
  private final Ludoteca ludoteca = new Ludoteca();

  public static void main(String[] args) {
    Reto002 app = new Reto002();
    app.ejecutarSimulacion();
  }

  public void ejecutarSimulacion() {
    int opcion;
    do {
      mostrarMenu();
      opcion = leerEntero("Seleccione una opción: ");
      procesarOpcion(opcion);
      if (opcion != 0) {
        leerCadena("Presione ENTER para continuar...");
      }
    } while (opcion != 0);
  }

  private void mostrarMenu() {
    System.out.println("LUDOTECA - SIMULACIÓN");
    System.out.println("1. Simular llegada de niño");
    System.out.println("2. Simular intento de inicio de juego");
    System.out.println("3. Aisha se presenta y pide a los niños que se presenten");
    System.out.println("4. Aisha pide que se presenten los niños mayores de X años");
    System.out.println("5. Aisha pide que se presenten los niños cuyo nombre empieza por letra");
    System.out.println("6. Aisha pide que se presenten los cinco primeros niños");
    System.out.println("7. Aisha pide que se presenten los cinco últimos niños");
    System.out.println("8. Aisha y Lydia dicen cuántos niños hay en cola");
    System.out.println("9. Aisha dice la edad promedio de los niños en cola");
    System.out.println("10. Simular intento de inicio del juego de la rana");
    System.out.println("11. Paso de niños menores de 5 años a monitora Daisy");
    System.out.println("12. Alarma contra incendios y protocolo de emergencia");
    System.out.println("13. Mostrar monitoras y niños");
    System.out.println("0. Salir");
  }

  private void procesarOpcion(int opcion) {
    if (opcion == 1) {
      String nombre = leerCadena("Nombre del niño: ");
      int edad = leerEntero("Edad del niño: ");
      Niño nuevo = new Niño(nombre, edad);
      ludoteca.lydia.recibirNiño(nuevo);
    } else if (opcion == 2) {
      ludoteca.lydia.intentarIniciarJuegoHacia(ludoteca.aisha);
    } else if (opcion == 3) {
      ludoteca.aisha.presentarseYSolicitarPresentaciones();
    } else if (opcion == 4) {
      int edadMinima = leerEntero("Edad mínima: ");
      ludoteca.aisha.presentarMayoresDe(edadMinima);
    } else if (opcion == 5) {
      String texto = leerCadena("Letra inicial: ");
      char inicial = texto.length() > 0 ? Character.toUpperCase(texto.charAt(0)) : ' ';
      ludoteca.aisha.presentarPorInicial(inicial);
    } else if (opcion == 6) {
      ludoteca.aisha.presentarPrimerosCinco();
    } else if (opcion == 7) {
      ludoteca.aisha.presentarUltimosCinco();
    } else if (opcion == 8) {
      ludoteca.aisha.decirConteoCon(ludoteca.lydia, ludoteca.daisy);
    } else if (opcion == 9) {
      ludoteca.aisha.decirEdadPromedio();
    } else if (opcion == 10) {
      ludoteca.aisha.verificarJuegoRana();
    } else if (opcion == 11) {
      ludoteca.aisha.pasarMenoresDeCincoADaisy(ludoteca.daisy);
    } else if (opcion == 12) {
      ludoteca.daisy.protocoloEmergenciaHacia(ludoteca.lydia);
      ludoteca.aisha.protocoloEmergenciaHacia(ludoteca.lydia);
    } else if (opcion == 13) {
      ludoteca.mostrarEstado();
    } else if (opcion == 0) {
      System.out.println("Saliendo...");
    } else {
      System.out.println("Opción no válida");
    }
  }

  private int leerEntero(String mensaje) {
    System.out.print(mensaje);
    while (!inputReader.hasNextInt()) {
      inputReader.nextLine();
      System.out.print(mensaje);
    }
    int valor = inputReader.nextInt();
    inputReader.nextLine();
    return valor;
  }

  private String leerCadena(String mensaje) {
    System.out.print(mensaje);
    return inputReader.nextLine();
  }
}

class Ludoteca {
  public final Lydia lydia = new Lydia("Lydia", Reto002.MAX_QUEUE_SIZE);
  public final Aisha aisha = new Aisha("Aisha", Reto002.MAX_QUEUE_SIZE);
  public final Daisy daisy = new Daisy("Daisy", Reto002.MAX_QUEUE_SIZE);

  public void mostrarEstado() {
    System.out.println("ESTADO ACTUAL");
    lydia.mostrarCola();
    aisha.mostrarCola();
    daisy.mostrarCola();
  }
}

class Monitor {
  public final String nombre;
  private final Niño[] cola;
  private int inicio;
  private int fin;
  private int cantidad;

  public Monitor(String nombre, int capacidad) {
    this.nombre = nombre;
    this.cola = new Niño[capacidad];
    this.inicio = 0;
    this.fin = 0;
    this.cantidad = 0;
  }

  public boolean encolar(Niño niño) {
    if (cantidad == cola.length) return false;
    cola[fin] = niño;
    fin = (fin + 1) % cola.length;
    cantidad++;
    return true;
  }

  public Niño desencolar() {
    if (cantidad == 0) return null;
    Niño niño = cola[inicio];
    cola[inicio] = null;
    inicio = (inicio + 1) % cola.length;
    cantidad--;
    return niño;
  }

  public int tamano() {
    return cantidad;
  }

  public Niño enPosicion(int index) {
    if (index < 0 || index >= cantidad) return null;
    int pos = (inicio + index) % cola.length;
    return cola[pos];
  }

  public void mostrarCola() {
    System.out.println(nombre.toUpperCase());
    if (cantidad == 0) {
      System.out.println("Cola vacía");
      return;
    }
    System.out.println("Niños en cola: " + cantidad);
    for (int i = 0; i < cantidad; i++) {
      Niño n = enPosicion(i);
      System.out.println("- " + n.nombre + " " + n.edad + " años");
    }
  }
}

class Lydia extends Monitor {
  public Lydia(String nombre, int capacidad) {
    super(nombre, capacidad);
  }

  public void recibirNiño(Niño niño) {
    boolean ok = encolar(niño);
    if (ok) {
      System.out.println("Llega " + niño.nombre + " (" + niño.edad + " años). " + niño.nombre + " pasa a la cola de Lydia");
    } else {
      System.out.println("La cola de Lydia está llena. No se puede agregar al niño.");
    }
  }

  public void intentarIniciarJuegoHacia(Aisha aisha) {
    if (tamano() >= Reto002.REQUIRED_MINIMUM_FOR_GAME) {
      System.out.println("Hay suficientes niños, Lydia transfiere sus niños a Aisha");
      System.out.println("Lista de niños transferidos:");
      int cantidad = tamano();
      for (int i = 0; i < cantidad; i++) {
        Niño n = desencolar();
        if (n != null) {
          System.out.println("- " + n.nombre + " (" + n.edad + " años)");
          aisha.encolar(n);
        }
      }
    } else {
      System.out.println("No hay suficientes niños para iniciar el juego");
      System.out.println("Se necesitan al menos " + Reto002.REQUIRED_MINIMUM_FOR_GAME + " niños");
    }
  }
}

class Aisha extends Monitor {
  public Aisha(String nombre, int capacidad) {
    super(nombre, capacidad);
  }

  public void presentarseYSolicitarPresentaciones() {
    if (tamano() == 0) {
      System.out.println("No hay niños en la cola de Aisha");
      return;
    }
    System.out.println("Aisha: Hola, soy Aisha, monitora de esta ludoteca");
    for (int i = 0; i < tamano(); i++) {
      Niño n = enPosicion(i);
      System.out.println("Hola, soy " + n.nombre + " y tengo " + n.edad + " años");
    }
  }

  public void presentarMayoresDe(int edadMinima) {
    if (tamano() == 0) {
      System.out.println("No hay niños en la cola de Aisha");
      return;
    }
    System.out.println("Aisha pide que se presenten los mayores de " + edadMinima + " años");
    for (int i = 0; i < tamano(); i++) {
      Niño n = enPosicion(i);
      if (n.edad >= edadMinima) {
        System.out.println("Hola, soy " + n.nombre + " y tengo " + n.edad + " años");
      }
    }
  }

  public void presentarPorInicial(char inicial) {
    if (tamano() == 0) {
      System.out.println("No hay niños en la cola de Aisha");
      return;
    }
    System.out.println("Aisha pide que se presenten los niños cuyo nombre empieza con " + inicial);
    for (int i = 0; i < tamano(); i++) {
      Niño n = enPosicion(i);
      if (n.nombre != null && n.nombre.length() > 0) {
        char primera = Character.toUpperCase(n.nombre.charAt(0));
        if (primera == inicial) {
          System.out.println("Hola, soy " + n.nombre);
        }
      }
    }
  }

  public void presentarPrimerosCinco() {
    if (tamano() == 0) {
      System.out.println("No hay niños en la cola de Aisha");
      return;
    }
    System.out.println("Aisha pide que se presenten los primeros 5 niños");
    int limite = tamano() < 5 ? tamano() : 5;
    for (int i = 0; i < limite; i++) {
      Niño n = enPosicion(i);
      System.out.println("Hola, soy " + n.nombre);
    }
  }

  public void presentarUltimosCinco() {
    if (tamano() == 0) {
      System.out.println("No hay niños en la cola de Aisha");
      return;
    }
    System.out.println("Aisha pide que se presenten los últimos 5 niños");
    int tam = tamano();
    int inicio = tam - 5;
    if (inicio < 0) inicio = 0;
    for (int i = inicio; i < tam; i++) {
      Niño n = enPosicion(i);
      System.out.println("Hola, soy " + n.nombre);
    }
  }

  public void decirConteoCon(Lydia lydia, Daisy daisy) {
    int l = lydia.tamano();
    int a = this.tamano();
    int d = daisy.tamano();
    System.out.println("CONTEO DE ASISTENCIA");
    System.out.println("Lydia tiene " + l + " niños en cola");
    System.out.println("Aisha tiene " + a + " niños en cola");
    System.out.println("Daisy tiene " + d + " niños en cola");
    System.out.println("Total " + (l + a + d) + " niños");
  }

  public void decirEdadPromedio() {
    int tam = tamano();
    if (tam == 0) {
      System.out.println("No hay niños en la cola de Aisha");
      return;
    }
    int suma = 0;
    for (int i = 0; i < tam; i++) {
      suma += enPosicion(i).edad;
    }
    double promedio = ((double) suma) / tam;
    System.out.println("Edad promedio de los niños en la cola de Aisha: " + String.format("%.1f", promedio) + " años");
  }

  public void verificarJuegoRana() {
    int total = tamano();
    System.out.println("Verificando condiciones para el juego de la rana...");
    System.out.println("Total de niños: " + total);
    int mayoresOIgualCinco = 0;
    for (int i = 0; i < total; i++) {
      if (enPosicion(i).edad >= 5) {
        mayoresOIgualCinco++;
      }
    }
    System.out.println("Niños de 5 años o más: " + mayoresOIgualCinco);
    if (total > 0 && mayoresOIgualCinco > total / 2) {
      System.out.println("¡Más de la mitad cumplen la condición! ¡Pueden jugar al juego de la rana!");
    } else {
      System.out.println("No hay suficientes niños mayores de 5 años");
      System.out.println("No pueden jugar todavía");
    }
  }

  public void pasarMenoresDeCincoADaisy(Daisy daisy) {
    int total = tamano();
    System.out.println("Separando niños para el juego de la rana...");
    System.out.println("Niños menores de 5 años pasan a Daisy:");
    for (int i = 0; i < total; i++) {
      Niño n = enPosicion(i);
      if (n.edad < 5) {
        System.out.println("- " + n.nombre + " " + n.edad + " años");
      }
    }
    System.out.println("Niños que se quedan con Aisha para jugar:");
    for (int i = 0; i < total; i++) {
      Niño n = enPosicion(i);
      if (n.edad >= 5) {
        System.out.println("- " + n.nombre + " " + n.edad + " años");
      }
    }
    System.out.println("NOTA: Al terminar el juego, los niños volverán a su posición original");
  }

  public void protocoloEmergenciaHacia(Lydia lydia) {
    System.out.println("Aisha transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
    int cantidad = tamano();
    int transferidos = 0;
    for (int i = 0; i < cantidad; i++) {
      Niño n = desencolar();
      if (n != null) {
        lydia.encolar(n);
        transferidos++;
      }
    }
    System.out.println(transferidos + " niños transferidos");
  }
}

class Daisy extends Monitor {
  public Daisy(String nombre, int capacidad) {
    super(nombre, capacidad);
  }

  public void protocoloEmergenciaHacia(Lydia lydia) {
    System.out.println("Daisy transfiere TODOS sus niños a Lydia INMEDIATAMENTE");
    int cantidad = tamano();
    int transferidos = 0;
    for (int i = 0; i < cantidad; i++) {
      Niño n = desencolar();
      if (n != null) {
        lydia.encolar(n);
        transferidos++;
      }
    }
    System.out.println(transferidos + " niños transferidos");
  }
}

class Niño {
  public final String nombre;
  public final int edad;

  public Niño(String nombre, int edad) {
    this.nombre = nombre;
    this.edad = edad;
  }
}
