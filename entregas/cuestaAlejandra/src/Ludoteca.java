public class Ludoteca {
    public static void main(String[] args) {
        Lidia lidia = new Lidia();
        List<Nino> todos = lidia.recibirNinos();

        Aisha aisha = new Aisha(todos);
        aisha.pedirPresentaciones();
        aisha.presentarPorEdad(6);
        aisha.presentarPorLetra('A');
        aisha.mostrarCincoPrimeros();
        aisha.mostrarCincoUltimos();
        aisha.calcularEdadPromedio();

        List<Nino> menores = aisha.separarMenores();
        List<Nino> mayores = aisha.mayoresParaJugar();

        Dalsy dalsy = new Dalsy(menores);
        dalsy.cuidar();

        JuegoRana juego = new JuegoRana();
        juego.jugar(mayores);

        
        aisha.evacuar(menores, mayores, lidia);
    }
}