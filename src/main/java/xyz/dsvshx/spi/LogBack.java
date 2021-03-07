package xyz.dsvshx.spi;

/**
 * @author dongzhonghua
 * Created on 2021-03-05
 */
public class LogBack implements Log {
    @Override
    public void log(String msg) {
        System.out.printf("LogBack: %s%n", msg);
    }
}
