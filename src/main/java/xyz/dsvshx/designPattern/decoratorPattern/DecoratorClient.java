package xyz.dsvshx.designPattern.decoratorPattern;

public class DecoratorClient {
    public static void main(String[] args) {
        Car benzCar = new BenzCar();
        Car bmwCar = new BmwCar();
        CarDecorator autoCarDecorator = new AutoCarDecorator(benzCar);
        CarDecorator flyCarDecorator = new FlyCarDecorator(new AutoCarDecorator(bmwCar));

        benzCar.run();
        bmwCar.run();
        autoCarDecorator.run();
        flyCarDecorator.run();
    }
}
