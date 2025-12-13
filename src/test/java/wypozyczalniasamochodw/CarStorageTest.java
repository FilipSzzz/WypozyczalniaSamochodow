package wypozyczalniasamochodw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarStorageTest {
    final CarStorage carStorage = new CarStorage();
    @BeforeEach
    void setUp() {
        CarStorageTest carStorage = new CarStorageTest();
    }

    // TESTUJE ZAWSZE HAPPY I SAD CASE, COS CO MA DZIALAC, I NIE DZIALAC

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

    }

}
