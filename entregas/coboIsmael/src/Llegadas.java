import java.util.Random;

public class Llegadas {
    public static int llegadasEnMinuto(int minuto, Random azar) {
        int cantidad = 0;

        if (minuto >= 0 && minuto <= 9) {
            cantidad = azar.nextInt(3);
        } else if (minuto >= 10 && minuto <= 29) {
            int diferencia = minuto - 10;
            boolean esMultiploDe3 = (diferencia % 3 == 0);

            if (esMultiploDe3) {
                int moneda = azar.nextInt(2);
                if (moneda == 0) {
                    cantidad = 1;
                } else {
                    cantidad = 0;
                }
            } else {
                cantidad = 0;
            }
        } else {
            cantidad = 0;
        }

        return cantidad;
    }

    public static int contarLlegadasEntre(int minInicioIncluido, int minFinExcluido, Random azar) {
        int total = 0;
        int minuto = minInicioIncluido;

        while (minuto < minFinExcluido) {
            int llegadas = llegadasEnMinuto(minuto, azar);
            total = total + llegadas;
            minuto = minuto + 1;
        }

        return total;
    }
}
