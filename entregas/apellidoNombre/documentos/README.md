# Propuesta de Estructuras Basadas en Listas

Para mitigar el impacto en el rendimiento, utilizaremos Listas de Objetos/Estructuras Enlazadas.

Nuestro cliente debe saber a qué asignaturas está suscrito. A su vez cada asignatura debe saber qué clientes están suscritos a ella.
Entonces para esto necesitamos dos listas globales para almacenar todos los objetos del sistema.

## Diagrama UML 

![image](../documentosUML/UML_Diagram.png))

## Justificación

Elegimos listas enlazadas para almacenar las asignaturas y los clientes debido a su simplicidad y facilidad de implementación. Esta estructura es adecuada para sistemas con un número moderado de elementos, donde las operaciones de inserción y eliminación son frecuentes. Las listas enlazadas permiten una gestión dinámica de la memoria y facilitan la adición y eliminación de elementos sin necesidad de reestructurar toda la colección.

    1. Enviar Mensaje:
        Primero, O(Nc​) para buscar la Categoría en SistemaIris.lista_categorias (Nc​: total de categorías). Luego, O(Ns​) para iterar sobre todos los clientes (Ns​: suscriptores).

    2. Suscribir Cliente:
        Requiere buscar el Cliente (O(Na​)) y la Categoría (O(Nc​)) en sus listas globales. Una vez encontrados, añadir la referencia es O(1).

    3: Desuscribir Cliente:
        Requiere buscar y eliminar la referencia en dos listas enlazadas internas: la lista de intereses del cliente (Nc_i​) y la lista de suscriptores de la categoría (Ns_c​). La eliminación en una lista es O(N) en el peor caso.
        
## Sacricifios

El principal problema  vendria con el rendimiento en sistemas con un gran número de asignaturas o clientes. Este diseño es viable solo si se espera que el número total de clientes o asignaturas sea pequeño o moderado.

## Casos Límite

    Asignaturas sin alumnos suscritos:

        Asignatura.clientes_suscriptores será una lista vacía. El coste de iteración será O(1).

    Alumnos sin asignatura inscrita:

        Cliente.Asignaturas_suscritas será una lista vacía.

    Exalumnos:

        Para eliminar un exalumno, primero hay que buscarlo en SistemaIris.lista_clientes (O(N)). Luego, se itera sobre su lista de asignaturas suscritas, y en cada asignatura, se busca y elimina al cliente de la lista de suscriptores (coste total de eliminación: O(N×C), siendo C el número de asignaturas).

    Mensajes que no provienen de una asignatura:

        Manejo: Es imposible procesar la redirección sin saber la Categoría ID para buscar el objeto Asignatura en SistemaIris.lista_asignatura (O(N)). Se requiere un ID o rechazar el mensaje.

    Mensajes no enviados:

        Se almacenan en la Asignatura.mensajes_pendientes (Lista/Cola).