# Reto - 004
### Integrantes:
- Jimena Calderón
- Manuel Muñoz
- Miguel Rivas 

## Descripción del problema
Imitar el comportamiento de un array utilizando listas en java, basándonos rigurosamente en la vista pública hecha en la clase anterior por el grupo sentado al lado contrario.

# Divergencias con respecto a la vista pública base.

La vista pública proporcionada tenía una serie de deficiencias y puntos poco claros, por lo que tuvimos que consultar con el grupo y discutir nosotros su utilidad.

Las divergencias más notorias se las describimos a continuación:

- El método *crearArray(int tamaño)* debería ser el constructor y no una función aparte. Además es el propio elemento el que pregunta el tipo cuando ya está recibiendo el tamaño lo cual causa una disonancia.

- Por lo que entendimos al preguntar, el método *eliminar()* asignaría null un valor del array.

- El método *tamaño()* está mal descrito

- El método *mostrarElemento(int coordenada1, int coordenada2)* está pensado para un array de varias dimensiones pero en los métodos anteriores no se describe la implementación de un array de dicha característica.

- En la vista pública proporcionada las funciones se dan con valores directos sin especificar su tipo o nombre, por lo que no se identifica qué representan (aquí si se les puso los parámetros que reciben).

- Aparte, parecería más conveniente aplicar una sobrecarga en vez de colocar dos métodos distintos para *mostrar()* y *mostrarElemento(int coordenada1, int coordenada2)*

En general se puede llegar a entender pero no queda del todo claro la manera de implementar la idea dado que faltan algunos elementos como los parámetros de las funciones o sobrecarga de funciones para distintos casos de arrays. 

### Vista pública por:
- José Cabrera 
- Marco Figueroa
- José Morales
- Sara Vaquero