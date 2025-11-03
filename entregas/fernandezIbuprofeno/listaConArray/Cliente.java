//import l000.Lista;
import l001.Lista;

class Cliente {
    
    public static void main(String[] args) {
        Lista lista = new Lista();
        
        System.out.println("AGREGANDO ELEMENTOS");
        lista.agregar(100);
        lista.agregar(200);
        lista.agregar(300);
        lista.agregar(400);
        lista.agregar(500);
        
        System.out.println("> Tamaño actual: " + lista.tamaño());
        
        System.out.println();
        System.out.println("MOSTRANDO ELEMENTOS");
        for (int i = 0; i < lista.tamaño(); i++) {
            System.out.println("> Posición " + i + ": " + lista.obtener(i));
        }
        
        System.out.println();
        System.out.println("ELIMINANDO POSICIÓN 2");
        int posicionAEliminar = 2;
        if (posicionAEliminar >= 0 && posicionAEliminar < lista.tamaño()) {
            lista.eliminar(posicionAEliminar);
        }
        
        System.out.println("> Nuevo tamaño: " + lista.tamaño());
        
        System.out.println();
        System.out.println("ELEMENTOS DESPUÉS DE ELIMINAR");
        for (int i = 0; i < lista.tamaño(); i++) {
            System.out.println("> Posición " + i + ": " + lista.obtener(i));
        }
        
        System.out.println();
        System.out.println("AGREGANDO MÁS ELEMENTOS");
        lista.agregar(600);
        lista.agregar(700);
        
        System.out.println("> Tamaño final: " + lista.tamaño());
        
        System.out.println();
        System.out.println("ESTADO FINAL");
        for (int i = 0; i < lista.tamaño(); i++) {
            System.out.println("> Posición " + i + ": " + lista.obtener(i));
        }
    }
}