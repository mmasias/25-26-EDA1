public class Order {

    private String type;
    private int totalTime;
    private int remaningTime;
    public int arrivalMinute;


    public Order(String type, int prepTime, int arrivalMinute) 
    {
        this.type = type;
        this.totalTime = prepTime;
        this.remainingTime = prepTime;
        this.arrivalMinute = arrivalMinute;


    }


@Override
public String toString() 
{
    return type + " (" + remainingTime + " min";

}

}
