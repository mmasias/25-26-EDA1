# PySpotify

[Enunciado](./enunciado.md)

## Propuesta

A ver, aunque Spotify en realidad es una red de nodos _---o, mejor dicho un plano N dimensional donde cada dimension representa una variable distinta a tener en cuenta para ordenar las canciones---_ como no sabemos programar eso tomaremos referencia de lo que si sabemos.

```mermaid
classDiagram
        class Anuncio {
                - int duracion

                + getDuracion() : int
        }

        class NodoAnuncio {
                - NodoAnuncio siguienteNodo
                - NodoAnuncio anteriorNodo

                - Anuncio anuncioEnlazado
        }

        class ColaDeAnuncios {
                - NodoAnuncio primerNodo

                + sacarAnuncio()
        }

        class Cancion {
                - String titulo
                - Autor autor
                - int duracion

                + getTitulo() : String
                + getAutor() : Autor
                + getDuracion() : int

                + setTitulo()
                + setAutor()
                + setDuracion()
        }

        class NodoListaCancion {
                - NodoListaCancion siguienteNodo
                - NodoListaCancion anteriorNodo

                - Cancion cancionEnlazada

                - boolean haSonado

                - int identificador
                - String titulo

                + marcarComoReproducida()
                + desmarcarComoReproducida()
        }

        class NodoListaShuffleCancion {
                - NodoListaShuffleCancion siguienteNodo
                - NodoListaShuffleCancion anteriorNodo

                - NodoListaCancion nodoCancionEnlazada
        }

        class NodoArbolCancionPorIndice {
                + buscarPorIndice(int indice) : NodoListaCancion
        }

        class NodoArbolCancionPorTitulo {
                + buscarPorTitulo(String titulo) : NodoListaCancion
        }

        class NodoArbolCancion {
                <<abstract>>
                - NodoArbolCancion izquierda
                - NodoArbolCancion derecha

                - NodoListaCancion nodoCancionEnlazada
        }

        class ColaDeReproduccion {
                - NodoListaCancion primeraCancionIndexada
                - NodoArbolCancion raizColaReproduccion

                - NodoListaCancion actual
                - int numeroTotalCanciones
                - int numeroCancionesConsumidas

                - NodoListaShuffleCancion actualShuffle;

                + insertarCancion()
                + insertarCancion(Cancion cancionAInsertar)
                // + insertarCancion(int posicion)

                + siguiente()
                + anterior()

                + sacarCancion() : Cancion

                - reiniciarCiclo()
        }

        class Spotify {
                - Cancion cancionMasRepetida
                - Time tiempoTotal

                - ColaDeReproduccion
                - ColaDeAnuncios

                - boolean esPremium
                - int contadorParaAnuncio

                + añadirAlFinal()
                + eliminarCancion(int indice)
                + eliminarCancion(Cancion cancionAEliminar)
                + siguiente()
                - reproducirAnuncio()
                + anterior()
                + modoAleatorio()
                + search(String name) : Cancion[]
                + estadisticas()
                + pagarPremium()
        }

        %% Relaciones de Herencia
        NodoArbolCancionPorIndice --|> NodoArbolCancion
        NodoArbolCancionPorTitulo --|> NodoArbolCancion

        %% Relaciones de Composición (Estructuras de datos)
        Spotify o-- ColaDeReproduccion : Gestiona
        Spotify o-- ColaDeAnuncios : Gestiona
        ColaDeAnuncios o-- NodoAnuncio : Contiene
        ColaDeReproduccion o-- NodoListaCancion : Secuencia original
        ColaDeReproduccion o-- NodoArbolCancion : Índices (ID/Título)
        ColaDeReproduccion o-- NodoListaShuffleCancion : Secuencia aleatoria

        %% Relaciones de Asociación (Punteros Quirúrgicos)
        NodoAnuncio o-- Anuncio : Referencia
        NodoListaCancion o-- Cancion : Referencia
        NodoListaShuffleCancion --> NodoListaCancion : Puntero sombra
        NodoArbolCancion --> NodoListaCancion : Puntero quirúrgico
```

### Justificación de Estructuras

#### Búsqueda e Indexación (Dual Tree)

**¿Qué he decidido?**
Uso de dos árboles de búsqueda (ID y Título) que heredan de una clase abstracta NodoArbolCancion.

**¿Por qué?**
Garantiza búsquedas en $O(\log n)$ tanto para procesos automáticos del sistema como para búsquedas manuales del usuario.

_Puntero Quirúrgico_: Cada nodo del árbol referencia directamente al NodoListaCancion. Esto permite que, tras localizar la canción, el borrado en la lista sea $O(1)$ al no tener que recorrerla para encontrar sus punteros anterior y siguiente.

#### Navegación y Shuffle (Shadow Lists)

**¿Qué he decidido?**
Uso de NodoListaShuffleCancion (Nodos Sombra).

**¿Por qué?**
Permite una navegación aleatoria bidireccional sin destruir el orden cronológico de la lista principal. Los nodos sombra son ligeros (solo punteros), cumpliendo la restricción de memoria limitada.

_Sincronización_: Al desactivar el shuffle, el puntero actual ya reside en la lista original, permitiendo retomar el orden natural instantáneamente.

#### Ciclo de Vida (Flag haSonado)

**¿Qué he decidido?**
Uso de un booleano haSonado en lugar de extracción física de nodos.

**¿Por qué?**
Mantener los nodos en la lista evita reconstruir la estructura cada vez que termina la playlist. El "reinicio" de la cola se convierte en una operación lineal simple $O(n)$ de reseteo de flags.
