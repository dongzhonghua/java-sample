package xyz.dsvshx.futurePattern;

/**
 * @author dongzhonghua
 * Created on 2021-04-12
 */
public class RealData implements Data {
    private final String result;

    // 模拟很慢的一个任务。
    public RealData(String result) {
        try {
            System.out.println("真正的去获取结果，等待一段时间");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = result;
    }

    @Override
    public String getResult() {
        return result;
    }
}
