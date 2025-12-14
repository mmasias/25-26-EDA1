# Compromisos y Costes del Diseño

Para lograr la eficiencia mencionada, hemos aceptado los siguientes compromisos en el diseño:

## Redundancia de Datos (Sacrificio de Memoria):

Como compromiso tendremos que guardar la relación Estudiante-Asignatura en dos lugares:
- En el NodoAsignatura (lista de Alumnos)
- En el Estudiante (lista de códigos)

#### **Cual sería el coste aqui?**

Debido a tener que guardar la relación de forma bidireccional aumentara un poco mas el consumo de los recursos. Pero como beneficio obtenemos velocidades de consulta más altas para el perfil del estudiante, evitando recorrer masivamente el árbol para consultas simples.

## Complejidad en la Suscripción

Como compromiso ahora la operación de suscripción realizará 2 operaciones en vez de 1.

### **Cual sería el coste aqui?**

Requerirá coordinar dos inserciones. Si el sistema falla justo a al mitad, podría haber una inconsistencia, como estar en una asignatura pero el usuario no poder verla o no tenerla en su perfil.

La forma de mitigar este problema será que el código asegure que ambas operaciones se ejecuten secuencialmente siempre.


## Balanceo del Árbol

Como compromiso será que al usar el BST estándar existe el riesgo de que el árbol se desbalancee si las asignaturas se insertan en orden alfabético estricto.

### **Cual sería el coste aqui?**

En el peor caso la búsqueda tendrá una mayor complejidad a la hora de buscar, pero nunca será tan alta la cota de degeneración del árbol (No hay infinitas asignaturas, en cambio si pueden haber muchos más usuarios que asignaturas).