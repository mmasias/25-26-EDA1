@startuml
class Cliente {
    - static Console console
    + static void main(String[] args)
}

class CustomArray {
    - static Console console
    - int tamaño
    - ListaEnteros elementos
    + CustomArray(int tamaño)
    + void mostrarArray()
    + void mostrarElemento(int posicion)
    + void modificarElemento(int posicion, int nuevoValor)
    + void eliminarElemento(int posicion)
    + int getTamaño()
}

class ListaEnteros {
    - int[] elementos
    - int cantidadElementos
    - static final int TAMANIO_INICIAL = 1
    + ListaEnteros()
    + int getCantidadElementos()
    + void insertar(int valor, int posicion)
    + void eliminar(int posicion)
    + int obtener(int posicion)
    + void mostrar()
    - void expandir()
    + void mostrarPosicionesValidas()
}

Cliente --> CustomArray : usa
CustomArray --> ListaEnteros : compone

@enduml