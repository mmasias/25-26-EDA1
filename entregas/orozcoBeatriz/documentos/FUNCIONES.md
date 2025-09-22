#FUNCION DE CADA CLASE

## `Nino`
- Guardar su nombre y el mensaje de su pizarrín.  
- Limpiar su pizarrín.  
- Recibir un mensaje y aplicarle la dinámica del teléfono.  
- Pasar el mensaje al siguiente.  

---

## `Fila`
- Mantener la cola de niños en orden de llegada.  
- Añadir niños al final de la fila.  
- Dar acceso a los niños y al tamaño de la cola.  

---

## `TextoAleatorio`
- Generar un texto de 10 letras mayúsculas aleatorias.  

---

## `TelefonoDescacharrado`
- Deformar un mensaje cambiando 0, 1 o 2 letras al azar.  
- Reconstruir el mensaje resultante carácter por carácter.  

---

## `Juego`
- Ejecutar una partida completa.  
- Limpiar pizarrines antes de empezar.  
- Mostrar el mensaje original.  
- Pasar el mensaje niño a niño, aplicando deformaciones.  
- Mostrar el mensaje final en la pizarra del salón.  

---

## `Lidia`
- Recibir a los niños que llegan.  
- Pasar los niños a Aisha si no hay juego.  
- Retener niños si llega alguno durante un juego.  
- Liberar los niños retenidos al terminar la partida.  

---

## `Aisha`
- Colocar a los niños al final de la fila.  
- Iniciar un juego cuando se cumplen las condiciones.  

---

## `Ludoteca`
- Simular 120 minutos de actividad en la ludoteca.  
- Controlar el ritmo de llegada de los niños:  
  - 0–9 min: 0–2 niños/minuto.  
  - 10–29 min: un niño cada 3 minutos (50%).  
  - ≥30 min: no llegan más niños.  
- Iniciar un juego si hay al menos 5 niños en la fila.  
- Calcular la duración de cada juego (nº de niños + 2).  
- Gestionar llegadas durante el juego (retenidas por Lidia).  
- Pasar los niños retenidos a la fila al acabar el juego.  

---

## `SimuladorLudoteca`
- Crear el objeto `Ludoteca`.  
- Lanzar la simulación completa.  
