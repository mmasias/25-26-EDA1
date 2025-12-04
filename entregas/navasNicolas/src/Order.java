public class Order {
    private String type;
    private int totalTime;
    private int remainingTime; 
    private double arrivalTime;
    
    public Order(String type, int time, double arrivalTime) {
        this.type = type;
        this.totalTime = time;
        this.remainingTime = time;
        this.arrivalTime = arrivalTime;
    }

    public String getType() {
        return type;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void decrementRemainingTime() {
        this.remainingTime--;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public String toString() {
        return type + " (" + remainingTime + " min rest)";
    }
}