public class Lista extends ListaAbstracta {
    
    public Lista(int capacidadInicial) { 
        this.NUMERO_MAXIMO = capacidadInicial; 
        this.primerNodo = null;
    }
    
    public Lista() {
        this(0); 
    }

    public void agregar(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento, tamaño()); 
        
        if (primerNodo == null) {
            primerNodo = nuevoNodo;
        } else {
            Nodo actual = (Nodo) primerNodo;
            while (actual.getSiguiente() != null) {
                actual = (Nodo) actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }
    
    public void agregar(int indice, Object elemento) {
        int tamañoActual = tamaño();
        if (indice < 0 || indice > tamañoActual) {
            System.out.println("Índice fuera de rango para inserción.");
            return;
        }
        
        Nodo nuevoNodo = new Nodo(elemento, indice);
        
        if (indice == 0) {
            nuevoNodo.setSiguiente(primerNodo);
            primerNodo = nuevoNodo;
        } else {
            Nodo previo = (Nodo) get(indice - 1); 
            if (previo == null) {
                 return; 
            }
            nuevoNodo.setSiguiente(previo.getSiguiente());
            previo.setSiguiente(nuevoNodo);
        }
        
        recalcularPosiciones();
    }
    
    public NodoAbstracto get(int indice) {
        if (indice < 0 || indice >= tamaño()) {
            return null;
        }
        
        Nodo actual = (Nodo) primerNodo;
        while (actual != null) {
            if (actual.getPosicion() == indice) {
                return actual;
            }
            actual = (Nodo) actual.getSiguiente(); 
        }
        return null; 
    }
    
    public Object obtener(int indice) {
        NodoAbstracto nodo = get(indice);
        return (nodo != null) ? nodo.getDato() : null;
    }
    
    public Object remover(int indice) {
        int tamañoActual = tamaño();
        boolean rango = indice < 0 || indice >= tamañoActual;
        if (rango) {
            System.out.println("Índice fuera de rango para eliminación.");
            return null;
        }

        Nodo nodoEliminado;
        
        if (indice == 0) {
            nodoEliminado = (Nodo) primerNodo; 
            primerNodo = primerNodo.getSiguiente();
        } else {
            Nodo previo = (Nodo) get(indice - 1);
            nodoEliminado = (Nodo) previo.getSiguiente();
            
            previo.setSiguiente(nodoEliminado.getSiguiente()); 
        }

        recalcularPosiciones();
        
        return nodoEliminado.getDato();
    }

    private void recalcularPosiciones() {
        Nodo actual = (Nodo) primerNodo; 
        int nuevaPosicion = 0;
        while (actual != null) {
            actual.posicion = nuevaPosicion++;
            actual = (Nodo) actual.getSiguiente();
        }
    }
    
    public int tamaño() {
        int contador = 0;
        NodoAbstracto actual = primerNodo;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
    
    public int size() {
        return tamaño();
    }
    
    public int obtenerCapacidad() {
        return -1; 
    }
    
    public String toString() {
        String texto = "[";
        Nodo actual = (Nodo) primerNodo; 
        while (actual != null) {
            texto += actual.getDato();
            if (actual.getSiguiente() != null) {
                texto += ", ";
            }
            actual = (Nodo) actual.getSiguiente();
        }
        texto += "]";
        return texto;
    }
    

    public void apilar(Object elemento) {
        agregar(elemento);
    }

    public Object desapilar() {
        if (tamaño() == 0) {
            System.out.println("Error: La pila está vacía.");
            return null;
        }
        return remover(tamaño() - 1);
    }
    

    public void encolar(Object elemento) {
        agregar(elemento);
    }

    public Object desencolar() {
        if (tamaño() == 0) {
            System.out.println("Error: La cola está vacía.");
            return null;
        }
        return remover(0);
    }
}