package xyz.dsvshx.designPattern.decoratorPattern;

public class CarDecorator implements Car{

    protected Car decoratorCar;

    public CarDecorator(Car decoratorCar) {
        this.decoratorCar = decoratorCar;
    }
    public void run() {
        decoratorCar.run();
    }
}
