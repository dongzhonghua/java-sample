package xyz.dsvshx.ratelimiter;

/**
 * @author dongzhonghua
 * Created on 2021-03-02
 */
public interface RateLimiter {
    boolean acquire();
}
