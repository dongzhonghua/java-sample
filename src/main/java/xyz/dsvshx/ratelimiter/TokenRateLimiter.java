package xyz.dsvshx.ratelimiter;

/**
 * @author dongzhonghua
 * Created on 2021-03-02
 */
public class TokenRateLimiter {


    private final double unitAddNum;    // 单位时间（1s）往桶中放令牌数量
    private final long maxTokenNum;      //


    private volatile long currentTokenCount = 0;  // 当前桶中有多少令牌
    private volatile long nextRefreshTime = 0L;  // 下一次刷新桶中令牌数量的时间戳

    /**
     * 令牌桶是一个桶，定时往里面放令牌，然后请求来了从令牌桶里取令牌，取到了继续后续逻辑，没取到就拦截不让请求
     *
     * @param unitAddNum 1秒增加几个令牌
     * @param maxToken 桶中最大令牌数
     * @param isFullStart 一开始是否是满的
     */
    public TokenRateLimiter(double unitAddNum, long maxToken, boolean isFullStart) {
        this.unitAddNum = unitAddNum;
        this.maxTokenNum = maxToken;
        if (isFullStart) {
            this.currentTokenCount = maxToken;
        } else {
            this.currentTokenCount = 0;
        }
        this.nextRefreshTime = calculateNextRefreshTime(System.currentTimeMillis());
    }

    public boolean acquire(long needTokenNum) {
        if (needTokenNum > this.maxTokenNum) {
            return false;
        }
        synchronized (this) {
            long currentTimestamp = System.currentTimeMillis();
            this.refreshCurrentTokenCount(currentTimestamp);
            if (needTokenNum <= this.currentTokenCount) {
                return this.doAquire(needTokenNum, currentTimestamp);
            }
            return false;
        }
    }

    private boolean doAquire(long needTokenNum, long currentTimestamp) {
        this.currentTokenCount -= needTokenNum;
        // 上一次从桶中获取令牌的时间戳（貌似用不到）
        return true;
    }

    /**
     * 刷新桶中令牌数量
     */
    private void refreshCurrentTokenCount(long currentTimestamp) {
        if (this.nextRefreshTime > currentTimestamp) {
            return;
        }
        this.currentTokenCount =
                Math.min(this.maxTokenNum, this.currentTokenCount + calculateNeedAddTokenNum(currentTimestamp));
        this.nextRefreshTime = calculateNextRefreshTime(currentTimestamp);

    }

    /**
     * 计算当前需要添加多少令牌
     */
    private long calculateNeedAddTokenNum(long currentTimestamp) {
        if (this.nextRefreshTime > currentTimestamp) {
            return 0;
        }
        long addOneMs = Math.round(1.0D / this.unitAddNum * 1000D); // 这么久才能加1个令牌
        return (currentTimestamp - this.nextRefreshTime) / addOneMs + 1;
    }

    private long calculateNextRefreshTime(long currentTimestamp) {
        if (currentTimestamp < this.nextRefreshTime) {
            return this.nextRefreshTime;
        }
        long addOneMs = Math.round(1.0D / this.unitAddNum * 1000D); // 这么久才能加1个令牌
        long result = 0;
        if (this.nextRefreshTime <= 0) {
            result = currentTimestamp + addOneMs;
        } else {
            result = this.nextRefreshTime + (currentTimestamp - this.nextRefreshTime) / addOneMs + addOneMs;
        }
        return result;
    }
}
