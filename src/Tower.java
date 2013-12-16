import java.util.concurrent.atomic.AtomicInteger;

public class Tower {
    private Coordinates position;
    private int distance;
    private AtomicInteger callsProcessed;

    public Coordinates getPosition(){
        return position;
    }

    public int getDistance(){
        return distance;
    }

    public void processCall(){
        callsProcessed.incrementAndGet();
    }

    public Tower(Coordinates position, int distance){
        this.position = position;
        this.distance = distance;
        this.callsProcessed = new AtomicInteger(0);
    }
}
