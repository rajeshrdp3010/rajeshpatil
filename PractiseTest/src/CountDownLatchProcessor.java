import java.util.concurrent.CountDownLatch;

public class CountDownLatchProcessor implements Runnable {

    private CountDownLatch latch;

    public CountDownLatchProcessor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Started.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
    }

}
