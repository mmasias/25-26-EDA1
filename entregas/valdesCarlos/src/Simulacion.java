public class Simulacion {
    private static final int JORNADA_MINUTOS = 120; 

    public static void main(String[] args) {
        Restaurante rcccf = new Restaurante();

        System.out.println("========================================");
        System.out.println(" INICIO DE JORNADA RCCCF  ");
        System.out.println("========================================");

        for (int minuto = 1; minuto <= JORNADA_MINUTOS; minuto++) {
            rcccf.recibirPosiblePedido(minuto);
            rcccf.gestionarCocina(minuto);

            if (minuto <= 5 || minuto == JORNADA_MINUTOS) {
                rcccf.imprimirEstado(minuto);
            }
        }
        rcccf.imprimirResumenFinal();
    }
}