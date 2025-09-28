# Diagrama de Workflow

```mermaid
flowchart TD
    A[Inicio] --> B[Presentación]
    B --> C[Crear Ludoteca, Tiempo, Mundo]
    C --> D[Simular]
    D --> E[Limpiar consola]
    E --> F[Imprimir tiempo]
    F --> G[Crear monitores Lydia y Aisha]
    G --> H[Recibir monitores en ludoteca]
    H --> I{Llega niño?}
    I -->|Sí| J[Crear niño con nombre aleatorio]
    J --> K[Imprimir llegada del niño]
    K --> L[Recibir niño en ludoteca]
    I -->|No| L
    L --> M[Actualizar ludoteca]
    M --> N[Imprimir estado ludoteca]
    N --> O[Siguiente tiempo]
    O --> P[Esperar usuario]
    P --> Q[Fin]