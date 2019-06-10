package c2_patterns.v2_factory.d2_factorymethod;

public class JiliFactory implements CarFactory{
    @Override
    public Car createCar() {
        return new Jili();
    }
}