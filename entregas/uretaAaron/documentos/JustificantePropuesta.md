## Justificación de la propuesta: pyIris

El proyecto **pyIris** nace de un problema muy común en el entorno académico: muchos correos institucionales son largos, poco claros y los estudiantes suelen revisarlos tarde o directamente no leerlos, mientras que las notificaciones por **WhatsApp** se consultan con mucha más frecuencia y rapidez.

Para mejorar esta comunicación, pyIris propone un sistema que recibe correos académicos, extrae la información realmente importante (profesor, asignatura y mensaje clave) y la transforma en mensajes breves y entendibles que se envían por WhatsApp. De esta forma, se reduce el ruido del correo original y se aumenta la probabilidad de que el estudiante se entere a tiempo de avisos, tareas o cambios de última hora.

La solución se apoya en una **arquitectura modular**, donde cada componente tiene una responsabilidad bien definida (procesar texto, gestionar suscriptores y enviar mensajes). Esto hace que el código sea más fácil de mantener, probar y extender en el futuro (por ejemplo, añadiendo otros canales además de WhatsApp). Además, se emplean estructuras de datos eficientes para obtener rápidamente todos los suscriptores de una asignatura y un flujo basado en una cola de notificaciones, lo que permite manejar picos de correos sin perder información ni bloquear el sistema.
