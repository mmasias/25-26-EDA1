# Bitácora de Cambios y Justificación

## Propuesta de Solución: Corrección de Vicios

Se han aplicado principios de "Clean Code" y programación defensiva con asserts para mejorar la calidad y profesionalidad del código. A continuación se detallan las mejoras implementadas y su justificación.

### 1. Programación Defensiva (Asserts)

En lugar de utilizar estructuras de control (`if`) para imprimir errores por consola —lo cual mezcla lógica con vista—, se han utilizado aserciones (`assert`). Esto establece un **contrato** claro: el método espera condiciones válidas (índices correctos) y falla estrepitosamente si el llamador no las respeta, forzando una depuración temprana.

### 2. Eliminación de Saltos de Control (Break)

Se ha sustituido la sentencia `switch` tradicional (que requiere `break`) por la sintaxis moderna "Arrow Switch" (`->`). Esto evita errores comunes por omisión de `break` (fall-through) y hace el código más declarativo y seguro.

### 3. Encapsulamiento

Todos los atributos (`elementos`, `cantidadElementos`) son `private`. No se exponen detalles de implementación. La interacción se realiza estrictamente a través de métodos públicos ("Vista Pública"), protegiendo la integridad del estado interno.

### 4. Modularidad

Se ha extraído la lógica de redimensionamiento al método privado `expandir()`. `Lista` no mezcla la lógica de "crecer" con la lógica de "insertar", respetando el principio de responsabilidad única.

### 5. Separación Vista/Lógica

Se eliminaron todos los `System.out.println` de la clase `Lista`. Se añadieron métodos como `obtenerTexto()` y `obtenerTextoPosicionesValidas()` para que la clase `Principal` (Interfaz) decida cómo y cuándo mostrar la información, desacoplando completamente la lógica de la presentación.
