package wypozyczalniasamochodw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentalServiceIntegrationTest {
    @MockitoBean
    private RentalStorage rentalStorage;
    @MockitoBean
    private CarStorage carStorage;
    @Autowired
    private RentalService rentalService;

    @Test
    @DisplayName("Powinien wypozyczyc samochod gdy jest dostepny")
    void testingRentExistingCarInAvailableDate(){
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Ferrari Roma", CarClass.Premium, 2543));
        List<Rental> rentals = new ArrayList<>();

        when(carStorage.getAllCars()).thenReturn(cars);
        when(rentalStorage.getRentals()).thenReturn(rentals);
        when(carStorage.existByVin(2543)).thenReturn(true);
        assertTrue(rentalService.isModelAvailableInDate(2543,LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2)));

    }
    @Test
    @DisplayName("Powinien zwrocic false, bo nie mozna wypozyczyc w tym samym terminie")
    void shouldReturnFalseWhenCarIsAlreadyRentedInGivenPeriod() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Ferrari Roma", CarClass.Premium, 2543));
        cars.add(new Car("Audi Q7", CarClass.SUV, 7777));

        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),1,2543));
        rentals.add(new Rental(LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),2,7777));
        when(carStorage.getAllCars()).thenReturn(cars);
        when(carStorage.existByVin(2543)).thenReturn(true);
        when(carStorage.existByVin(7777)).thenReturn(true);
        when(rentalStorage.getRentals()).thenReturn(rentals);

        boolean resultOfFirstCar = rentalService.isModelAvailableInDate(2543,LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2));
        boolean resultOfSecondCar = rentalService.isModelAvailableInDate(7777,LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2));
        rentals.forEach(System.out::println);
        assertFalse(resultOfFirstCar);
        assertFalse(resultOfSecondCar);
    }


}
