
public class Call implements Runnable {
    private Tower[] towers;
    private Coordinates position;

    public Call(Tower[] towers, Coordinates position) {
        this.towers = towers;
        this.position = position;
    }

    @Override
    public void run() {
        try {
            for (Tower tower : towers) {
                if (tower.getPosition().distance(position) < tower.getDistance()) {
                    tower.processCall();
                }
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
