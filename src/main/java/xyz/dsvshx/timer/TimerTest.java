package xyz.dsvshx.timer;

import org.junit.jupiter.api.Test;

/**
 * @author dongzhonghua
 * Created on 2021-01-09
 */
class TimerTest {

    @Test
    public void test() {
        /*
         这两句话具体什么意思？
         if (Math.abs(period) > (Long.MAX_VALUE >> 1)) {
             period >>= 1;
         }
        */

        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE >> 1);
        System.out.println(64 >> 1);
        System.out.println(2 << 2);
        System.out.println(64 >> 3);
    }

}