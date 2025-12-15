# Propuesta de Solución Reto-006

## Justificación de Estructuras

### Estructura Principal: Sistema de Mensajería

**HashMap de Categorías (`categorias`)**
- **Estructura**: `Dict[str, Categoria]` donde la clave es el ID de la categoría
- **Justificación**: Permite acceso O(1) a cualquier categoría por su identificador. Esencial para operaciones de publicación y suscripción frecuentes.
- **Operaciones soportadas**:
  - Buscar categoría: O(1)
  - Crear nueva categoría: O(1)
  - Eliminar categoría: O(1)

**HashMap de Suscriptores (`suscriptores`)**
- **Estructura**: `Dict[str, Suscriptor]` donde la clave es el ID del suscriptor
- **Justificación**: Acceso directo a información del suscriptor sin recorrer listas. Crítico para verificar estado de suscripción y enviar notificaciones.
- **Operaciones soportadas**:
  - Buscar suscriptor: O(1)
  - Actualizar información: O(1)
  - Verificar estado activo: O(1)

**Índice Invertido de Suscripciones (`indice_suscripciones`)**
- **Estructura**: `Dict[str, Set[str]]` mapea id_categoria → conjunto de ids_suscriptor
- **Justificación**: Permite encontrar rápidamente todos los suscriptores de una categoría sin iterar sobre todos los usuarios. Optimiza la distribución de resúmenes.
- **Operaciones soportadas**:
  - Obtener suscriptores de categoría: O(1) + O(k) donde k = número de suscriptores
  - Añadir suscriptor a categoría: O(1)
  - Eliminar suscriptor de categoría: O(1)

**Cola de Mensajes por Categoría (`buffers_mensajes`)**
- **Estructura**: `Dict[str, Deque[Mensaje]]` mapea id_categoria → cola de mensajes pendientes
- **Justificación**: Deque permite inserción O(1) al final y acceso eficiente para resumir. Mantiene orden temporal de mensajes.
- **Operaciones soportadas**:
  - Encolar mensaje: O(1)
  - Acceder a todos los mensajes para resumen: O(n) donde n = mensajes en buffer
  - Limpiar buffer tras resumen: O(1)

**HashMap de Resúmenes Pendientes (`resumenes_pendientes`)**
- **Estructura**: `Dict[str, Resumen]` mapea id_suscriptor → resumen pendiente de envío
- **Justificación**: Permite acumular múltiples resúmenes antes de enviar, evitando notificaciones excesivas. Un suscriptor puede tener un único resumen consolidado.
- **Operaciones soportadas**:
  - Almacenar resumen: O(1)
  - Recuperar resumen para envío: O(1)
  - Consolidar múltiples categorías: O(1)

**Conjunto de Emisores Válidos (`emisores_validos`)**
- **Estructura**: `Set[str]` con IDs de emisores autorizados
- **Justificación**: Verificación O(1) de si un emisor está autorizado. Previene spam y mensajes no autorizados.
- **Operaciones soportadas**:
  - Verificar emisor válido: O(1)
  - Añadir/eliminar emisor: O(1)

### Estructura de Datos Auxiliares

**Índice Temporal (`indice_temporal`)**
- **Estructura**: SortedList o Heap de tuplas (timestamp, id_mensaje)
- **Justificación**: Permite encontrar y limpiar mensajes antiguos eficientemente. Útil para políticas de retención.
- **Operaciones soportadas**:
  - Encontrar mensajes anteriores a fecha: O(log n)
  - Insertar con orden temporal: O(log n)

**Índice de Suscripciones por Usuario (`suscripciones_usuario`)**
- **Estructura**: `Dict[str, Set[str]]` mapea id_suscriptor → conjunto de ids_categoria
- **Justificación**: Operación inversa al índice de suscripciones. Permite consultar rápidamente a qué categorías está suscrito un usuario.
- **Operaciones soportadas**:
  - Listar categorías de un suscriptor: O(1) + O(m) donde m = categorías suscritas
  - Verificar si usuario está suscrito a categoría: O(1)

---

