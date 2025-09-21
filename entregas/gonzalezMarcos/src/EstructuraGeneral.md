#  Explicación del Código: Juego “Teléfono Escacharrado”

## 🏁 Estructura General
El proyecto está compuesto por **4 clases principales**:

- `Main` – Punto de entrada del programa.  
- `Juego` – Controla el flujo completo del juego.  
- `Cola` – Estructura que gestiona a los niños en fila.  
- `Nino` – Representa a cada niño, su nombre y su pizarra.

---

## 1️⃣ Clase `Main`

```java
public class Main {
    public static void main(String[] args) {
        Juego telefonoEscacharrado = new Juego();
        telefonoEscacharrado.iniciarJuego();
    }
}
```
- Rol
	-	Es el punto de entrada del programa.
	-	Crea un objeto Juego y llama al método iniciarJuego() para comenzar la simulación.

## 2️⃣ Clase Juego

Controla la lógica principal del juego: llegada de niños, formación de la cola y distorsión del mensaje.

### Atributos
```java
private static final int INTERVALO_RAPIDO = 10;
private static final int MAX_LLEGADAS = 2;
private static final int DURACION = 120;
private static final int MOD_LENTO = 3;
private static final int TIEMPO_EXTRA = 2;
private static final int LONGITUD_MENSAJE = 10;
private static final int INTERVALO_LENTO = 30;
private static final int MIN_NINOS = 6;
```
- Constantes del juego:
	-	INTERVALO_RAPIDO: Durante los primeros 10 minutos los niños llegan rápido.
	-	MAX_LLEGADAS: Máximo de niños que pueden llegar en un minuto rápido.
	-	DURACION: Duración total del juego en minutos.
	-	MOD_LENTO: Cada cuántos minutos puede llegar un niño en la fase lenta.
	-	TIEMPO_EXTRA: Tiempo extra que se consume cuando se juega una ronda de mensaje.
	-	LONGITUD_MENSAJE: Longitud de cada mensaje generado (10 letras).
	-	INTERVALO_LENTO: Hasta qué minuto dura la fase lenta.
	-	MIN_NINOS: Número mínimo de niños para que inicie el paso del mensaje.

```java
private Cola cola;
private int tiempo;
private int totalMinutos;
```
- cola: Objeto Cola donde se enfilan los niños.
- tiempo: Minuto actual del juego.
- totalMinutos: Minutos totales del juego (inicialmente DURACION).

### Constructor
```java
public Juego() {
    cola = new Cola();
    tiempo = 0;
    totalMinutos = DURACION;
}
```
- Inicializa una cola vacía.
- Establece el tiempo en 0 y fija la duración total.

### Método principal: iniciarJuego()

Controla la simulación minuto a minuto:

1.	Bucle principal:

    Mientras tiempo < totalMinutos:
    -	Muestra el minuto actual.
    -	Controla la llegada de niños:
    -	Rápida (0-9 min): Llegan 0, 1 o 2 niños al azar.
    -	Lenta (10-29 min): 50% de probabilidad de llegada cada 3 min.
    -	Muestra los niños en la cola.
	    
2.	Cuando hay suficientes niños (>= MIN_NINOS):
	-	Limpia las pizarras de todos.
	-	Genera un mensaje aleatorio de 10 letras.
	-	Cada niño, uno tras otro, copia el mensaje en su pizarra alterando una letra al azar, simulando el “teléfono roto”.
	-	Avanza el tiempo en tamañoCola + TIEMPO_EXTRA.
3.	Si no hay niños suficientes:
	-	Incrementa el tiempo en 1 minuto.

---

### Otros métodos

**limpiarPizarras()**
- Llama a cada niño de la cola para borrar su pizarra.

**generarMensaje()**
-	Crea un string aleatorio de 10 letras mayúsculas (A–Z).

## 3️⃣ Clase Cola

Representa la estructura de datos de cola circular que guarda a los niños en orden de llegada.

### Atributos

```java
private Nino[] cola;
private int inicio;
private int fin;
private int tamaño;
```
-	cola: Arreglo de niños (capacidad 100).
-	inicio: Índice del primer niño.
-	fin: Índice donde se insertará el siguiente niño.
-	tamaño: Número de niños actuales.

---

### Métodos clave
-	añadirNiño(Nino niño)
Inserta un niño al final si hay espacio, ajustando índices circulares.
-	obtenerNiñosCola()
Devuelve un arreglo con los niños actuales en orden.
-	tamañoCola()
Retorna la cantidad de niños en la cola.
-	limpiarCola()
Vacía la cola por completo.
-	toString()
Devuelve una representación de la cola con los nombres de los niños o “Cola vacía”.



## 4️⃣ Clase Nino

Representa a cada niño participante.

### Atributos
´´´java
private String nombre;
private String pizarra;
´´´
-	nombre: Identificador del niño (por ejemplo, “Niño1”).
-	pizarra: Mensaje que el niño tiene escrito.

---

### Métodos
-	Constructor Nino(String nombre)
Asigna el nombre y una pizarra vacía.
-	getNombre()
Devuelve el nombre del niño.
-	limpiarPizarra()
Borra el contenido de la pizarra.
-	escribirEnPizarra(String mensaje)
-	Toma el mensaje recibido.
-	Elige una posición aleatoria dentro del mensaje.
-	Sustituye esa letra por otra aleatoria (A–Z).
-	Guarda el nuevo mensaje en su pizarra.
-	Retorna el mensaje modificado (que se pasará al siguiente niño).
-	toString()
Muestra el nombre y el mensaje que escribió.


##  Flujo del Juego (Resumen)
1.	Inicio: Main crea un Juego y llama a iniciarJuego().
2.	Llegadas: Niños llegan a la cola según la fase (rápida o lenta).
3.	Ronda de Mensaje:
	-	Si hay al menos 6 niños:
	    1.	Se genera un mensaje aleatorio (10 letras).
	    2.	Cada niño lo copia alterando una letra.
	    3.	Se muestra la evolución del mensaje final.
4.	Tiempo: Avanza según el número de niños que participaron + 2 min extra.
5.	Fin: El juego termina al llegar a 120 minutos.
