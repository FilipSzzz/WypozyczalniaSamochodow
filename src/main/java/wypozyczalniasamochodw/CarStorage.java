package wypozyczalniasamochodw;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarStorage {
    private ArrayList<Car> cars = new ArrayList<>();

    /*
     * UWAGA TA KLASE UWAZAMY ZA "BAZE DANYCH" CZY MAJA TUTAJ BYC METODY KTORE TYLKO
     * SA ODPOWIEDZIALNE ZA BAZE DANYCH, WYSZUKIWANIE SAMOCHODU
     *
     * */
    public CarStorage() {
        initializeCars();
    }

    public void initializeCars() {
        cars.add(new Car("Passat w gazie", CarClass.Economy, 1234));
        cars.add(new Car("Ferrari Roma", CarClass.Premium, 2222));
        cars.add(new Car("LandRover Velar", CarClass.Premium, 4444));
        cars.add(new Car("BMW M4 CS", CarClass.Standard, 5555));
        cars.add(new Car("Mercedes S CLASS", CarClass.Bussiness, 6666));
        cars.add(new Car("Audi Q7", CarClass.SUV, 7777));
    }

    public ArrayList<Car> getAllCars() {
        return cars;
    }

    public boolean existByVin(int vin) { // TUTAJ JEST CHYBA ZAIMPLEMENTOWANE DOBRZE, BO SAMOCHOD PO VINIE JEST UNIKALNY
        for (Car car : cars) {
            if (car.getVin() == vin) {
                return true;
            }
        }
        return false;
    }
    public Car getCarByVin(int vin) {
        for (Car car : cars) {
            if (car.getVin() == vin) {
                return car;
            }
        }
        return null;
    }
    public void addCar(Car car) {
        if (existByVin(car.getVin())) {
            System.out.println("Samochod o podanym VIN juz istnieje w bazie danych.");
            return;
        }
        cars.add(car);
    }

    public List<Car> getCarByModel(String model) { // trzeba to robic bo moga byc np DWA PASSATY
        ArrayList<Car> carModelFor = new ArrayList<>();
        String trimmedModel = model.trim();
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(trimmedModel)) {
                carModelFor.add(car);
            }
        }
        return carModelFor;
    }

}