## Compromisos de Diseño

### Compromisos Aceptados

**Memoria vs. Velocidad**
- **Decisión**: Mantener múltiples índices (indice_suscripciones, suscripciones_usuario, indice_temporal)
- **Coste**: 2-3x uso de memoria adicional
- **Beneficio**: Todas las operaciones críticas son O(1) o O(log n)
- **Justificación**: En un sistema de mensajería, la latencia es crítica. Los usuarios esperan notificaciones instantáneas.

**Consistencia Eventual para Resúmenes**
- **Decisión**: Los resúmenes se generan periódicamente (por lotes), no en tiempo real
- **Coste**: Retraso entre mensaje original y notificación de resumen (configurable: 5-15 minutos)
- **Beneficio**: Reduce carga computacional y agrupa múltiples mensajes, evitando spam de notificaciones
- **Justificación**: Para un sistema académico, un retraso de minutos es aceptable

**Sin Persistencia de Mensajes Completos**
- **Decisión**: Una vez generado el resumen, los mensajes originales se pueden descartar
- **Coste**: No es posible recuperar mensajes históricos originales
- **Beneficio**: Reduce drásticamente uso de almacenamiento
- **Justificación**: El valor está en el resumen, no en mensajes individuales. Si se requiere historial, se almacenan resúmenes, no mensajes.

**Modelo de Suscripción Simple**
- **Decisión**: Un suscriptor está "dentro" o "fuera" de una categoría, sin niveles de prioridad
- **Coste**: No hay diferenciación entre tipos de suscriptores
- **Beneficio**: Simplicidad en la lógica de distribución
- **Justificación**: La complejidad adicional de múltiples niveles no justifica el beneficio en el contexto académico

**Procesamiento Asíncrono de Resúmenes**
- **Decisión**: La generación de resúmenes y el envío son operaciones separadas en el tiempo
- **Coste**: Mayor complejidad en el flujo de trabajo (necesita planificador/trabajador)
- **Beneficio**: El emisor no espera a que se procesen los resúmenes; mejor escalabilidad
- **Justificación**: Desacopla escritura de lectura, permitiendo manejar picos de carga

### Operaciones Sacrificadas

**Búsqueda de Texto Completo en Mensajes**
- **Sacrificada**: No hay índice para buscar por contenido de mensajes
- **Razón**: Los mensajes se descartan tras resumen; el índice sería temporal y costoso
- **Alternativa**: Búsqueda en resúmenes almacenados (menor granularidad)

**Ordenamiento Personalizado de Resúmenes**
- **Sacrificada**: Los resúmenes se envían en orden de categoría, no por prioridad/relevancia
- **Razón**: Requeriría puntuación compleja y múltiples índices adicionales
- **Alternativa**: Orden cronológico simple y predecible

**Historial Completo de Cambios de Suscripción**
- **Sacrificada**: No se mantiene registro de cuándo un usuario se suscribió/desuscribió
- **Razón**: Aumentaría complejidad sin valor operacional claro
- **Alternativa**: Solo se mantiene estado actual; registros de auditoría se pueden implementar externamente

**Notificaciones en Tiempo Real**
- **Sacrificada**: No hay envío instantáneo al recibir mensaje
- **Razón**: Compromiso consciente por el modelo de procesamiento por lotes
- **Alternativa**: Consulta periódica o webhooks programados

---

## Manejo de Casos Límite

### Categorías sin Suscriptores

**Comportamiento**:
- Los mensajes se reciben y almacenan temporalmente en `buffers_mensajes[id_categoria]`
- Al momento de generar resúmenes, se detecta que `indice_suscripciones[id_categoria]` está vacío o no existe
- No se genera resumen ni se envía nada

**Gestión de Recursos**:
- Los mensajes en buffer se descartan tras un tiempo de espera configurable (ej: 24 horas)
- Se mantiene un contador de mensajes descartados por categoría para métricas
- La categoría permanece activa y puede recibir suscriptores en cualquier momento

**Optimización**:
- Se puede implementar una bandera `tiene_suscriptores` en la estructura Categoria para evitar procesamiento innecesario

