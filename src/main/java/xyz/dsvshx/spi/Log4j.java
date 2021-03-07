package xyz.dsvshx.spi;

/**
 * @author dongzhonghua
 * Created on 2021-03-05
 */
public class Log4j implements Log {
    @Override
    public void log(String msg) {
        System.out.printf("Log4j: %s%n", msg);
    }
}
