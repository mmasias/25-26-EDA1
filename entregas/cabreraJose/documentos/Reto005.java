import java.util.Scanner;

public class Reto005 {
  public static final int CAPACIDAD_INICIAL = 100;

  private final Scanner input = new Scanner(System.in);
  private final GestorPedidos gestor = new GestorPedidos(CAPACIDAD_INICIAL);

  public static void main(String[] args) {
    Reto005 app = new Reto005();
    app.ejecutar();
  }

  public void ejecutar() {
    int opcion;
    do {
      mostrarMenu();
      opcion = leerEntero("Seleccione opción: ");
      procesarOpcion(opcion);
      if (opcion != 0) {
        leerCadena("Presione ENTER para continuar...");
      }
    } while (opcion != 0);
  }

  private void mostrarMenu() {
    System.out.println("\n=== GESTOR DE PEDIDOS CON PRIORITY QUEUE ===");
    System.out.println("1. Agregar nuevo pedido");
    System.out.println("2. Procesar pedido con prioridad mínima");
    System.out.println("3. Ver estado del sistema");
    System.out.println("4. Consultar próximo pedido (sin procesar)");
    System.out.println("0. Salir");
  }

  private void procesarOpcion(int opcion) {
    if (opcion == 1) {
      opcionAgregarPedido();
    } else if (opcion == 2) {
      opcionProcesarPedido();
    } else if (opcion == 3) {
      opcionVerEstado();
    } else if (opcion == 4) {
      opcionConsultarProximo();
    } else if (opcion == 0) {
      System.out.println("Saliendo del sistema...");
    } else {
      System.out.println("Opción no válida");
    }
  }

  private void opcionAgregarPedido() {
    int id = leerEntero("Ingrese ID del pedido: ");
    int costo = leerEntero("Ingrese costo del pedido: ");
    int tiempo = leerEntero("Ingrese tiempo estimado (minutos): ");
    
    Pedido nuevoPedido = new Pedido(id, costo, tiempo);
    gestor.agregarPedido(nuevoPedido);
    System.out.println("✓ Pedido " + id + " agregado a la cola de espera.");
  }

  private void opcionProcesarPedido() {
    Pedido procesado = gestor.procesarPedidoMinimo();
    if (procesado != null) {
      System.out.println("📤 Procesando: Pedido " + procesado.id + 
                         " (costo=" + procesado.costo + ", tiempo=" + procesado.tiempo + " min)");
    } else {
      System.out.println("No hay pedidos en la cola");
    }
  }

  private void opcionVerEstado() {
    System.out.println("\n=== ESTADO DEL SISTEMA ===");
    System.out.println("Pedidos pendientes: " + gestor.cantidadPendientes());
    if (gestor.cantidadPendientes() > 0) {
      System.out.println("Próximo a procesar: Pedido " + gestor.consultarMinimo().id + 
                         " (costo=" + gestor.consultarMinimo().costo + ")");
      gestor.mostrarTodos();
    } else {
      System.out.println("Cola vacía");
    }
  }

  private void opcionConsultarProximo() {
    Pedido proximo = gestor.consultarMinimo();
    if (proximo != null) {
      System.out.println("Próximo pedido a procesar:");
      System.out.println("  ID: " + proximo.id);
      System.out.println("  Costo: " + proximo.costo);
      System.out.println("  Tiempo: " + proximo.tiempo + " min");
    } else {
      System.out.println("No hay pedidos pendientes");
    }
  }

  private int leerEntero(String mensaje) {
    System.out.print(mensaje);
    while (!input.hasNextInt()) {
      input.nextLine();
      System.out.print(mensaje);
    }
    int valor = input.nextInt();
    input.nextLine();
    return valor;
  }

  private String leerCadena(String mensaje) {
    System.out.print(mensaje);
    return input.nextLine();
  }
}

class Pedido implements Comparable<Pedido> {
  public final int id;
  public final int costo;
  public final int tiempo;

  public Pedido(int id, int costo, int tiempo) {
    this.id = id;
    this.costo = costo;
    this.tiempo = tiempo;
  }

  @Override
  public int compareTo(Pedido otro) {
    return Integer.compare(this.costo, otro.costo);
  }

  @Override
  public String toString() {
    return "Pedido " + id + " (costo=" + costo + ", tiempo=" + tiempo + " min)";
  }
}

class MinHeap {
  private Pedido[] heap;
  private int tamaño;

  public MinHeap(int capacidad) {
    this.heap = new Pedido[capacidad];
    this.tamaño = 0;
  }

  public void insertar(Pedido pedido) {
    if (tamaño == heap.length) {
      redimensionar();
    }
    heap[tamaño] = pedido;
    subirHeap(tamaño);
    tamaño++;
  }

  public Pedido extraerMinimo() {
    if (tamaño == 0) return null;
    
    Pedido minimo = heap[0];
    heap[0] = heap[tamaño - 1];
    heap[tamaño - 1] = null;
    tamaño--;
    
    if (tamaño > 0) {
      bajarHeap(0);
    }
    
    return minimo;
  }

  public Pedido consultarMinimo() {
    return tamaño > 0 ? heap[0] : null;
  }

  public int tamaño() {
    return tamaño;
  }

  public void mostrarTodos() {
    if (tamaño == 0) {
      System.out.println("  Cola vacía");
      return;
    }
    System.out.println("Pedidos en cola:");
    for (int i = 0; i < tamaño; i++) {
      System.out.println("  " + (i + 1) + ". " + heap[i]);
    }
  }

  private void subirHeap(int indice) {
    Pedido actual = heap[indice];
    while (indice > 0) {
      int indicePadre = (indice - 1) / 2;
      Pedido padre = heap[indicePadre];
      if (actual.compareTo(padre) >= 0) break;
      
      heap[indice] = padre;
      indice = indicePadre;
    }
    heap[indice] = actual;
  }

  private void bajarHeap(int indice) {
    Pedido actual = heap[indice];
    int mitadTamaño = tamaño / 2;
    
    while (indice < mitadTamaño) {
      int indiceHijo = 2 * indice + 1;
      Pedido hijo = heap[indiceHijo];
      
      int indiceDerechoDer = indiceHijo + 1;
      if (indiceDerechoDer < tamaño && heap[indiceDerechoDer].compareTo(hijo) < 0) {
        indiceHijo = indiceDerechoDer;
        hijo = heap[indiceDerechoDer];
      }
      
      if (actual.compareTo(hijo) <= 0) break;
      
      heap[indice] = hijo;
      indice = indiceHijo;
    }
    heap[indice] = actual;
  }

  private void redimensionar() {
    Pedido[] nuevoHeap = new Pedido[heap.length * 2];
    for (int i = 0; i < tamaño; i++) {
      nuevoHeap[i] = heap[i];
    }
    heap = nuevoHeap;
  }
}

class GestorPedidos {
  private final MinHeap colaPedidos;

  public GestorPedidos(int capacidad) {
    this.colaPedidos = new MinHeap(capacidad);
  }

  public void agregarPedido(Pedido pedido) {
    colaPedidos.insertar(pedido);
  }

  public Pedido procesarPedidoMinimo() {
    return colaPedidos.extraerMinimo();
  }

  public Pedido consultarMinimo() {
    return colaPedidos.consultarMinimo();
  }

  public int cantidadPendientes() {
    return colaPedidos.tamaño();
  }

  public void mostrarTodos() {
    colaPedidos.mostrarTodos();
  }
}
