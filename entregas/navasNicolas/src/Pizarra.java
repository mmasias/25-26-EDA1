public class Pizarra {
    private String letras = "";

    public void setLetras(String letras) {
        this.letras = letras;
    }

    public String getLetras() {
        return letras;
    }

    public void mostrar() {
        System.out.println("Pizarra: " + letras);
    }
}