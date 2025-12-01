import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;

public class Kitchen 
{
    public ArrayList<Order> queue = new ArrayList<>();
    private TreeMap<Integer, ArrayList<Order>> bst = new TreeMap<>();
    private Random rng = new Random();

    public int servedOrders = 0;
    public int totalWaitingTime = 0;
    public int comparisons = 0;

    private Order currentOrder = null;

    public Order generateRandomOrder(int minute)
    {
        String[] types = {"Drink", "Coffee","Chocolate Milk","Sandwich","Salad"};
        String t = types[rng.nextInt(types.lenth)];
        int prepTime;

        switch (t) {
            case "Drink": prepTime = rng.nextInt(2) + 1; break;
            case "Coffee": prepTime = rng.nextInt(2) + 2; break;
            case "Chocolate Milk": prepTime = rng.nextInt(3) + 2; break;
            case "Sandwich": prepTime = rng.nextInt(3) + 3; break;
            case "Salad": prepTime = rng.nextInt(4) + 5; break;
            default: prepTime = 5;
        }

        return new Order(t, prepTime, minute);

    }

    public Order extractShortestOrder()
    {
        if (queue.isEmpty()) return null;

        int minute = bst.firstKey();
        Order o = bst.get(minTime).remove(0);

        if (bst.get(minTime).isEmpty()) bst.remove(min.Time);

        int index = queue.indexOf(o);
        comparisons += index + 1;
        queue.remove(index);

        return o;
    }



    public void processOneMinute()
    {
        if (currentOrder == null) {
            currentOrder = extractShortestOrder();
    }

    if (currentOrder != null)
    {
        currentOrder.remainingTime--;
        if (currentOrder.remainingTime == 0)
        {
            servedOrders++;
            currentOrder = null;
        }
    }

    for(Order o : queue)
    {
        totalWaitingTime++;
    }

    }

    public int getPendingCount() { return queue.size();}
    public Order getCurrentOrder() {return currentOrder;}
    
}
