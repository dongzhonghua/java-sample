package xyz.dsvshx.reference;

/**
 * @author dongzhonghua
 * Created on 2021-03-16
 */
public class HardAndGc {
    @Override
    protected void finalize() throws Throwable {
        System.out.println(">>>>>>>>> 被回收了");
    }

    public static void main(String[] args) {
        HardAndGc test = new HardAndGc();
        test = null;
        System.gc();
    }
}
