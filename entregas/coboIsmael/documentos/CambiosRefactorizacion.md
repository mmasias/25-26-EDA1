# Cambios y justificación

## 1. Separación de lo que muestra por pantalla
Antes había partes de la estructura que imprimían cosas por consola.
Ahora la consola se usa solo en `Cliente`, y las estructuras se centran en guardar y devolver datos.

## 2. Comprobaciones simples con assert
En vez de llenar los métodos de `if` con mensajes de error, se usan `assert` para comprobar:
- índices válidos
- capacidad válida

Así el código queda más limpio y el fallo se ve rápido si algo se usa mal.

## 3. Datos encapsulados
Los atributos se dejan como `private` para no tocar datos desde fuera sin control.
Las operaciones pasan siempre por métodos.

## 4. Métodos más claros y cortos
Se han separado trozos repetidos en métodos privados:
- `expandir()` para crecer la estructura principal
- `localizar()` para encontrar el nodo de una posición

Esto evita repetir recorridos y hace el código más fácil de seguir.

## 5. Estructura interna ordenada
El almacenamiento se organiza en varios niveles:
- `Lista` gestiona el tamaño real y cuándo crecer
- `Array` gestiona una capacidad fija
- `ListaEnlazada` y `Nodo` se usan para guardar los valores internamente

El resultado final es el mismo que en el parcial, pero con el código más ordenado.