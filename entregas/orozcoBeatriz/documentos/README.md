# 📘 ArrayComoLista

Este proyecto implementa una **estructura de datos tipo lista** usando un **array dinámico**.  
La idea es simular el comportamiento de una lista enlazada, pero implementada desde cero, sin usar colecciones predefinidas.

---

## 🔹 Objetivo del programa

Crear una clase (`ArrayComoLista`) que permita:
- Agregar elementos al final.
- Insertar elementos en cualquier posición.
- Eliminar elementos.
- Modificar valores existentes.
- Imprimir la lista completa.
- Hacer que el array crezca automáticamente cuando se llena.

Todo esto se gestiona **internamente con un array** que se redimensiona cuando es necesario.

---

## 🔹 Clases del programa

### 🧩 Clase `ArrayComoLista`

Es la clase principal. Contiene los atributos y métodos para gestionar la lista.

#### **Atributos**
| Atributo | Tipo | Descripción |
|-----------|------|--------------|
| `datos` | `int[]` | Array que almacena los valores de la lista. |
| `tamaño` | `int` | Número actual de elementos almacenados. |

---

#### **Constructor**
```java
public ArrayComoLista(int capacidadInicial)
