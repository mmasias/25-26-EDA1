``` mermaid
classDiagram
    class Cliente {
        +main(String[] args)
    }

    class Menu {
        -Lista lista
        +iniciar()
    }

    class Arreglo {
        -Lista miLista
        -int capacidad
        +Arreglo(int capacidad)
        +get(int indice)
        +set(int indice, Object valor)
    }

    class Lista {
        -Nodo cabeza
        -int tamano
        +Lista()
        +agregar(Object valor)
        +insertar(Object valor, int pos)
        +obtener(int indice)
        +modificar(int indice, Object valor)
        +eliminar(int indice)
        +tamano()
        +mostrar()
    }

    class Nodo {
        -Object dato
        -Nodo siguiente
        +Nodo(Object dato)
        +getDato()
        +setDato(Object dato)
        +getSiguiente()
        +setSiguiente(Nodo siguiente)
    }

    Cliente ..> Menu : usa
    Menu --> Lista : gestiona
    Arreglo *-- Lista : compone
    Lista *-- Nodo : encadena
```