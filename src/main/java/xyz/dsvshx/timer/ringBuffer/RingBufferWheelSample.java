package xyz.dsvshx.timer.ringBuffer;

import java.util.concurrent.Executors;

/**
 * https://crossoverjie.top/2019/09/27/algorithm/time%20wheel/
 */
public class RingBufferWheelSample {
    public static void main(String[] args) {
        RingBufferWheel ringBufferWheel = new RingBufferWheel(Executors.newFixedThreadPool(2));
        for (int i = 0; i < 30; i++) {
            RingBufferWheel.Task job = new Job();
            job.setKey(i);
            ringBufferWheel.addTask(job);
        }
    }

    public static class Job extends RingBufferWheel.Task {
        @Override
        public void run() {
            System.out.println("test5");
        }
    }
}
