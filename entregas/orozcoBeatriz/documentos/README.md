# Refactorización del sistema RCCCF con árbol binario

En esta refactorización he sustituido la lista enlazada por un árbol binario de búsqueda.  
La lista obligaba a recorrer todos los elementos para mantener el orden de los pedidos.  
El árbol, en cambio, coloca cada pedido automáticamente según su tiempo de preparación, los más pequeños a la izquierda y los más grandes a la derecha.

Esto deja el diseño más simple y más limpio.  
El árbol se encarga del orden, obtener el pedido más corto es directo y el sistema queda más minimalista y organizado.