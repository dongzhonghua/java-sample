package xyz.dsvshx.concurrent;

/**
 * @author dongzhonghua
 * Created on 2021-02-20
 */
public class MyLatch {
    private int total = 0;
    private int counter = 0;

    public MyLatch(int total) {
        this.total = counter;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }
}
