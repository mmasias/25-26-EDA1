package zzzotros.rcccf;

class Restaurant {
    private final double OPENING_TIME = 9.0;
    private final double CLOSING_TIME = 21.0;
    private final double MINUTE = 1.0 / 60.0;
    private final double PROBABILITY_ARRIVAL = 0.4;
    private final int NUM_COCINEROS = 1;

    public void run() {
        for (double time = OPENING_TIME; time < CLOSING_TIME; time = time + MINUTE) {
            validateArrival();
            processQueue();
            showSummary();
        }
    }

    private void showSummary() {

    }

    private void processQueue() {
        
    }

    private void validateArrival() {

    }
}
