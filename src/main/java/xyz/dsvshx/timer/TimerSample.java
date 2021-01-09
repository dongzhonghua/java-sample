package xyz.dsvshx.timer;


import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerSample {
    public static void main(String[] args) {
        // new Timer()之后就会启动TimerThread的main loop
        // main loop 则会从这个queue中循环读取时间最近的值。
        Timer timer = new Timer();
        AtomicInteger task1 = new AtomicInteger(0);
        AtomicInteger task2 = new AtomicInteger(0);
        //每隔1秒调用一次
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("test1:" + task1 + new Date().toString());
                task1.getAndIncrement();
            }
        };

        timer.schedule(task, 0, 1000);

        //每隔3秒调用一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test2:" + task2 + new Date().toString());
                task2.getAndIncrement();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 3000);

    }

}
