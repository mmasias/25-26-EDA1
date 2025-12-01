public class Order {
    String type;
    int totalTime;
    int remainingTime; 
    double arrivalTime;
    public Order(String type, int time, double arrivalTime) {
        this.type = type;
        this.totalTime = time;
        this.remainingTime = time;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return type + " (" + remainingTime + " min rest)";
    }
}