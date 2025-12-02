public class HeapPedidos {
    private Pedido[] monticulo;
    private int tamano;
    private final int CAPACIDAD_MAXIMA = 100;
    private int comparaciones;

    public HeapPedidos() {
        this.monticulo = new Pedido[CAPACIDAD_MAXIMA];
        this.tamano = 0;
        this.comparaciones = 0;
    }

    private void intercambiar(int i, int j) {
        Pedido temporal = monticulo[i];
        monticulo[i] = monticulo[j];
        monticulo[j] = temporal;
    }

    
    private void reordenarAbajo(int indice) {
        boolean continuar = true; 

        while (continuar) {
            int hijoIzquierdo = 2 * indice + 1;
            int hijoDerecho = 2 * indice + 2;
            int menor = indice;

        
            if (hijoIzquierdo < tamano) {
                comparaciones++;
                if (monticulo[hijoIzquierdo].getTiempoPrep() < monticulo[menor].getTiempoPrep()) {
                    menor = hijoIzquierdo;
                }
            }

        
            if (hijoDerecho < tamano) {
                comparaciones++;
                if (monticulo[hijoDerecho].getTiempoPrep() < monticulo[menor].getTiempoPrep()) {
                    menor = hijoDerecho;
                }
            }

            
            if (menor != indice) {
                intercambiar(indice, menor);
                indice = menor;
            } else {
            
                continuar = false;
            }
        }
    }

    public void insertar(Pedido pedido) {
        if (tamano >= CAPACIDAD_MAXIMA) {
            System.out.println("ERROR: La cola está llena.");
        } else {
            monticulo[size()] = pedido;
            int indice = size();
            tamano++;

        
            boolean continuarFlotando = true;
            while (indice > 0 && continuarFlotando) {
                int indicePadre = (indice - 1) / 2;
                comparaciones++;

                if (monticulo[indice].getTiempoPrep() < monticulo[indicePadre].getTiempoPrep()) {
                    intercambiar(indice, indicePadre);
                    indice = indicePadre;
                } else {
                    continuarFlotando = false;
                }
            }
        }
    }

    public Pedido extraerMin() {
        Pedido min = null;
        if (tamano > 0) {
            min = monticulo[0];
            tamano--;
            
            if (tamano > 0) {
                monticulo[0] = monticulo[tamano];
                monticulo[tamano] = null;
                reordenarAbajo(0); 
            } else {
                monticulo[0] = null;
            }
        }
        return min;
    }

    public int size() {
        return tamano;
    }

    public int getComparaciones() {
        return comparaciones;
    }
}