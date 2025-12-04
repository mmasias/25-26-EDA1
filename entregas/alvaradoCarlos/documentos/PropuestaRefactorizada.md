# Refactorización de Propuesta de RCCCF - Reto 005

## Minimalismo

Considerando lo comentado en clase, la propuesta realizada anteriormete con [MinHeap](/entregas/alvaradoCarlos/documentos/README.md) puede verse muy compleja y "adelantada" a los conocimientos actuales, por lo que decidí regresar el enfoque a lo primero que tenia en mente al realizar el reto, hacerlo únicamente con árboles binarios y un recorrido en ***InOrden***.

Básicamente con este enfoque se simplifica el proceso:

- Si los nuevos pedidos son más rápidos van a la izquierda del nodo, y si son más lentos van a la derecha del nodo.

- El pedido más rápido SIEMPRE es el nodo que está más a la izquierda de todos.

- El cocinero gracias a su prioridad irá tomando las ordenes más pequeñas, por lo que recorrerá en ***InOrden*** el árbol y obtendrá la lista de preparación correcta.

## Estructura

La propuesta de estructuración se vé de esta forma:

```mermaid
graph TD
    subgraph "Propuesta: Árbol Binario"
    Root((Café<br>3 min))
    
    Root -->|Menores| L((Bebida<br>1 min))
    Root -->|Mayores| R((Ensalada<br>8 min))
    
    L --> LR((Pincho<br>0.5 min))
    L --> LR1((Café<br>2 min))
    R --> RL((Bocadillo<br>5 min))
    R --> RL1((Estofado<br> 15 min))
    end

    style LR fill:#d4edda,stroke:#28a745,stroke-width:4px
    note[<b>El Cocinero busca aquí</b><br>El nodo más a la izquierda] -.-> LR
```

Esta era la propuesta anterior:

```mermaid
flowchart TD
    subgraph "Algoritmo Min-Heap (Anterior)"
    A1[Inicio] --> A2{¿Coger Mínimo?}
    A2 --> A3[<b>Sacar Raíz</b><br>Posición 0]
    A3 --> A4[Mover Último a Raíz]
    A4 --> A5[<b>Hundir </b><br>Intercambiar con hijos<br>hasta encajar]
    end

    style A5 fill:#f9f,stroke:#333,stroke-dasharray: 5 5
```

Y la evolución de mi nueva propuesta es asi:

```mermaid
flowchart TD
    subgraph "Algoritmo BST (Actual)"
    B1[Inicio] --> B2{¿Coger Mínimo?}
    B2 --> B3[Empezar en Raíz]
    B3 --> B4{¿Hay hijo Izq?}
    B4 -- Sí --> B5[Bajar a la Izquierda]
    B5 --> B4
    B4 -- No --> B6[<b>ENCONTRADO</b><br>Este es el mínimo]
    B6 --> B7[Extraer y conectar<br>su hijo derecho al abuelo]
    end
    
    style B5 fill:#9f9,stroke:#333,stroke-width:2px
```

## Clases

El cambio de clases no será tan significativo, únicamente eliminaremos la clase de estructura del MinHeap y crearemos la clase de la estructura del Árbol Binario, más la estructura de cada Nodo para el árbol. Quedando el diagrama de clases de esta forma:

```mermaid
classDiagram
    direction TB

    class Restaurante {
        +main()
        +run()
    }

    class Cocina {
        -arbol: ArbolBinario
        -enProceso: Pedido
        +recibir(Pedido)
        +trabajar()
        +getEstado() String
    }

    class ArbolBinario {
        -raiz: NodoArbol
        +insertar(Pedido)
        +extraerMasRapido() Pedido
    }

    class NodoArbol {
        +pedido: Pedido
        +izq: NodoArbol
        +der: NodoArbol
        +NodoArbol(Pedido)
    }

    class Pedido {
        +nombre: String
        +tiempo: int
        +esMasRapidoQue(Pedido) boolean
        +cocinar()
    }

    class TipoPlato {
        +nombre: String
        +min: int
        +max: int
        +calcularTiempoReal() int
    }

    Restaurante --> Cocina
    Restaurante ..> Pedido
    Restaurante ..> TipoPlato
    
    Cocina *-- ArbolBinario
    Cocina o-- Pedido
    
    ArbolBinario --> NodoArbol
    NodoArbol --> NodoArbol
    NodoArbol *-- Pedido
```

