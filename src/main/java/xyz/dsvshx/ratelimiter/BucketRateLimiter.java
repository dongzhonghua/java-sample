package xyz.dsvshx.ratelimiter;

/**
 * @author dongzhonghua
 * Created on 2021-03-02
 */
public class BucketRateLimiter implements RateLimiter {
    // 桶的容量
    private int capacity;
    // 当前水量
    private long water;
    // 水流速度 毫秒 可不可以利用起来时间单位？
    private double rate;
    // 最后一次加水时间
    public long lastTime = System.currentTimeMillis();

    /**
     * 漏桶算法，water按照恒定速率减少，小于零则为0。
     * 超过了capacity则拒绝执行。来了一个任务如果小于capacity则water++
     */
    public BucketRateLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
    }

    @Override
    public boolean acquire() {
        refresh();
        if (water < capacity) {
            water++;
            return true;
        }
        System.out.println(">>>>>>>执行拒绝策略");
        return false;
    }

    private void refresh() {
        long now = System.currentTimeMillis();
        long l = (now - lastTime);
        water = (long) Math.max(0, water - l * rate);
        System.out.println("距离上一次过了：" + l + "ms，剩余容量:" + (capacity - water));
        lastTime = now;
    }
}
