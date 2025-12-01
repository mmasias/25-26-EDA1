public class Order {

    private static int globalId = 0;

    public final int id;
    public final DishType type;
    public final int initialTime;
    public int remaining;
    public int arrivalMinute;
    public int waitTime = 0;

    public Order(DishType type, int prepTime, int arrivalMinute) {
        this.id = ++globalId;
        this.type = type;
        this.initialTime = prepTime;
        this.remaining = prepTime;
        this.arrivalMinute = arrivalMinute;
    }

    @Override
    public String toString() {
        return type + " (" + remaining + " min)";
    }
}
