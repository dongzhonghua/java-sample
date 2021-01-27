package xyz.dsvshx.threadLocal;

/**
 * @author dongzhonghua
 * Created on 2021-01-24
 */
public class Client {
    private static final ThreadLocal<String> myThreadLocal = ThreadLocal.withInitial(() -> "This is the initial value");

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++){
            new Thread(new MyRunnable(), "线程"+i).start();
        }

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "的threadLocal"+ ",设置为" + name);
            myThreadLocal.set(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            System.out.println(name + ":" + myThreadLocal.get());
        }

    }
}
