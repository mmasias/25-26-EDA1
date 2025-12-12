# Documentación Técnica: Arquitectura de Datos de Iris

## 1. Introducción
Este documento detalla la arquitectura de estructuras de datos diseñada para el proyecto **Iris**. Siguiendo un enfoque de diseño explícito y fuertemente tipado, se han implementado estructuras de datos específicas para cada entidad del dominio (`Correo`, `Materia`, `Alumno`).

---

## 2. Definición de Estructuras de Datos

El sistema se fundamenta en tres implementaciones de estructuras lineales dinámicas, cada una adaptada a un propósito concreto:

### A. Gestión de Flujo Temporal (`ColaCorreos`)
Estructura encargada de la recepción de mensajes.
* **Componentes:**
    * **`NodoCorreo`**: Unidad de almacenamiento que contiene una referencia directa a un objeto `Correo`.
    * **`ColaCorreos`**: Estructura tipo **FIFO** (First In, First Out).
* **Justificación Técnica:**
    * Se utiliza una **Cola** para garantizar que los correos se procesan estrictamente en su orden de llegada.

### B. Gestión de Conocimiento (`ListaMaterias`)
Estructura encargada de almacenar el catálogo de asignaturas disponibles.
* **Componentes:**
    * **`NodoMateria`**: Nodo que envuelve a un objeto `Materia`.
    * **`ListaMaterias`**: Lista simplemente enlazada.
* **Justificación Técnica:**
    * Permite el crecimiento dinámico del catálogo de asignaturas sin reasignación de memoria (como ocurriría en un Array).
    * Facilita la búsqueda secuencial (`buscar(String codigo)`) para asociar un correo entrante con su asignatura correspondiente.

### C. Gestión de Relaciones (`ListaAlumnos`)
Estructura encargada de almacenar los suscriptores.
* **Componentes:**
    * **`NodoAlumno`**: Nodo que contiene los datos del `Alumno`.
    * **`ListaAlumnos`**: Lista enlazada que reside **dentro** de la clase `Materia`.
* **Justificación Técnica:**
    * Esta lista modela la relación "uno a muchos" (Una materia -> Muchos alumnos) mediante **composición**.
    * Al estar encapsulada dentro de la `Materia`, optimiza el proceso de notificación: el sistema solo recorre los nodos de los alumnos realmente interesados, evitando iterar sobre una base de datos global.