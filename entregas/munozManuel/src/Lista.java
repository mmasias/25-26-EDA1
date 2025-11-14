public class Lista {
    private Nodo[] nodos;
    private int cabeza;

    public Lista() {
        nodos = new Nodo[1];
        cabeza = -1;
    }

    public Nodo obtener(int posicion){
        if(posicion == 1){
            return primerNodo();
        }else if (posicion == nodos.length) {
            return ultimoNodo();
        }else{
            return buscarNodo(posicion);
        }
    }

    public void agregarInicio(Nodo nodo) {
        redimensionar();
        ingresarDato(nodo, 0);
    }

    public void agregarFinal(Nodo nodo) {
        redimensionar();
        ingresarDato(nodo, 1);
    }

    public void insertar(Nodo nodo, int posicion){ 
        if(cabeza == nodos.length - 1){
            redimensionar();
            ingresarDato(nodo, posicion);
        }else{
            ingresarDato(nodo, posicion);
        }
    }

    public void eliminar(int posicion) {
        if(posicion >= 1 && posicion <= nodos.length){
            Nodo nodoAeliminar = buscarNodo(posicion);
            nodoAeliminar.eliminarDato();
        }
    }
    
    public void limpiarLista(){
        for(Nodo nodo : nodos){
            if (nodo != null){
                nodo.eliminarDato();
            }
        }
    }

    public void actualizar(char dato, int posicion){
        buscarNodo(posicion).actualizarDato(dato);
    }

    public void actualizar(int dato, int posicion){
        buscarNodo(posicion).actualizarDato(dato);
    }

    public void actualizar(boolean dato, int posicion){
        buscarNodo(posicion).actualizarDato(dato);
    }

    public void actualizar(double dato, int posicion){
        buscarNodo(posicion).actualizarDato(dato);
    }

    public void actualizar(String dato, int posicion){
        buscarNodo(posicion).actualizarDato(dato);
    }
    
    public void imprimirLista() {
        Nodo nodoActual = primerNodo();
        int indice = 1;

        while (nodoActual.siguiente() != -1) {
            if (!nodoActual.dato().equals("")) {
                System.out.println(indice + ": " + nodoActual.dato());
                indice++;
            }
            nodoActual = nodos[nodoActual.siguiente()];
        }
        
        if (!nodoActual.dato().equals("")) {
            System.out.println(indice + ": " + nodoActual.dato());
        }
    }

    public void imprimirListaDetallada(){
        for(int i = 0; i < nodos.length; i++){
            if (nodos[i] != null){
                System.out.println(i + ":");
                System.out.println("dato: " + nodos[i].dato());
                System.out.println("anterior: " + nodos[i].anterior());
                System.out.println("siguiente: " + nodos[i].siguiente());
            }
        }
    }

    private void redimensionar() {
        if(cabeza == nodos.length - 1){
            Nodo[] nuevosNodos = new Nodo[nodos.length + 5];
            System.arraycopy(nodos, 0, nuevosNodos, 0, nodos.length);
            nodos = nuevosNodos;
        }
    }

    private void ingresarDato(Nodo nodo, int posicion){
        Nodo nodoVacio = buscarNodoVacio();
        if(nodoVacio != null){
            int indiceNodoVAcio = posicionEnArray(nodoVacio);
            nodos[indiceNodoVAcio].actualizarDato(nodo.dato());
            return;
        }

        cabeza++;
        if (posicion == 0) {
            nodos[cabeza] = nodo;
            actualizarReferencias(posicion);
        } else if (posicion == 1) {
            nodos[cabeza] = nodo;
            actualizarReferencias(posicion);
        } else {
            nodos[cabeza] = nodo;
            actualizarReferencias(posicion);
        }
    }

    private void actualizarReferencias(int posicion){
        if(posicion == 0) {
            Nodo primerNodo = primerNodo();

            nodos[cabeza].anterior(-1);
            nodos[cabeza].siguiente(posicionEnArray(primerNodo));
            primerNodo.anterior(cabeza);
        }else if (posicion == 1) {
            if (cabeza > 0){
                Nodo ultimNodo = ultimoNodo();

                nodos[cabeza].siguiente(-1);
                nodos[cabeza].anterior(posicionEnArray(ultimNodo));
                ultimNodo.siguiente(cabeza);
            }
        } else {
            Nodo nodoAnterior = buscarNodo(posicion - 1);
            Nodo nodoSiguiente = buscarNodo(posicion);

            nodoAnterior.siguiente(cabeza);

            nodos[cabeza].anterior(posicionEnArray(nodoAnterior));
            nodos[cabeza].siguiente(posicionEnArray(nodoSiguiente));
            
            nodoSiguiente.anterior(cabeza);
        }
    }

    private int posicionEnArray(Nodo nodo){
        for(int i = 0; i < nodos.length; i++){
            if (nodos[i].dato().equals(nodo.dato()) && nodos[i].siguiente() == nodo.siguiente()){
                return i;
            }
        }
        return -1;
    }

    private Nodo buscarNodo(int posicion){
        Nodo nodoABuscar = primerNodo();
        if(posicion > 0 && posicion <= nodos.length){
            do{
                nodoABuscar = nodos[nodoABuscar.siguiente()];
                if(nodoABuscar.dato().length() > 0){
                    posicion--;
                }          
            }while (posicion > 1);
        }
        return nodoABuscar;
    }

    private Nodo primerNodo(){
        for(Nodo nodo : nodos){
            if (nodo.anterior() == -1){
                return nodo;
            }
        }
        return null;
    }

    private Nodo ultimoNodo(){
        for(Nodo nodo : nodos){
            if (nodo.siguiente() == -1){
                return nodo;
            }
        }
        return null;
    }

    private Nodo buscarNodoVacio(){
        if(cabeza > 0){
            Nodo nodoVacio = primerNodo();
            do{
                if (nodoVacio.dato().equals("")){
                    return nodoVacio;
                }
                nodoVacio = nodos[nodoVacio.siguiente()];
            }while (nodoVacio.siguiente() != -1);
        }
        return null;
    }

}
