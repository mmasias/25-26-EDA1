# Comentarios y reflexiones

Durante la revisión de los PRs del Reto 001 (Teléfono descacharrado), se realizaron comentarios específicos que revelaron patrones interesantes sobre el enfoque al resolver un problema de simulación sin conocimiento formal de estructuras de datos.

## Comentarios

### [PR #39](https://github.com/mmasias/25-26-EDA1/pull/39)

**Archivo**: `src/Aisha.java`
- **Uso indebido de ArrayList**: *"Nup: era solo con lo visto en PRG1 y PRG2... Spoiler: ArrayList no lo veremos ni en EDA1!!!"*
- **Números mágicos**: *"¿Qué es 5?"* (referencia a `fila.size() > 5`)

**Archivo**: `src/Ludoteca.java`
- **Aprobación del diseño**: *"Muy bien!"* (sobre la estructura de clases)
- **Mejores prácticas**: *"final int, no?"* (sobre `tiempoTotal = 120`)
- **Sugerencia de diseño OOP**: *"Siiii... y casi que puedes hacer: `lydia.pasarNiños(aisha)` piensalo!!!"*
- **Cuestionamiento**: *"🤔"* (sobre el método `vaciarFila()`)

### [PR #32](https://github.com/mmasias/25-26-EDA1/pull/32)

**Archivo**: `src/Juego.java`
- **Reconocimiento de actores**: *"Aisha y Lydia 😉"*
- **Interés por la limitación de recursos**: *"Esto es interesante: hay un número limitado de pizarrines... A ver como se desarrolla el código!"*
- **Clarificación de limitaciones**: *"Ahora veo que 10 y 5 son el número máximo de niños. Conviene que aquí ya quede explícito y, por otro lado, en realidad no había una limitación, no?"*
- **Sugerencias de diseño elegante**:
  ```java
  monitora2.recibeNiñosParaJugar(monitora1);
  ```
  o menos elegante pero recomendable:

  ```java
  monitora2.recibeNiñosParaJugar(monitora1.entregaNiños());
  ```
- **Preferencia por diseño**: *"o (este me gusta más): `monitora1.entregaNiños(monitora2);` Mañana recuerdame comentar esto!"*
- **Sugerencia de constantes**:
  ```java
  final int MAXIMO_NIÑOS_MONITORA1 = 10;
  ```

**Archivo**: `src/Monitora.java`
- **Aprobación**: *"👍🏼"*

### [PR #31](https://github.com/mmasias/25-26-EDA1/pull/31)

**Archivo**: `src/Estructura.md`
- **Cuestionamiento de falta de elementos**: *"¿Y las monitoras???"*

**Archivo**: `src/Cola.java`
- **Crítica de nomenclatura**: *"estructura es un nombre muy críptico... ¿Por que no `Niños`?"*

### [PR #35](https://github.com/mmasias/25-26-EDA1/pull/35)

**Archivo**: `src/Aisha.java`
- **Restricción de herramientas**: *"Con lo que hemos visto en PRG1 y PRG2. Queues y LinkedList no las vamos a ver ni en EDA1 😛"*
- **Sugerencia de nomenclatura**: `public void recibirChild(Child child)`
- **Números mágicos**: *"¿Qué es el 10?"*
- **Reflexión pedagógica importante**: *"A ver si recuerdo el ejemplo que puse hace un momento: No es lo mismo que me digan 'Estudia!' y yo diga 'Estoy estudiando!' a que me ponga a estudiar... Aquí, lo mismo. ¿Me sigues? Recuérdame que mañana lo comentemos"*

**Archivo**: `src/Ludoteca.java`
- **Aprobación**: *"👍🏼"*

### [PR #46](https://github.com/mmasias/25-26-EDA1/pull/46)

**Archivo**: `src/Juegotelefono.java`
- **Convenciones de nomenclatura**: *"Cuidado con el nombre de archivo que lo contiene!"*

### [PR #52](https://github.com/mmasias/25-26-EDA1/pull/52)

**Archivo**: `documentos/Aisha.java`
- **Responsabilidades de clases**: *"¿Qué hace Aisha?"*

**Archivo**: `documentos/Lydia.java`
- **Responsabilidades y principios OOP**: *"Más que recibir un niño, lo está creando, no?... ¿Le corresponde hacerlo?"*

**Archivo**: `documentos/Mensaje.java`
- **Propósito de clases**: *"¿Para qué?"*
- **Malas prácticas**: *"`public static` no es recomendado!"*

**Archivo**: `documentos/TelefonoDescacharrado.java`
- **Principio de responsabilidad única**: *"si hemos de gestionar todo aquí, entonces TelefonoDescacharrado se convierte en una clase que tiene que hacer de todo... Reflexionemos sobre esto."*

