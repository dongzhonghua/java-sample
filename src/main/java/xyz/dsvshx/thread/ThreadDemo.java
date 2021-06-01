package xyz.dsvshx.thread;

/**
 * @author dongzhonghua
 * Created on 2021-05-30
 */
public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ">>>>>main方法执行");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 正在执行");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 正在执行");
            }
        });

        thread.start();
        thread2.start();
    }
}
