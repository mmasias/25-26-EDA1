# Petición del Cliente

Un servicio de streaming de música (modelo gratuito con publicidad) necesita gestionar la cola de reproducción del usuario. El sistema debe soportar múltiples operaciones:

1. **Añadir al final**: Usuario agrega canción al final de la cola de reproducción.
1. **Reproducir siguiente**: Usuario quiere que cierta canción suene inmediatamente después de la actual.
1. **Eliminar canción**: Usuario decide quitar una canción específica de la cola.
1. **Siguiente/Anterior**: Navegación bidireccional durante reproducción.
1. **Modo aleatorio (shuffle)**: Reproducir canciones en orden aleatorio sin repetir hasta agotar cola.
1. **Historial sin repetición**: Al seleccionar "radio automática", el sistema debe agregar canciones nuevas evitando las ya reproducidas en la sesión actual.
1. **Búsqueda en cola**: Usuario necesita verificar si cierta canción ya está en cola antes de agregarla.
1. **Estadísticas de sesión**: Canción más reproducida, tiempo total de reproducción.
1. **Inserción de publicidad**: El sistema debe insertar automáticamente un anuncio cada 4 canciones reproducidas.
1. **Restricción de salto**: Durante reproducción de publicidad, botón "siguiente" está deshabilitado.

## Restricciones explícitas

### Contexto operativo

- Cola de reproducción: 50-200 canciones simultáneas
- Historial de sesión: hasta 1000 canciones reproducidas

### De recursos

- El sistema funcionará en entornos de memoria limitada, por lo que se requiere optimizar este recurso
- Por contra, la capacidad de procesamiento es razonablemente alta

### De operación

- El modo shuffle no debe repetir canciones hasta agotar la cola completa
- La publicidad NO forma parte de la cola del usuario: si el usuario visualiza su cola, no debe ver anuncios
- La publicidad NO cuenta para estadísticas: tiempo total y canción más reproducida solo consideran canciones reales
- Si el usuario agrega canciones manualmente mientras suena publicidad, el contador de "cada 4 canciones" NO se reinicia

## Entregable

1. **Diagramas**: Representación visual UML de qué estructuras se utilizan y cómo se conectan
1. **Justificación**: Para cada operación, explicar **qué** estructura soporta esa operación y **por qué**.
1. **Compromisos**: Identificar de modo claro qué operaciones se sacrifican (si alguna) y el coste aceptado
1. **Casos límite**: Describa cómo el diseño que propone maneja:
   - Activar shuffle con una sola canción en cola
   - Eliminar canción que está sonando actualmente
   - Usuario agrega manualmente una canción ya reproducida en la sesión
   - Usuario agrega 10 canciones de golpe: ¿dónde se insertan los anuncios?
   - Usuario elimina todas las canciones de la cola mientras suena publicidad: ¿qué ocurre al terminar el anuncio?
   - Botón "anterior" presionado durante publicidad: ¿vuelve a canción anterior o queda bloqueado?