### [PR #45](https://github.com/mmasias/25-26-EDA1/pull/45)

**Archivo**: `src/Fila.java`
- **Reconocimiento de solución creativa**: *"Mola! Recuérdame comentarlo la próxima clase!"*

**Archivo**: `src/Ludoteca.java`
- **Crítica al antipatrón "God Class"**: *"Modo MAMA on... nah!!! Que Aisha maneje su propia fila! ¿Para qué se lo da la Ludoteca? ¿Y por qué no le da una fila también a Lidia?"*
- **Advertencia sobre código peligroso**: *"línea potente pero peligrosa. No la recomiendo..."*

### [PR #38](https://github.com/mmasias/25-26-EDA1/pull/38)

**Archivo**: `src/ColaCircular.java`
- **Sorpresa positiva**: *"😮"*
- **Reacción negativa a implementación**: *"🤢"*
- **Cuestionamiento de diseño**: *"¿Que diferencia (real) hay entre fin y tamaño?"*

### [PR #27](https://github.com/mmasias/25-26-EDA1/pull/27)

**Archivo**: `src/SimulacionTelefono.java`
- **Restricción de conocimientos**: *"¿Vimos try-catch en PRG1 o PRG2?"*

**Archivo**: `src/Nino.java`
- **Diseño de clases**: *"A pesar de las reservas que tengo con la clase GeneradorMensajes, ya que la creamos: ¿no tendría más sentido que esa clase ofrezca la posibilidad de cambiar el mensaje? Lo comentamos mañana"*

**Archivo**: `src/Ludoteca.java`
- **Falta de elementos del dominio**: *"Y las monitoras?"*

### [PR #7](https://github.com/mmasias/25-26-EDA1/pull/7)

**Archivo**: `documentos/README.md`
- **Crítica a prioridades de diseño**: *"O sea que creas una clase utilitaria y no creas a la pobres y sufridas Aisha y Lydia? 😛"*

**Archivo**: `documentosUML/DiagramaClases.png`
- **Aprobación de diseño**: *"👍🏼"*

**Archivo**: `src/ColaNiños.java`
- **Redundancia en diseño**: *"Esta parte la he visto en varios códigos, y no termino de ver la necesidad de duplicar final y cantidad."*

**Archivo**: `src/Niño.java`
- **Sorpresa por uso de inyección de dependencias**: *"😮 le pasas un objeto random?"*

### [PR #13](https://github.com/mmasias/25-26-EDA1/pull/13)

**Archivo**: `src/Pizarra.java`
- **Aprobación**: *"👍🏼"*

**Archivo**: `src/Ninho.java`
- **Responsabilidades incorrectas**: *"Nooooo: el niño tiene un pizarrin!"*
- **Tristeza por implementación**: *"😢"*

**Archivo**: `src/Ludoteca.java`
- **Código peligroso**: *"peligroso! Mañana lo comentamos"*

**Archivo**: `src/ColaNinho.java`
- **Cuestionamiento simple**: *"?"*
- **Principios de organización**: *"Una clase dentro de otra es agregar una capa de complejidad innecesaria: seamos ordenados. Si vas a usar un nodo (que es una progresión lógica, y de hecho me esperaba que algunos llegaran a esa conclusión incluso antes de mencionarla), ok: úsalo. Pero hagámoslo bien: una clase, un archivo!"*

### [PR #14](https://github.com/mmasias/25-26-EDA1/pull/14)

**Archivo**: `src/ColaCircular.java`
- **Idea pedagógica**: *"Ya que muchos han hecho una cola circular, en lugar de preguntar 'pa' qué' mañana proponemos hacer el juego ese de las sillas..."*
- **Rechazo fuerte**: *"No! No! NO!!!!"*
- **Cuestionamiento de diseño**: *"Dicho esto: ¿qué diferencia real hay entre fin y tamaño?"*
- **Sospecha**: *":suspect:"*

**Archivo**: `src/Metrica.java`
- **Números mágicos**: *"128 qué es?"*
- **Cuestionamiento de propósito**: *"¡Metrica! ¿Eso qué es?"*
- **Sorpresa por algoritmo**: *"hamming???"*

**Archivo**: `src/ModeloErrorBinario.java`
- **Emoji expresivo**: *":finnadie:"*

### [PR #19](https://github.com/mmasias/25-26-EDA1/pull/19)

