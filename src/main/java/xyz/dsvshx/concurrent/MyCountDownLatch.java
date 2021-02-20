package xyz.dsvshx.concurrent;

import java.util.Random;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dongzhonghua
 * Created on 2021-02-20
 */
@Slf4j
public class MyCountDownLatch {
    /**
     * 例子：数据库迁移，分多个阶段
     * 1. 迁移数据，比对数据
     * 2. 切换数据源，必须等到第一阶段完成
     *
     * @param args
     */
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        MyLatch latch = new MyLatch(2);

        log.info(">>>>>>>迁移数据开始");
        IntStream.rangeClosed(1, 2).forEach(i -> new Thread(() -> {
            log.info(Thread.currentThread().getName() + "正在工作");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }, String.valueOf(i)).start());
        latch.await();
        log.info(">>>>>>>>>>迁移数据，比对数据都已经完成，准备切换数据源");
        log.info("。。。。。。。。。。。。。。。。。。。。");
        log.info("已经完成切换");
    }
}
