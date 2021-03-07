package xyz.dsvshx.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author dongzhonghua
 * Created on 2021-03-05
 */

public class LogTest {
    public static void main(String[] args) {
        ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Log log = iterator.next();
            log.log("JDK SPI");
        }
    }
}
