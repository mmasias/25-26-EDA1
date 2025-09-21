public class Pizarra {

    private char[] mensaje = new char[10];

    public void setMensaje(char[] msg) {
        mensaje = msg.clone();
    }

    public void limpiar() {
        mensaje = new char[10];
    }

    public void imprimir() {
        System.out.println(new String(mensaje));
    }

}
