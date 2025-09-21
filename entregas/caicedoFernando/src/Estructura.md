# Estructura General

El proyecto está compuesto por **4 clases principales**:

- `Main` – Punto de entrada del programa.
- `Juego` – Controla la simulación del juego.
- `Cola` – Implementa una cola circular de niños.
- `Nino` – Representa a cada niño, su nombre y lo que escribe en la pizarra.

---

## 1 Clase `Main`

```java
public class Main {
    public static void main(String[] args) {
        Juego simulador = new Juego();
        simulador.ejecutarJuego();
    }
}
```

## 2 Clase `Juego`

- Atributos:

```java
private static final int PERIODO_INICIAL = 10;
private static final int LLEGADAS_PERMITIDAS = 2;
private static final int TIEMPO_LIMITE = 120;
private static final int FACTOR_MODULO = 3;
private static final int BONUS_TEMPORAL = 2;
private static final int CARACTERES_TEXTO = 10;
private static final int PERIODO_SECUNDARIO = 30;
private static final int CANTIDAD_MINIMA = 6;

private Cola filaEspera;
private int momentoActual;
private int duracionMaxima;
```

- `PERIODO_INICIAL`: Durante los primeros 10 minutos llegan varios niños por minuto.
- `LLEGADAS_PERMITIDAS`: Máximo número de niños que pueden llegar en un minuto rápido
- `TIEMPO_LIMITE`: Duración total del juego (120 minutos).
- `FACTOR_MODULO`: En la fase lenta, solo llegan niños si el minuto actual es múltiplo de este número.
- `BONUS_TEMPORAL`: Minutos extra consumidos cuando se juega una ronda.
- `CARACTERES_TEXTO`: Tamaño del mensaje generado (10 letras).
- `PERIODO_SECUNDARIO`: Define hasta qué minuto dura la fase lenta (30).
- `CANTIDAD_MINIMA`: Número mínimo de niños necesarios para iniciar la ronda del mensaje.

- Variables de control
- `fila\Espera`: Cola donde se enfilan los niños.
- `momentoActual`: Minuto en el que va la simulación.
- `duracionMaxima`: Tiempo total del juego.

- Constructor

```java
    public Juego() {
        filaEspera = new Cola();
        momentoActual = 0;
        duracionMaxima = TIEMPO_LIMITE;
    }
```

## 3 Clase `Cola`

- Atributos:

```java
    private Nino[] estructura;
    private int puntoInicio;
    private int puntoFinal;
    private int contadorElementos;
```

- `estructura`: Arreglo que guarda hasta 100 niños.
- `puntoInicio`: Índice del primer niño en la cola.
- `puntoFinal`: Índice donde se insertará el próximo niño.
- `contadorElementos`: Cantidad de niños en la cola.

## 4 Clase `Nino`

- Atributos:

```java
private String identificacion;
private String contenidoTablero;
```

- `identificacion`: Nombre único del niño (ejemplo: “Niño1”).
- `contenidoTablero`: Mensaje que el niño tiene escrito en su pizarra.
