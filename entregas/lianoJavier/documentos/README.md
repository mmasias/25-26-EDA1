# Documentación Técnica: Lista de Enteros

## 1. Diseño Orientado a Objetos (HOOD)

### Diagrama de Clases

Representación estática de la estructura del sistema y las relaciones entre sus componentes.

```mermaid
classDiagram
    class Principal {
        +main(args : String[]) void
    }

    class Lista {
        -elementos : Array
        -cantidadElementos : int
        -TAMANIO_INICIAL : int
        +Lista()
        +getCantidadElementos() : int
        +insertar(valor : int, posicion : int) void
        +eliminar(posicion : int) void
        +obtener(posicion : int) int
        -expandir() void
        +obtenerTexto() : String
        +obtenerTextoPosicionesValidas() : String
    }

    class Array {
        +Array(capacidad : int)
        +longitud() : int
        +get(indice : int) : int
        +set(indice : int, valor : int) void
    }

    Principal ..> Lista : usa
    Lista *-- Array : compone
```

## 2. Vista Pública y Colaboraciones

Resumen de responsabilidades y colaboraciones entre las clases del sistema.

| Clase         | Responsabilidad                                                                                                                                     | Colabora con          |
| :------------ | :-------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------- |
| **Principal** | Gestionar la interacción con el usuario (E/S), validar entradas básicas y coordinar el flujo del programa.                                          | `Lista` (Uso)         |
| **Lista**     | Gestionar la lógica de negocio de la estructura de datos: almacenamiento, inserción ordenada (física), eliminación y acceso a elementos abstractos. | `Array` (Composición) |
| **Array**     | Proveer una estructura de almacenamiento de tamaño fijo y acceso directo (simulación de memoria primitiva).                                         | -                     |

## 3. Diagrama de Secuencia

Interacción de objetos para la operación de **Insertar**.

```mermaid
sequenceDiagram
    participant User
    participant P as Principal
    participant L as Lista
    participant A as Array (Interno)

    User->>P: 1. Elegir opción Insertar
    P->>L: obtenerTextoPosicionesValidas()
    L-->>P: "0, 1, ... n"
    P->>User: Muestra posiciones
    User->>P: Ingresa valor y posición
    P->>L: insertar(valor, pos)
    alt Capacidad llena
        L->>L: expandir()
    end
    L->>L: assert(pos valida)
    loop Desplazamiento
        L->>A: get(i)
        L->>A: set(i+1, val)
    end
    L->>A: set(pos, valor)
    L-->>P: void
    P->>User: Confirma/Vuelve al menú
```
