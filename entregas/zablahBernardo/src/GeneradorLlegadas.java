public class GeneradorLlegadas {
    public int ninosQueLlegan(int minuto) {
        if (minuto < 10) {
            return (int)(Math.random() * 3);
        } else if (minuto < 30) {
            if (minuto % 3 == 0) {
                return (Math.random() < 0.5) ? 1 : 0;
            }
        }
        return 0;
    }
}
