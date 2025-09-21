# 📞 Teléfono Descacharrado

Las monitoras **Lydia** y **Aisha** han organizado una ludoteca a la que asisten varios niños, entre ellos varios sobrinos de Lydia.

Entre los juegos, tienen una versión muy particular del popular *Teléfono Descacharrado*. Las reglas son las siguientes:

---

## 1. Organización inicial

* Al abrir la ludoteca, **Lydia** recibe a los niños.
* Conforme van llegando, se los pasa a **Aisha**, quien:

  * Forma una fila colocando a cada nuevo niño al final.
  * Entrega a cada niño un **pizarrín**.

---

## 2. Inicio del juego

Cuando la fila tiene **más de 5 niños**, Aisha da comienzo al juego:

1. **Preparación**

   * Limpia la pizarra del salón.
   * Pide a los niños que limpien sus pizarrines.

2. **Transmisión del mensaje**

   * Escribe un **texto de 10 letras** en un pizarrín y se lo muestra al **primer niño de la cola**.
   * El primer niño lo copia en su pizarrín y se lo muestra al siguiente.
   * El proceso se repite hasta llegar al **último niño**.

3. **Finalización**

   * El último niño corre a la pizarra del salón y escribe allí el mensaje recibido.

---

## 3. Distorsión del mensaje

Los niños están tan emocionados (es sábado, están con sus tías favoritas y no piensan en exámenes 😄) que, al copiar, a veces cambian **una o dos letras**.

* Resultado: el mensaje se **va deformando progresivamente**.
* Cuantos más niños haya en la fila, **mayor será la distorsión**.

---

## 4. Llegada de los niños

El ritmo de llegada es el siguiente:

* **Primeros 10 minutos**: llegan entre **0 y 2 niños por minuto**.
* **Siguientes 20 minutos**: puede llegar **1 niño cada 3 minutos** (con un **50% de probabilidad**).
* **Después de 30 minutos**: ya no llegan más niños.

📌 Nota: si un juego está en curso, los nuevos niños se quedan con Lydia, quien los sienta y los pasa a la fila **solo cuando termine el juego actual**.

---

## 5. Duración de las acciones

* Cada niño tarda **1 minuto** en leer el mensaje y copiarlo.
* También se contabiliza **1 minuto inicial** (cuando Aisha muestra el mensaje) y **1 minuto final** (cuando el último niño escribe en la pizarra del salón).

---

## 6. Retos de la simulación

La ludoteca está abierta durante **2 horas**. El juego resulta tan divertido que los niños **juegan sin parar durante todo ese tiempo**.

Los retos a resolver son:

1. Simular el paso del mensaje entre los niños.
2. Implementar la deformación progresiva del mensaje.

---

## 7. Entregables

* **Obligatorio**: código fuente del programa.
* **Optativo**: cualquier otro artefacto adicional.

## 8. Documentación Adicional

* **[Desarrollo del Proyecto](desarrollo_proyecto.md)**: Documento que explica el proceso de construcción del proyecto.
* **[Diagrama de Clases](../documentosUML/diagrama_clases.md)**: Diagrama UML de clases que muestra la estructura estática del proyecto.
* **[Diagrama de Workflow](../documentosUML/diagrama_workflow.md)**: Diagrama de secuencia que ilustra el flujo dinámico de la simulación.