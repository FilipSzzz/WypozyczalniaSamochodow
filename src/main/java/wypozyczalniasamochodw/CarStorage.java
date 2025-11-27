package wypozyczalniasamochodw;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class CarStorage {
    private ArrayList<Car> cars = new ArrayList<>();

    public CarStorage(){
        initializeCars();
    }
    public void initializeCars(){
        cars.add(new Car("Passat w gazie", CarClass.Economy, 1234));
        cars.add(new Car("Ferrari Roma", CarClass.Premium, 2222));
        cars.add(new Car("LandRover Velar", CarClass.Premium, 4444));
        cars.add(new Car("BMW M4 CS", CarClass.Standard, 5555));
        cars.add(new Car("Mercedes S CLASS", CarClass.Bussiness, 6666));
        cars.add(new Car("Audi Q7", CarClass.SUV, 7777));
    }
    public ArrayList<Car> getAllCars(){
        return cars;
    }
    public Car getCarByVin(int vin){
        for(Car car : cars){
            if(car.getVin() == vin){
                return car;
            }
        }
        return null;
    }
    public boolean isCarExist(String model){
        for(Car car : cars){
            if(car.getModel().equals(model)){
                return true;
            }
        }
        return false;
    }
    public void addCar(Car car){
        cars.add(car);
    }

    public Car getCarByModel(String model){
        for(Car car : cars){
            if(car.getModel().equals(model)){
                return car;
            }
        }
        return null;
    }

}
