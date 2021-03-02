package xyz.dsvshx.ratelimiter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * @author dongzhonghua
 * Created on 2021-03-02
 */
public class SemaphoreRateLimiter {
        private static final Semaphore semaphore = new Semaphore(4);

        public static void bizMethod() throws InterruptedException {
            if (!semaphore.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + "被拒绝");
                return;
            }

            System.out.println(Thread.currentThread().getName() + "执行业务逻辑");
            Thread.sleep(1000);//模拟处理业务逻辑需要1秒
            // semaphore.release();
        }

        public static void main(String[] args) {

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // semaphore.release(4);
                    System.out.println("释放所有锁");
                }
            }, 1000, 1000);

            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);//模拟每隔100ms就有1个请求进来, 一秒钟10个
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(() -> {
                    try {
                        SemaphoreRateLimiter.bizMethod();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
