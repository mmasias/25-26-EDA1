# Desarrollo del Proyecto Ludoteca

## Resumen
Este documento describe el proceso de desarrollo del proyecto Ludoteca, una simulación del juego "Teléfono Descacharrado" en una ludoteca. El proyecto fue construido de manera incremental, siguiendo buenas prácticas de desarrollo de software.

## Camino Seguido

### 1. Análisis de Requisitos
- Lectura del README del proyecto para entender las reglas del juego
- Identificación de las clases principales: Ludoteca, Lydia, Aisha, Juego, Ninho, Pizarra, Tiempo
- Definición de las responsabilidades de cada clase

### 2. Diseño Arquitectónico
- Creación de la clase base `Ludoteca` con el método `abrir()`
- Implementación de la clase `Tiempo` para manejar el paso del tiempo
- Diseño de las clases `Lydia` (Recepcionista) y `Aisha` (Animadora) para manejar niños y juegos
- Creación de estructuras de datos: `Fila`, `ColaNinho`, `Ninho`, `Pizarra`, `Juego`

### 3. Implementación Incremental
- **Tiempo.java**: Gestión del tiempo de la ludoteca
- **Lydia.java**: Recepción de niños según el ritmo especificado
- **Fila.java**: Cola de niños para Aisha
- **Juego.java**: Control del estado del juego
- **Ninho.java**: Representación de niños con mensajes
- **Pizarra.java**: Pizarra principal para mostrar mensajes finales
- **Aisha.java**: Gestión de la fila y coordinación del juego
- **Ludoteca.java**: Lógica principal de simulación

### 4. Refinamientos y Mejoras
- Implementación de herencia con clase abstracta `Tutor`
- Renombrado de clases para mayor claridad: Lydia → Recepcionista, Aisha → Animadora
- Uso de arrays de char en lugar de String para mensajes
- Implementación de cola personalizada `ColaNinho`
- Agregado de prints de debug para seguimiento del flujo
- Validación de que Aisha nunca tenga más de 5 niños

### 5. Documentación
- Creación de diagramas Mermaid:
  - [Diagrama de Clases](../documentosUML/diagrama_clases.md): Estructura estática del proyecto
  - [Diagrama de Workflow](../documentosUML/diagrama_workflow.md): Flujo dinámico de la simulación

## Principios Aplicados
- **Responsabilidad Única**: Cada clase tiene una responsabilidad clara
- **Encapsulamiento**: Atributos privados con métodos públicos
- **Herencia**: Uso de clase abstracta para compartir comportamiento
- **Composición**: Relaciones entre objetos
- **Polimorfismo**: Métodos comunes en clases derivadas
- **Estructuras de Datos**: Implementación de cola personalizada
- **Debugging**: Prints detallados para seguimiento

## Resultado Final
El proyecto simula correctamente la ludoteca durante 2 horas, manejando la llegada de niños, formación de filas, juegos con distorsión de mensajes, y generación de reportes finales. Los diagramas proporcionan una visión clara de la arquitectura y el flujo de trabajo.

**Nota**: Los diagramas Mermaid fueron generados con asistencia de inteligencia artificial (IA) para facilitar la visualización de la estructura y el flujo del proyecto.