## Sobre esta solución

### Cambios clave

- **Solo 4 clases**: `Pedido`, `Nodo`, `ArbolPedidos` y `SimuladorRCCCF`.
- **Sin recursividad**: el árbol se maneja con bucles (`while`)
- **El cocinero no es un objeto**: solo uso dos variables (`pedidoEnProceso` y `tiempoRestante`).
- **El tiempo de espera se suma cada minuto**, no se calcula al final. Así se cuenta todo el tiempo que el pedido está en el sistema.
- **El árbol solo guarda los pedidos que esperan**: el que se está cocinando está fuera del árbol.
- **Nada de programación defensiva innecesaria**: confío en que el código se usa bien (por ejemplo, no extraigo mínimo si el árbol está vacío).
