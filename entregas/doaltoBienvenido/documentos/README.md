# Explicación del proyecto: Teléfono Escacharrado

Este proyecto simula el juego del **teléfono escacharrado** en una ludoteca, siguiendo las reglas y requisitos del enunciado. A continuación se explica el propósito y funcionamiento de cada clase, así como la lógica general del código.

## Clases principales

### `TelefonoEscacharrado`
- Clase principal que gestiona todo el ciclo del juego.
- Controla la llegada de los niños, el inicio de cada partida y el paso del mensaje.
- Usa constantes `final` para evitar números mágicos y facilitar la comprensión del código.
- Utiliza una cola de niños (`ColaNiños`), un array de niños en espera y un array de nombres para asignar a los nuevos niños.
- El método `iniciarJuego()` simula minuto a minuto la llegada de niños y el desarrollo de las partidas.
- Cuando hay suficientes niños en la cola, se inicia el juego, se limpia la pizarra y los pizarrines, y se pasa el mensaje deformándolo según las reglas.

### `ColaNiños`
- Implementa una cola circular básica usando un array de objetos `Niño`.
- Permite añadir niños, obtener el tamaño de la cola, obtener todos los niños en la cola y limpiar la cola.
- No utiliza colecciones avanzadas, solo arrays y contadores.

### `Niño`
- Representa a cada niño participante.
- Cada niño tiene un nombre y un pizarrín (pizarra personal).
- Puede limpiar su pizarrín y escribir en él, deformando el mensaje con 0, 1 o 2 cambios aleatorios.
- Usa constantes para evitar números mágicos.

### `Mensaje`
- Clase utilitaria para mostrar mensajes por pantalla.
- Permite imprimir mensajes con o sin salto de línea, evitando el uso directo de `System.out.print` en el resto del código.

---

## Lógica general del código

- Al iniciar la ludoteca, los niños van llegando según las reglas de tiempo y probabilidad.
- Lydia recibe a los niños y Aisha los coloca en la cola, dándoles un pizarrín.
- Cuando hay al menos 6 niños en la cola, se inicia una partida del teléfono escacharrado.
- Se limpia la pizarra y los pizarrines, se genera un mensaje de 10 letras y se pasa de niño en niño, deformándose por la emoción del juego.
- El último niño escribe el mensaje final en la pizarra del salón.
- El proceso se repite durante las dos horas de apertura de la ludoteca.

---