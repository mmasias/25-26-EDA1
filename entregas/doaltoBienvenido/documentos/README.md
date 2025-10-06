# Ludoteca - Documentación del diseño y uso

Este documento describe la arquitectura de la simulación de la ludoteca, los roles de cada clase, las reglas de negocio implementadas y cómo se usa el programa.

## Estructura general

- Universo (punto de entrada): contiene el método `main` que crea un `Mundo` y ejecuta la simulación.
- Mundo (controlador de la simulación): muestra el menú, lee opciones y coordina acciones entre las monitoras y los niños.
- Ludoteca (agregador): contiene tres monitoras (`Lydia`, `Aisha`, `Dalsy`) y expone operaciones para consultar su estado.
- Monitor (gestión de colas): administra una cola simple basada en array (capacidad fija), operaciones de transferencia y utilidades de consulta.
- Niño (entidad): nombre y edad; utilidades de presentación; puede tener un pizarrín (cuando sea necesario).
- Pizarra (valor simple): almacenar/limpiar un texto.
- Mensaje (servicio I/O): simplifica impresión de mensajes `mensaje` y `mensajeLn`.

El diagrama de clases PlantUML se encuentra en `documentosUML/diagrama_clases.puml`.

## Reglas de negocio clave (constantes)

- Mínimo de niños para iniciar juego: `5`
- Edad mínima para el juego de la rana: `5`
- Mayoría simple para aprobar condición: `> total / 2`

Estas constantes están definidas en `Mundo.java` como `static final` y se usan en el flujo de opciones.

## Flujo de la simulación

1. Llegada de niños: `Mundo.simularLlegada()` solicita nombre y edad, y añade el niño a la cola de `Lydia`.
2. Intento de inicio de juego: si `Lydia` tiene al menos 5 niños, transfiere su cola a `Aisha`.
3. Presentaciones: `Aisha` puede pedir presentaciones generales, por edad mínima, por inicial, o mostrar los primeros/últimos 5.
4. Conteo y promedios: reporte de niños por monitora y cálculo de edad promedio en la cola de `Aisha`.
5. Juego de la rana: valida si al menos la mitad (mayoría simple) son de 5 años o más.
6. Separación por edad: mueve menores de 5 años a `Dalsy` sin dejar huecos en la cola (compactación).
7. Alarma: transfiere todos los niños a `Lydia` para evacuación ordenada.

## Decisiones de diseño

- Cola basada en array fijo: `Monitor` utiliza un array `Niño[]` con tope `CAPACIDAD_MAXIMA` (50). Esto simplifica el manejo sin colecciones avanzadas.
- Métodos utilitarios para robustez:
	- `getNiñoEn(int)` y `removerEn(int)`: permiten inspeccionar y extraer elementos compactando el array, evitando `null` intermedios.
	- `edadPromedio()` y `contarMayoresDe(int)`: ignoran posibles `null` por seguridad.
- Nombres descriptivos: se renombraron variables (por ejemplo, `monitorLydia`) para mejorar legibilidad.
- Eliminación de “magic numbers”: todas las cifras relevantes se centralizaron como constantes.

## Cómo ejecutar

1. Compilar y ejecutar desde `Universo`.
2. El menú mostrará opciones numeradas; introduzca el número y presione Enter.
3. Tras cada acción, se imprime un separador para mejorar la lectura en consola.

## Posibles mejoras

- Extraer una clase `Cola` para encapsular totalmente el manejo del array y capacidad.
- Validación de entradas del usuario con reintentos (evitar `InputMismatchException`).
- Persistencia de sesiones o reporte final de métricas.

## Diagrama de clases

Para visualizar el diagrama en VS Code, puede usar la extensión de PlantUML. El archivo está en `documentosUML/diagrama_clases.puml`.

