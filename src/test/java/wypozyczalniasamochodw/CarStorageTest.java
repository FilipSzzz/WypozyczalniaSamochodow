package wypozyczalniasamochodw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarStorageTest {
    CarStorage carStorage = new CarStorage();
    @BeforeEach
    void setUp() {
        this.carStorage = new CarStorage();
    }
    @Test
    void checkingIfCarExistByVIN(){
        Car car = new Car("Ferrari Roma Czerwona", CarClass.Premium, 2543);
        carStorage.addCar(car);
        assertTrue(carStorage.existByVin(2222));
    }
    @Test
    void checkingIfCarDoesntExistByVIN(){
        Car car = new Car("Ferrari Roma", CarClass.Premium, 1111);
        carStorage.addCar(car);
        assertTrue(carStorage.existByVin(1111));
    }
    @Test
    void checkIfCarExistByCar(){
        Car car = new Car("Ferrari Roma", CarClass.Premium, 2222);
        carStorage.addCar(car);
        assertThat(carStorage.existByVin(car.getVin()));
    }
    @Test
    void checkIfCarDoesntExistByCar(){
        Car car = new Car("Ferrari Roma", CarClass.Premium, 9999);
        carStorage.addCar(car);
        assertTrue(carStorage.existByVin(car.getVin()));
    }
    @Test
    void checkingNumberOfCars(){
        int initialSize = carStorage.getAllCars().size();
        Car car = new Car("Laferrari", CarClass.Premium, 6767);
        carStorage.addCar(car);
        assertThat(carStorage.getAllCars().size()).isEqualTo(initialSize + 1);
    }
    @Test
    void checkingIfExistingCarCanBeAdded(){
        Car car = new Car("Ferrari Roma", CarClass.Premium, 2222);
        carStorage.addCar(car);
        assertThat(carStorage.getCarByModel("Ferrari Roma"));
    }
    @Test
    void checkingIfNonExistingCarCanBeAdded(){
        Car car = new Car("Opel Corsa",CarClass.Economy,4912);
        carStorage.addCar(car);
        assertThat(carStorage.getCarByModel("Opel Corsa"));
    }
    @Test
    @DisplayName("Sprawdzenie czy mozna z malych liter ")
    void checkIfCarExistByModel(){
        Car car = new Car("ferrari roma", CarClass.Premium, 2222);
        carStorage.getCarByModel(car.getModel());
        assertThat(carStorage.existByVin(car.getVin()));
    }
    @Test
    void checkIfCarDoesntExistByModel(){
        Car car = new Car("Ferrari Roma", CarClass.Premium, 9999);
        carStorage.getCarByModel(car.getModel());
        assertFalse(carStorage.existByVin(car.getVin()));
    }
    @Test
    @DisplayName("Sprawdzenie czy mozna dodac auto z tym samym vinem")
    void checkIfCarCanBeAddedTwice(){
        Car car = new Car("Ferrari Roma", CarClass.Premium, 2222);
        carStorage.addCar(car);
        assertThat(carStorage.getCarByModel("Ferrari Roma")).isNotNull();
    }

}