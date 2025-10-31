# RETO 004 

## PARTE 1

- Tamaño – Representar un número de elementos usados dentro del array y permitir su expansión cuando sea necesario.

- Inserción – Mover los elementos existentes para crear espacio y actualizar el número real de elementos.

- Eliminación – Desplazar los elementos siguientes para eliminar huecos.

- Espacio Libre – Reservar posiciones vacías o marcarlas como no utilizadas.

- Acceso – Controlar el acceso directo, controlando el rango válido según la cantidad de elementos.

## PARTE 2

Para simular una lista utilizando un array, primero se debe distinguir entre tamaño físico (capacidad total del array) y tamaño lógico, que será el número real de elementos almacenados y que se mantendrá en una variable independiente, ya que el array en sí no puede crecer. Si el array se llena tenemos 2 opciones:

- Ampliarlo internamente copiando todo a uno más grande.
- Decir que no cabe más.

Para insertar algo en medio, habría que mover todos los elementos a la derecha para abrir un hueco, y para eliminar algo, los movemos a la izquierda para que no queden huecos vacíos. Todo esto pasaría por dentro y de forma automática, de modo que el usuario vea que solo puede agregar, quitar o insertar elementos.

## PARTE 3

- Con un array fijo como el del autor, la flexibilidad no puede ser muy amplia, ya que el número de especialidad es fijo. Como solución desatendida, un segundo autor.

- En el caso del segundo autor, pasándonos por tierra de atractivo cuando uno más grande por detrás sin que el array se deba crearla, el problema estaría en el costo de tiempo, ya que insertar o eliminar algo en medio de un array requiere de un gran uso de memoria y tiempo.

- Entra cómo somos capaces de hacer algo conceptualmente y que no siempre coincide con lo que el array realmente permite.

- En nuestro caso pasándonos decir los arrays utilizados en C++ que internamente utilizan arrays fijos pero se utilizan como línea. Incluso los sistemas de bases de datos hacen esto, lo ves hasta modificar para implementar usar bloques organizados.

### Conclusión:

Siendo una lista con un array dinámico, que un array estático puede comportarse como flexible si se configura mediante obstaculación. Para simular una lista con un array es necesario manejar un tamaño variable y controlar cuándo un elemento está realmente usado, permitir inserciones y eliminaciones provisionales obteniendo según la posición, requerir o marcar espacios libres, y asegurar que el acceso a los elementos solo se haga dentro del rango válido.