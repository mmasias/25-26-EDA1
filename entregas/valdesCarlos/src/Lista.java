public class Lista {
    private int[] datos; 
    private int tamaño;  

    public Lista() {
        this.datos = new int[10]; 
        this.tamaño = 0;
    }

    public void agregar(int valor) {
        
        if (tamaño >= datos.length) {
            redimensionar();
        }
        datos[tamaño] = valor;
        tamaño++;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño 
               : "Mal: el índice está fuera de rango";
        return datos[indice];
    }

    public void modificar(int indice, int nuevoValor) {
        assert indice >= 0 && indice < tamaño 
               : "Mal: el índice está fuera de rango";
        datos[indice] = nuevoValor;
    }

    public int tamaño() {
        return tamaño;
    }

    
    private void redimensionar() {
        int[] nuevosDatos = new int[datos.length * 2];
        for (int i = 0; i < datos.length; i++) {
            nuevosDatos[i] = datos[i];
        }
        datos = nuevosDatos;
    }
}