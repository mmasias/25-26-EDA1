# Diagrama de Workflow

```mermaid
flowchart TD
    A[Inicio] --> B[Presentación]
    B --> C[Crear Ludoteca, Tiempo, Mundo]
    C --> D{¿Tiempo terminado?}
    D -->|No| E[Simular]
    E --> F[Limpiar consola]
    F --> G[Imprimir tiempo]
    G --> J{Llega niño?}
    J -->|Sí| K[Crear niño con nombre aleatorio]
    K --> L[Imprimir llegada del niño]
    L --> M[Recibir niño en ludoteca]
    J -->|No| M
    M --> N[Actualizar ludoteca]
    N --> O[Imprimir estado ludoteca]
    O --> P[Siguiente tiempo]
    P --> Q[Esperar usuario]
    Q --> D
    D -->|Sí| R[Fin]