**Archivo**: `src/Cola.java`
- **Números mágicos**: *"¿Qué es 100? TAMAÑO_MAXIMO quizá?"*
- **Cuestionamiento de diseño**: *"Reflexionemos: ¿qué diferencia (real) hay entre fin y tamaño?"*
- **Simplificación de código**: *"Y por qué no `return fin`?"*
- **Responsabilidades de objetos**: *"Esto no lo entiendo: si tu eres la cola y tienes los niños, ¿te los pides a ti mismo?. Cógelos directamente, no?"*
- **Diseño de responsabilidades**: *"Eres la cola, no tienes que pedirte los niños: son tuyos! Mañana comentamos esto..."*

**Archivo**: `src/EstructuraGeneral.md`
- **Falta de elementos**: *"Y las monitoras?"*

### [PR #20](https://github.com/mmasias/25-26-EDA1/pull/20)

**Archivo**: `src/reto-001/Fila.java`
- **Estilo de código**: *"No me gusta el foreach, pero si lo usamos, no perdamos la belleza de: `for(Nino nino: ninos) { nino.limpiarPizarrin(); }` Dicho esto: muy bien lo de explicitar la acción de limpiarPizarrin(). Por ahí van los tiros!"*

**Archivo**: `src/reto-001/JuegoTelefono.java`
- **Reflexión**: *"🤔"*

**Archivo**: `src/reto-001/Mensaje.java`
- **Sorpresa por autogeneración**: *"El mensaje se autogenera? 🤯"*

### [PR #21](https://github.com/mmasias/25-26-EDA1/pull/21)

**Archivo**: `evaluaciones/retos/Aisha.java`
- **Múltiples observaciones**: *"Cuidado con la indentación, los saltos de línea y los espacios!!! `public Aisha(ColaNinos cola) { this.cola = cola; }` #2Think: ¿No hace nada más Aisha? ¿Aisha es una clase? ¿No es Monitor?"*

**Archivo**: `evaluaciones/retos/ColaNinos.java`
- **Restricción de herramientas**: *"Nop: el reto se debe resolver solo con lo que hemos visto hasta PRG1 y PRG2."*

**Archivo**: `evaluaciones/retos/Estadistica.java`
- **Cuestionamiento de propósito**: *"¿Estadísticas para qué?"*

**Archivo**: `evaluaciones/retos/Mensaje.java`
- **Sorpresa por algoritmo avanzado**: *"hamming?!?!?!?"*

### [PR #22](https://github.com/mmasias/25-26-EDA1/pull/22)

**Archivo**: `src/reto001/Fila.java`
- **Restricción de herramientas**: *"Nup: el reto era hacerlo con lo que hemos visto en PRG1 y PRG2!"*

**Archivo**: `src/reto001/Juego.java`
- **Tristeza**: *"😭"*

**Archivo**: `src/reto001/Niño.java`
- **Cuestionamiento de atributos**: *"¿Para qué el id?"*

### [PR #24](https://github.com/mmasias/25-26-EDA1/pull/24)

**Archivo**: `src/Ludoteca.java`
- **Tristeza**: *"😭"*
- **Números mágicos**: *"16 🤔"*

**Archivo**: `src/Juego.java`
- **Responsabilidades**: *"Esto no son los niños quien lo hacen?"*

## Patrones

<div align=center>

| Patrón | Problema detectado | Respuesta pedagógica | Estrategia |
|-|-|-|-|
| Uso indebido de herramientas avanzadas | Estudiantes usando `ArrayList`, `Queue`, `LinkedList`, `try-catch` | Recordatorio claro de las limitaciones ("solo PRG1 y PRG2") | Spoiler educativo ("no lo veremos ni en EDA1") |
| Números mágicos y constantes | Uso de literales como 5, 10, 100, 120, 128 | Preguntas socráticas ("¿Qué es 100?") | Uso de `final int` y nombres descriptivos (TAMAÑO_MAXIMO) |
| Responsabilidades de objetos | Confusión sobre qué objeto debe hacer qué | Preguntas directas ("¿te los pides a ti mismo?") | Clarificar principio de responsabilidad única |
| Algoritmos sorprendentemente avanzados | Implementación de Hamming, métricas complejas | Sorpresa y cuestionamiento ("hamming?!?!?!?") | Valorar creatividad pero mantener simplicidad |
| Organización y estructura | Clases anidadas, código mal indentado | Principios claros ("una clase, un archivo") | Enfocarse en orden y legibilidad |
| Elementos del dominio faltantes | Ausencia de Aisha y Lydia en diseños | Recordatorio directo ("Y las monitoras?") | Fidelidad al enunciado del problema |
| Estado vs comportamiento | Confusión entre "decir" vs "hacer" | Ejemplo pedagógico ("Estudia!" vs estudiar) | Base para entender encapsulamiento |
| Reacciones emocionales como feedback | Códigos que generan tristeza o sorpresa | Emojis expresivos (😭, 😮, 🤢) | Comunicación más humana y directa |

</div>
