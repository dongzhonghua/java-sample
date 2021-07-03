package xyz.dsvshx.designPattern.futurePattern;

/**
 * @author dongzhonghua
 * Created on 2021-04-12
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isDone = false;

    public synchronized void setRealData(RealData realData) {
        if (isDone) {
            return;
        }
        this.realData = realData;
        isDone = true;
        System.out.println("获取到了结果");
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isDone) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        return realData.getResult();
    }
}
