public class Lista {
    private Nodo[] nodos;
    private int actual;

    public Lista() {
        nodos = new Nodo[1];
        actual = -1;
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
        if(actual == nodos.length - 1){
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
        if(actual == nodos.length - 1){
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

        actual++;
        if (posicion == 0) {
            nodos[actual] = nodo;
            actualizarReferencias(posicion);
        } else if (posicion == 1) {
            nodos[actual] = nodo;
            actualizarReferencias(posicion);
        } else {
            nodos[actual] = nodo;
            actualizarReferencias(posicion);
        }
    }

    private void actualizarReferencias(int posicion){
        if(posicion == 0) {
            Nodo primerNodo = primerNodo();

            nodos[actual].anterior(-1);
            nodos[actual].siguiente(posicionEnArray(primerNodo));
            primerNodo.anterior(actual);
        }else if (posicion == 1) {
            if (actual > 0){
                Nodo ultimNodo = ultimoNodo();

                nodos[actual].siguiente(-1);
                nodos[actual].anterior(posicionEnArray(ultimNodo));
                ultimNodo.siguiente(actual);
            }
        } else {
            Nodo nodoAnterior = buscarNodo(posicion - 1);
            Nodo nodoSiguiente = buscarNodo(posicion);

            nodoAnterior.siguiente(actual);

            nodos[actual].anterior(posicionEnArray(nodoAnterior));
            nodos[actual].siguiente(posicionEnArray(nodoSiguiente));
            
            nodoSiguiente.anterior(actual);
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
        if(actual > 0){
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
