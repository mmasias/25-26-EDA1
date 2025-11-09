//import l000.Lista;
import l001.Lista;

class ClienteLista {
    
    public static void main(String[] args) {
        Lista miLista = new Lista();
        
        System.out.println("AGREGANDO ELEMENTOS");
        miLista.agregar(100);
        miLista.agregar(200);
        miLista.agregar(300);
        miLista.agregar(400);
        miLista.agregar(500);
        
        System.out.println("> Tamaño actual: " + miLista.tamaño());
        
        System.out.println();
        System.out.println("MOSTRANDO ELEMENTOS");
        for (int i = 0; i < miLista.tamaño(); i++) {
            System.out.println("> Posición " + i + ": " + miLista.obtener(i));
        }
        
        System.out.println();
        System.out.println("ELIMINANDO POSICIÓN 2");
        int posicionAEliminar = 2;
        if (posicionAEliminar >= 0 && posicionAEliminar < miLista.tamaño()) {
            miLista.eliminar(posicionAEliminar);
        }
        
        System.out.println("> Nuevo tamaño: " + miLista.tamaño());
        
        System.out.println();
        System.out.println("ELEMENTOS DESPUÉS DE ELIMINAR");
        for (int i = 0; i < miLista.tamaño(); i++) {
            System.out.println("> Posición " + i + ": " + miLista.obtener(i));
        }
        
        System.out.println();
        System.out.println("AGREGANDO MÁS ELEMENTOS");
        miLista.agregar(600);
        miLista.agregar(700);
        
        System.out.println("> Tamaño final: " + miLista.tamaño());
        
        System.out.println();
        System.out.println("ESTADO FINAL");
        for (int i = 0; i < miLista.tamaño(); i++) {
            System.out.println("> Posición " + i + ": " + miLista.obtener(i));
        }
    }
}