##  **Así Funcionará la Cocina**

Voy a modelar el sistema para que corra durante **120 minutos**. En cada minuto, el programa simulará la llegada de nuevos clientes: pondré una **probabilidad del 40%** de que entre un nuevo pedido.

Tendré que definir el **menú** (Bebida, Café, Ensalada, etc.), donde cada plato tendrá un **tiempo de preparación al azar dentro de su rango**.  
La regla de oro que programaré para el cocinero será simple: **si acaba un plato, mirará la lista de espera y elegirá el que tenga el menor tiempo de preparación**.

---

##  El Dilema de la Lista de Espera

El mayor desafío técnico que tendré que resolver es cómo manejar eficientemente la lista de pedidos pendientes.  
Si llegan, digamos, **50 pedidos**, y cada vez que el cocinero está libre tengo que recorrer los 50 para encontrar el más rápido, el sistema se hará lento.

La herramienta perfecta para esta tarea sería una lista que se ordena automáticamente: una **Cola de Prioridad**.  
Con ella, insertar o extraer el más rápido sería prácticamente instantáneo.

**Pero…**  
El reto me pide contar exactamente **cuántas comparaciones** hace el programa para encontrar el pedido más rápido.  
Si uso la Cola de Prioridad, esas comparaciones quedan ocultas.

---

##  La Solución que Implementaré

Para cumplir con esta métrica, tomaré una decisión estratégica:

 **Ignoraré la Cola de Prioridad y usaré una lista normal (ArrayList en Java).**

Esto implica:

- Yo mismo mantendré la lista ordenada.
- Cada vez que llegue un nuevo pedido, **recorreré la lista para insertar en el lugar correcto**.
- En ese proceso, **contaré exactas las comparaciones**.

Este método es teóricamente más lento, pero me permite obtener la métrica que el ejercicio exige.

---

##  Arquitectura de Clases

Dividiré el proyecto en **cinco clases**, ordenadas en sus paquetes:

### **1. Pedido.java**
- Representa una orden.
- Guarda:
  - tipo de plato,
  - tiempo total necesario,
  - minuto en que llegó.
- Reducirá su tiempo de preparación minuto a minuto.

### **2. Plato.java**
- Clase auxiliar para definir el menú.
- Guarda rangos de tiempo (ej.: Bebida = 1 a 2 min).
- Genera un tiempo real al azar dentro del rango.

### **3. RestauranteRCCCF.java**
- Clase principal de la simulación.
- Contendrá:
  - bucle de los 120 minutos,
  - probabilidad de llegada de clientes,
  - lógica de la cola y conteo de comparaciones,
  - control del estado del cocinero.

### **4. Mundo.java**
- Contiene el `main`.
- Crea la instancia del restaurante y ejecuta la simulación.

### **5. utils/Console.java**
- Clase de utilidad para entrada y salida.
- Hará la interfaz por consola más limpia.

---

##  Resultado Final

La simulación se ejecutará minuto a minuto, mostrando:

- llegadas de pedidos,
- qué plato está preparando el cocinero,
- cuántos quedan en cola.

Al final, generará un resumen con:

- platos atendidos,
- platos pendientes,
- tiempo promedio de espera,
- **total de comparaciones realizadas**.

