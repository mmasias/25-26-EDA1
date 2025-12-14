# Uroboro: La serpiente que se muerde la cola

## Contexto

El **uroboro** es un símbolo antiguo que representa una serpiente o dragón que se muerde su propia cola, formando un círculo. En el contexto de estructuras de datos, representa una **dependencia circular completa** donde:

- Una **Lista** se implementa usando un **Array**
- Ese **Array** se implementa internamente usando una **Lista**

Este ejercicio lleva al límite los conceptos de **abstracción** e **independencia entre interfaz e implementación**.

## El círculo cerrado

<div align=center>

|Escenario 1|Escenario 2|
|:-:|:-:|
|Lista|Array|
*↓ usa*|*↓ usa*
Array (arrayConLista)|Lista (listaConArray)
*↓ usa*|*↓ usa*
Lista enlazada (con nodos)|Array de nodos (int[][])

</div>

## 🚬

### Parte 1: Sobre la abstracción

1. Si una Lista usa un Array que internamente es una Lista, ¿cuál es la "lista real"?
2. ¿En qué momento una abstracción se convierte en la "implementación base"?
3. ¿Puede existir una estructura de datos sin una implementación "primitiva" en algún nivel?
4. ¿Qué revela esto sobre la distinción entre "ser" y "actuar como"?

### Parte 2: Sobre las capas de indirección

1. ¿Cuántas conversiones de datos ocurren al ejecutar `lista.agregar(100)` en el uroboro?
2. ¿Conlleva algún coste esta cadena de abstracciones?
3. Si el comportamiento externo es idéntico, ¿importa la implementación interna?
4. ¿En qué punto la "pureza conceptual" entra en conflicto con la eficiencia?

### Parte 3: Reflexión final

1. ¿Es esto útil en la práctica o solo un ejercicio intelectual?
2. ¿Qué problema del mundo real podría resolverse con múltiples capas de abstracción?
3. ¿Hasta qué punto la complejidad oculta es aceptable si la interfaz es simple?
4. ¿Qué dice este ejercicio sobre los fundamentos de la programación orientada a objetos?

## El bootstrap problem

El uroboro plantea un **problema de inicialización**:

- Para crear una Lista, necesito un Array
- Para crear un Array, necesito una Lista
- ¿Cuál creo primero?

**Solución**: Cada implementación usa la del "otro ejercicio":
- `uroboros.lista` usa `arrayConLista.a000.Array`
- `uroboros.array` usa `listaConArray.l001.Lista`

Estas implementaciones base, a su vez, usan estructuras primitivas del lenguaje (arrays nativos de Java), rompiendo el círculo en el nivel más bajo.

## La lección

**Las estructuras de datos son abstracciones conceptuales, no entidades físicas.**

Lo que define a una estructura no es su implementación interna, sino:
- Su **contrato público** (qué operaciones ofrece)
- Sus **garantías semánticas** (qué promete sobre su comportamiento)
- Su **interfaz** (cómo se usa desde fuera)

El uroboros demuestra que la misma abstracción puede construirse de infinitas formas diferentes, y todas son igualmente válidas... aunque no igualmente eficientes.

---

> *"La realidad es aquello que, cuando dejas de creer en ello, no desaparece."* — Philip K. Dick

En estructuras de datos, la "realidad" es el contrato, no la implementación.
