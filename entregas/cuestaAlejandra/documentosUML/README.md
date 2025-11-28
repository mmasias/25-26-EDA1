# Explicación Sencilla del UML – Simulación RCCCF

Este sistema tiene **3 clases principales** que trabajan juntas para simular un restaurante donde el cocinero siempre prepara primero el pedido más rápido.

---

## 1. `Pedido`
Representa **un plato que pide un cliente**.

- **Atributos** (datos que tiene):
  - `tipo`: nombre del plato (ej. "Café", "Ensalada").
  - `tiempoPreparacion`: cuántos minutos tarda en prepararse.
  - `tiempoRestante`: cuánto falta para terminarlo.
  - `minutoLlegada`: en qué minuto llegó el pedido.
- **Método** (acción que sabe hacer):
  - `toString()`: muestra el pedido como texto (ej. `"Café (2 min)"`).

> Cada vez que llega un cliente, se crea un nuevo `Pedido`.

---

## 2. `Restaurante`
Es el **encargado de gestionar todo** durante la simulación.

- **Atributos**:
  - Lista de `Pedido` que están esperando (`cola`).
  - El `Pedido` que está cocinando ahora (`cocinando`).
  - Contadores: cuántos pedidos se atendieron, tiempo de espera, etc.
- **Métodos**:
  - `avanzarMinuto()`: simula lo que pasa cada minuto (llegan pedidos, el cocinero trabaja, etc.).
  - `cerrar()`: al final del día, suma el tiempo de espera de los pedidos no atendidos.

> El restaurante **busca manualmente** el pedido más rápido entre los que están en la cola.

---

## 3. `Simulation`
Es la **clase principal** que **inicia todo**.

- No tiene datos.
- Tiene un solo método:
  - `main()`: crea el restaurante y hace correr la simulación minuto a minuto (120 minutos).

> Es como el "botón de inicio" del programa.

---

## ¿Cómo se relacionan?

- `Simulation` **usa** a `Restaurante`.
- `Restaurante` **guarda y maneja** muchos objetos de tipo `Pedido`.
- No hay relaciones complicadas: todo es **simple y directo**.

---

Este diseño es ideal para aprender, porque:
- Usa solo lo básico (clases, atributos, métodos).
- No necesita estructuras avanzadas.