package NuestraPropuesta;

public class Main {
    public static void main(String[] args) {
        Array array = new Array();
        array.fijarTamaño(5);   
        array.mostrar();         

        array.modificar(2, 10); 
        array.mostrar();         

        array.Acceso(2);        
    }
}

