package xyz.dsvshx.concurrent.threadPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dongzhonghua
 * Created on 2021-02-14
 */
@Slf4j
public class SimpleThreadPool extends Thread {
    private int currentSize;
    private DiscardPolicy discardPolicy;
    private int queueSize;
    private int min;
    private int active;
    private int max;
    private boolean isDistroy = false;

    private static final int DEFAULT_SIZE = 4;
    private static final int DEFAULT_QUEUE_SIZE = 30;
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    private static final List<WorkerThread> THREAD_QUEUE = new ArrayList<>();
    private static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new RuntimeException("任务数量超出限制");
    };

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.currentSize = min;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public void init() {
        this.start();
        for (int i = 0; i < currentSize; i++) {
            createWorker();
        }
    }

    public void createWorker() {
        WorkerThread workerThread = new WorkerThread();
        workerThread.start();
        THREAD_QUEUE.add(workerThread);
    }

    private enum WorkerThreadState {
        FREE,
        BLOCK,
        RUNNING,
        DEAD
    }

    /**
     * 提交任务
     */
    public void submit(Runnable task) {
        synchronized (TASK_QUEUE) {
            // 当前队列中的任务书超出预定的数量就拒绝
            if (TASK_QUEUE.size() > queueSize) {
                log.info("当前任务的数量超出预期：{}", TASK_QUEUE.size());
                this.discardPolicy.discard();
            }
            TASK_QUEUE.addLast(task);
            TASK_QUEUE.notifyAll();
        }
    }

    public void shutdown() throws InterruptedException {
        if (isDistroy) {
            throw new RuntimeException("线程池已经关闭");
        }
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(500);
        }
        // synchronized (THREAD_QUEUE) {
        int threadSize = THREAD_QUEUE.size();
        while (threadSize > 0) {
            for (WorkerThread worker : THREAD_QUEUE) {
                // 当线程状态为RUNNING的时候不能关闭
                if (worker.state == WorkerThreadState.BLOCK) {
                    worker.interrupt();
                    worker.close();
                    threadSize--;
                } else {
                    Thread.sleep(500);
                }
            }
        }
        // }
        this.isDistroy = true;
        log.info(">>>>>>>>>>>>线程池已经关闭！");
    }

    /**
     * 监控线程池动态扩容缩容
     */
    public void run() {
        // 1.1 min<任务数<active，或者任务数<min，线程数只要min就可以了
        // 1.2 active<任务数<max，线程数要扩展到active
        // 1.3 max<任务数，线程数要扩到max
        while (!isDistroy) {
            log.info("当前任务数" + TASK_QUEUE.size() + ",min:" + min + ",active:" + active + ",max:" + max + ",current:"
                    + currentSize);
            // 扩容
            if (THREAD_QUEUE.size() > active && currentSize < active) {
                for (int i = currentSize; i < active; i++) {
                    createWorker();
                }
                currentSize = active;
                log.info("线程池已经扩容到active:" + active);
            } else if (TASK_QUEUE.size() > max && currentSize < max) {
                for (int i = currentSize; i < max; i++) {
                    createWorker();
                }
                currentSize = max;
                log.info("线程池扩容到max:" + max);
            }
            // 缩容
            synchronized (THREAD_QUEUE) {

                if (TASK_QUEUE.isEmpty() && currentSize > active) {
                    log.info("线程池开始缩容");
                    int releaseSize = currentSize - active;
                    Iterator<WorkerThread> iterator = THREAD_QUEUE.iterator();
                    while (iterator.hasNext()) {
                        if (releaseSize <= 0) {
                            break;
                        }
                        WorkerThread workerThread = iterator.next();
                        if (workerThread.state == WorkerThreadState.BLOCK) {
                            workerThread.interrupt();
                            workerThread.close();
                            iterator.remove();
                            releaseSize--;
                        }
                    }
                    currentSize = active;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一、线程池原理
     * 1. 定义一个队列，用来存在提交的任务（任务也是线程，为什么？）
     * 2. 内部线程类操作任务队列
     * 3. 外部线程池提交任务
     * <p>
     * 二、线程池拒绝策略
     * 1. 任务队列中不可能无限制增加任务，当任务达到一个阈值的时候，线程池要拒绝请求
     * <p>
     * 三、关闭线程池
     * 1. 线程池在非RUNNING状态的时候，即BLOCK状态的时候才能被关闭
     * <p>
     * 四、线程池的扩容和缩容
     * 1. 扩容
     * 1.1 min<任务数<active，或者任务数<min，线程数只要min就可以了
     * 1.2 active<任务数<max，线程数要扩展到active
     * 1.3 max<任务数，线程数要扩到max
     */
    static class WorkerThread extends Thread {
        WorkerThreadState state = WorkerThreadState.FREE;

        @Override
        public void run() {
            OUTER:
            while (state != WorkerThreadState.DEAD) {
                Runnable task;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            this.state = WorkerThreadState.BLOCK;
                            TASK_QUEUE.wait(); // 线程夯住
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    task = TASK_QUEUE.remove();
                }
                // 并行执行
                if (task != null) {
                    this.state = WorkerThreadState.RUNNING;
                    task.run();
                    this.state = WorkerThreadState.FREE;
                }
            }
        }

        public void close() {
            this.state = WorkerThreadState.DEAD;
        }
    }

    public interface DiscardPolicy {
        void discard();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool simpleThreadPool =
                new SimpleThreadPool(5, 10, 25, 66, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        // 提交50个任务
        IntStream.rangeClosed(0, 50).forEach(i -> simpleThreadPool.submit(() -> {
            try {
                Thread.sleep(1000);
                log.info(">>>>>>>>当前线程：" + Thread.currentThread() + "处理任务" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        simpleThreadPool.shutdown();
    }
}
