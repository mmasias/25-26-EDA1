public class EjemploArrayComoLista {
    public static void main(String[] args) {
        ArrayComoLista lista = new ArrayComoLista(4);
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(40);
        lista.imprimir();
        System.out.println("Insertamos 30 en la posición 2: ");
        lista.insertar(2, 30);
        lista.imprimir();
        System.out.println("Cambiamos el valor en la posición 0 a 99: ");
        lista.establecer(0, 99);
        lista.imprimir();
        int eliminado = lista.eliminarEn(1);
        System.out.println("Elemento eliminado: " + eliminado);
        lista.imprimir();
        lista.insertar(10, 100);
        System.out.println("Valor en posición 10: " + lista.obtener(10));
    }
}
