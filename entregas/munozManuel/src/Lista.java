public class Lista {
    private Nodo[] nodos;
    private int actual;

    public Lista() {
        nodos = new Nodo[1];
        actual = -1;
    }

    public String leerDato(int posicion){
        if(posicion == 0){
            return primerNodo().dato();
        }else if (posicion == nodos.length - 1) {
            return ultimoNodo().dato();
        }else{
            for(int i = 0; i < posicion; i++){
                if(nodos[i].siguiente() == posicion){
                    return nodos[nodos[i].siguiente()].dato();
                }
            }
        }
        return "";
    }

    public void agregarInicio(Nodo nodo) {
        if(actual == nodos.length - 1) {
            redimensionar();
            ingresarDato(nodo, 0);
        } else {
            ingresarDato(nodo, 0);
        }
    }

    public void agregarFinal(Nodo nodo) {
        if(actual == nodos.length - 1) {
            redimensionar();
            ingresarDato(nodo, 1);
        } else {
            ingresarDato(nodo, 1);
        }
    }

    public void insertar(Nodo nodo, int posicion){
        if(actual == nodos.length - 1){
            redimensionar();
            ingresarDato(nodo, posicion);
        }else{
            ingresarDato(nodo, posicion);
        }
    }

    public void eliminar(int indice) {
        if(indice >= 0 && indice <= actual){
            nodos[indice].eliminarDato();
        }
    }
    
    public void actualizar(char dato, int indice){
        nodos[indice].actualizarDato(dato);
    }

    public void actualizar(int dato, int indice){
        nodos[indice].actualizarDato(dato);
    }

    public void actualizar(boolean dato, int indice){
        nodos[indice].actualizarDato(dato);
    }

    public void actualizar(double dato, int indice){
        nodos[indice].actualizarDato(dato);
    }

    public void actualizar(String dato, int indice){
        nodos[indice].actualizarDato(dato);
    }
    
    public void imprimirLista(){
        Nodo primerNodo = primerNodo();
        int posicion = posicion(primerNodo.dato());
        int elemetoLista = 1;
        do { 
            System.out.println(elemetoLista +": " + nodos[posicion].dato());
            posicion = nodos[posicion].siguiente();
            elemetoLista++;
        } while (posicion != -1);
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
        Nodo[] nuevosNodos = new Nodo[nodos.length + 5];
        System.arraycopy(nodos, 0, nuevosNodos, 0, nodos.length);
        nodos = nuevosNodos;
    }

    private void ingresarDato(Nodo nodo, int posicion){
        actual++;
        switch (posicion) {
            case 0 -> {
                nodos[actual] = nodo;
                actualizarReferencias(posicion);
            }
            case 1 -> {
                nodos[actual] = nodo;
                actualizarReferencias(posicion);
            }
            default ->{
                nodos[actual] = nodo;
                actualizarReferencias(posicion);
            }
        }
    }

    private void actualizarReferencias(int posicion){
        
        switch (posicion) {
            case 0 -> {
                Nodo primerNodo = primerNodo();

                nodos[actual].anterior(-1);
                nodos[actual].siguiente(posicion(primerNodo.dato()));
                primerNodo.anterior(actual);
            }
            case 1 -> {
                if (actual > 0){
                    Nodo ultimNodo = ultimoNodo();

                    nodos[actual].siguiente(-1);
                    nodos[actual].anterior(posicion(ultimNodo.dato()));
                    ultimNodo.siguiente(actual);
                }
            }
            default -> {
                Nodo nodoSiguiente = nodoSiguiente(posicion);
                Nodo nodoAnterior = nodoAnterior(nodoSiguiente);

                nodoAnterior.siguiente(actual);

                nodos[actual].anterior(posicion(nodoAnterior.dato()));
                nodos[actual].siguiente(posicion(nodoSiguiente.dato()));
                
                nodoSiguiente.anterior(actual);
            }
        }
    }

    private int posicion(String valor){
        for(int i = 0; i < nodos.length; i++){
            if (nodos[i].dato().equals(valor)){
                return i;
            }
        }
        return -1;
    }

    private Nodo buscarNodo(int posicion){
        for (int i = 0; i < nodos.length; i++) {
            if(i == posicion - 1){
                return nodos[i];
            }
        }
        return null;
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

    private Nodo nodoSiguiente(int posicion){
        Nodo nodoSiguente = buscarNodo(posicion - 1);
        return nodoSiguente;

    }

    private Nodo nodoAnterior(Nodo nodoSiguiente){
        Nodo nodoAnterior = buscarNodo(nodoSiguiente.anterior() + 1);
        return nodoAnterior;
    }
    
}
