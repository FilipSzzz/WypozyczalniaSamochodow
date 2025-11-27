package wypozyczalniasamochodw;

public class Car {
    private String model;
    private int vin;
    public CarClass carClass;

    public Car(String model, CarClass carClass, int vin) {
        this.model = model;
        this.carClass = carClass;
        this.vin = vin;
    }
    public String getModel() {
        return model;
    }
    public int getVin() {
        return vin;
    }
    public CarClass getCarClass() {
        return carClass;
    }
    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", vin=" + vin +
                ", carClass=" + carClass +
                '}';
    }
}
