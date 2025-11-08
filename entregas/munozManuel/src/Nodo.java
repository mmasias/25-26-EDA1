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
    
    public Object valor() {
        return switch (dato[1]) {
            case "char" -> datoChar();
            case "int" -> datoInt();
            case "boolean" -> datoBool();
            case "double" -> datoDouble();
            case "String" -> dato[0];
            default -> null;
        };
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

    private char datoChar() {
        return dato[0].charAt(0);
    }

    private int datoInt() {
        return Integer.parseInt(dato[0]);
    }

    private boolean datoBool() {
        return Boolean.parseBoolean(dato[0]);
    }

    private double datoDouble() {
        return Double.parseDouble(dato[0]);
    }

}
