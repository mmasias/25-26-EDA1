# Explicación del proyecto: ArrayComoLista

El proyecto se compone de dos clases: **Ejemplo de ArrayComoLista** y **ArrayComoLista**.  

## Clase ArrayComoLista

Toda la lógica se encuentra en la clase **ArrayComoLista**.  
Contiene dos atributos:  
una variable denominada `tamaño`, que señala el número de elementos que están ocupando posiciones en el array, y un array denominado `datos`, donde se almacenan los valores.  
El constructor recibe una capacidad inicial y crea el array basándose en ella.  
Si el valor proporcionado es menor o igual a cero, se establece por defecto una capacidad de 4.  

La clase cuenta con varios métodos.  
La manera de `agregar` añade un elemento nuevo al final, en tanto que el método `insertar` permite situar un valor en una ubicación específica, moviendo los elementos que vienen después una posición a la derecha.  
El método `establecer` reemplaza el valor que ya existe con uno nuevo, mientras que el método `obtener` devuelve el valor de una posición específica.  
El método `eliminarEn` suprime un elemento de una posición y desplaza los restantes hacia la izquierda para no dejar espacios vacíos.  

Además, existe un método privado que se llama `asegurarCapacidad` y su función es aumentar el tamaño del array cuando este se llena.  
Si el espacio no es suficiente, genera un nuevo array con el doble de tamaño y transfiere los datos viejos a este.

## Clase EjemploArrayComoLista

En esta clase se crean varios objetos de la clase `ArrayComoLista` y se invocan los métodos previos para verificar su operatividad, incluyendo agregar, modificar, insertar, eliminar y obtener valores.  
Para que el programa muestre los mensajes correctos, también hemos incluido casos que están fuera de rango.
