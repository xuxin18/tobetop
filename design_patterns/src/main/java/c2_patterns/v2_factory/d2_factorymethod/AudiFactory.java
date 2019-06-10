package c2_patterns.v2_factory.d2_factorymethod;

public class AudiFactory implements CarFactory{
    @Override
    public Car createCar() {
        return new Audi();
    }
}