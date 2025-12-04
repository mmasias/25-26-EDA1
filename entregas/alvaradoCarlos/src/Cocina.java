public class Cocina {
    private ArbolBinario arbol;
    private Pedido enProceso;
    private int completados = 0;
    private int contadorPendientes = 0;

    public Cocina() {
        this.arbol = new ArbolBinario();
    }

    public void recibirPedido(Pedido pedido) {
        arbol.insertar(pedido);
        contadorPendientes++;
    }

    public void trabajar() {
        if (enProceso == null && !arbol.esVacio()) {
            enProceso = arbol.extraerMasRapido();
        }

        if (enProceso != null) {
            enProceso.cocinar();
            if (enProceso.tiempoRestante() <= 0) {
                completados++;
                enProceso = null;
            }
        }
    }

    public void asignarTrabajo() {
        if (enProceso == null && !arbol.esVacio()) {
            enProceso = arbol.extraerMasRapido();
            contadorPendientes--;
        }
    }

    public int pendientes() { 
        return contadorPendientes; 
    }

    public int completados() { 
        return completados; 
    }

    public String estado() { 
        return (enProceso == null) ? "Libre" : enProceso.toString(); 
    }

    public int eficiencia() { 
        return arbol.comparaciones(); 
    }
}