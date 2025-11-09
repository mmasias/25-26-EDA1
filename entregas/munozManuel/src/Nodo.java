public class Nodo {
    private final String[] dato = new String[2];
    private int anterior = -1;
    private int siguiente = -1;

    public Nodo(char dato) {
        this.dato[0] = dato + "";
        this.dato[1] = "char";
    }

    public Nodo(int dato) {
        this.dato[0] = dato + "";
        this.dato[1] = "int";
    }

    public Nodo(boolean dato) {
        this.dato[0] = dato + "";
        this.dato[1] = "boolean";
    }

    public Nodo(double dato) {
        this.dato[0] = dato + "";
        this.dato[1] = "double";
    }

    public Nodo(String dato) {
        this.dato[0] = dato;
        this.dato[1] = "String";
    }

    public void actualizarDato(char dato){
        this.dato[0] = dato + "";
        this.dato[1] = "char";
    }

    public void actualizarDato(int dato){
        this.dato[0] = dato + "";
        this.dato[1] = "int";
    }

    public void actualizarDato(boolean dato){
        this.dato[0] = dato + "";
        this.dato[1] = "boolean";
    }

    public void actualizarDato(double dato){
        this.dato[0] = dato + "";
        this.dato[1] = "double";
    }

    public void actualizarDato(String dato){
        this.dato[0] = dato;
        this.dato[1] = "String";
    }

    public void eliminarDato(){
        dato[0] = "";
        anterior = -1;
        siguiente = -1;
    }

    public int anterior(){
        return anterior;
    }

    public int siguiente(){
        return siguiente;
    }

    public void anterior(int indice) {
        this.anterior = indice;
    }

    public void siguiente(int indice) {
        this.siguiente = indice;
    }

    public String dato(){
        return dato[0];
    }

    public char datoChar() {
        return dato[0].charAt(0);
    }

    public int datoInt() {
        return Integer.parseInt(dato[0]);
    }

    public boolean datoBool() {
        return Boolean.parseBoolean(dato[0]);
    }

    public double datoDouble() {
        return Double.parseDouble(dato[0]);
    }

}
