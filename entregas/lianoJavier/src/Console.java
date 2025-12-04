
public class Console {
  public static int randomIntInRange(int range) {
    return (int) (Math.random() * range);
  }

  public static int randomInt(int min, int max) {
    return min + randomIntInRange(max - min + 1);
  }

  public static void imprimirln(String string) {
    imprimir(string);
    saltoDeLinea();
  }

  public static void saltoDeLinea() {
    System.out.println();
  }

  public static void imprimir(String string) {
    System.out.print(string);
  }
}
