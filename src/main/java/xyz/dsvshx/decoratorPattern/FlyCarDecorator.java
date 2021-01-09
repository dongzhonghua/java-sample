package xyz.dsvshx.decoratorPattern;

public class FlyCarDecorator extends CarDecorator{
    public FlyCarDecorator(Car decoratorCar) {
        super(decoratorCar);
    }

    @Override
    public void run() {
        decoratorCar.run();
        fly();
    }

    private void fly() {
        System.out.println("汽车飞行");
    }
}