### Suscriptores sin Categorías Inscritas

**Comportamiento**:
- El suscriptor existe en `suscriptores` con `suscripciones_usuario[id_suscriptor] = Set()` (vacío)
- No se genera ni envía ningún resumen a este usuario
- El sistema no intenta procesarlo durante la fase de distribución

**Validación**:
- Al intentar suscribirse, se valida que la categoría exista
- Si un usuario se desuscribe de todas las categorías, permanece como entidad válida pero inactiva

**Estados Posibles**:
```
- ACTIVO_SUSCRITO: Tiene suscripciones activas → recibe resúmenes
- ACTIVO_NO_SUSCRITO: Existe pero sin suscripciones → no recibe nada
- INACTIVO: Marcado como inactivo (ver siguiente sección)
```

### Exalumnos

**Definición**: Suscriptores que ya no deben recibir notificaciones

**Implementación**:
- Campo `activo: bool` en la estructura Suscriptor
- Mantener el registro histórico pero marcar como inactivo

**Comportamiento**:
1. Al procesar resúmenes, se filtra por `suscriptor.activo == True`
2. Las suscripciones permanecen en `indice_suscripciones` pero se ignoran durante distribución
3. Permite reactivación sin perder información de suscripciones previas

**Limpieza**:
- Proceso periódico (ej: mensual) puede mover exalumnos a tabla de archivo
- Se mantiene el ID para evitar reutilización y preservar integridad referencial

**Alternativa de Diseño**:
- Implementar `suscriptores_archivados` separado para reducir carga en estructuras principales
- Mover exalumnos tras período de gracia (ej: 6 meses sin actividad)

### Mensajes sin Categoría Asignada

**Prevención**:
- Validación obligatoria: Todo mensaje DEBE tener `id_categoria`
- Rechazo en el punto de entrada con error explícito

**Manejo de Errores**:
```
Si id_categoria es None o vacío:
  → Retornar error 400 "Categoría requerida"
  → Registrar en log de errores
  → Incrementar métrica de mensajes rechazados

Si id_categoria no existe en `categorias`:
  → Opción 1 (estricta): Rechazar mensaje
  → Opción 2 (flexible): Crear categoría automáticamente con nombre genérico
```

**Categoría por Defecto**:
- Se puede implementar una categoría "sin_categorizar" como respaldo
- Útil para migración o integración con sistemas externos
- Requiere suscripción explícita para evitar spam

### Mensajes No Enviados

**Definición**: Mensajes que existen pero no se distribuyeron exitosamente

**Causas Posibles**:
1. Error en generación de resumen (fallo de IA/servicio)
2. Error en envío de notificación (servicio email/push caído)
3. Tiempo de espera agotado en procesamiento
4. Suscriptor inválido al momento de envío

**Estrategia de Manejo**:

**Para errores de resumen**:
- Mover mensajes a `resumenes_fallidos[id_categoria]`
- Reintentar con retroceso exponencial (3 intentos)
- Si falla persistentemente, notificar a administrador y descartar

**Para errores de envío**:
- Mantener resumen en `resumenes_pendientes[id_suscriptor]`
- Marcar con `contador_reintentos` y `proxima_hora_reintento`
- Cola de reintentos separada procesada periódicamente
- Tras N intentos (ej: 5), mover a cola de mensajes muertos

**Cola de Mensajes Muertos**:
- Estructura: `Dict[str, List[EntregaFallida]]`
- Contiene: id_suscriptor, resumen, timestamp, razon_error, contador_reintentos
- Permite investigación manual y recuperación
- Se limpia tras período configurable (ej: 7 días)

**Idempotencia**:
- Cada resumen tiene un `id_resumen` único
- Al reenviar, se verifica si ya fue entregado exitosamente
- Previene duplicados en caso de reintentos

**Métricas y Monitoreo**:
- Contador de mensajes no enviados por causa
- Alertas si tasa de fallo supera umbral (ej: 5%)
- Panel de control con mensajes en cola de mensajes muertos