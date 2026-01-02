package wypozyczalniasamochodw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {
    private RentalService rentalService;
    private CarStorage carStorage;
    private RentalStorage rentalStorage;

    @BeforeEach
    void setUp() {
        this.rentalStorage = new RentalStorage();
        this.carStorage = new CarStorage();
        this.rentalService = new RentalService(rentalStorage, carStorage);
    }
    @Test
    @DisplayName("Powinien dac false, nie ma takiego samochodu")
    void testingIsModelAvailableInDateWithNoExistingCar(){
        assertFalse(rentalService.isModelAvailableInDate(1111, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2)));
    }
    @Test
    @DisplayName("Sprawdzenie czy mozna podac null")
    void testingIsModelAvailableInDateWithNull(){
        assertFalse(rentalService.isModelAvailableInDate(1234, null, null));
    }
    @Test
    @DisplayName("Case gdy dataOd jest po dataDo")
    void testingIsModelAvailableInDateWithDateOdAfterDateDo(){
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.of(2025, 1, 30), LocalDate.of(2025, 1, 20)));
    }
    @Test
    @DisplayName("Wypozyczenie samochodu na jeden dzien")
    void testingIsModelAvailableInDateWithDateOdEqualsDateDo(){
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.of(2025, 1, 20), LocalDate.of(2025, 1, 20)));
    }
    @Test
    @DisplayName("Happy case, rental car")
    void isModelAvailableInDateHappyCase(){
        rentalService.rentalMethod("Ferrari Roma", LocalDate.of(2025, 1,1), LocalDate.of(2025,1,10),2);
        rentalService.rentalMethod("Ferrari Roma", LocalDate.of(2025, 1,10), LocalDate.of(2025,1,20),2);
        rentalStorage.printRentals();
        assertTrue(rentalService.isModelAvailableInDate(2222, LocalDate.of(2025, 1,10), LocalDate.of(2025,1,20)));
    }
    @Test
    @DisplayName("Overlapping date, should return false after rental first case")
    void isModelAvailableInDateWithOverlappingDate_fromLeft(){
        rentalService.rentalMethod("Ferrari Roma", LocalDate.of(2025, 1,1), LocalDate.of(2025,1,30),2);
        rentalService.rentalMethod("Ferrari Roma", LocalDate.of(2025, 1,1), LocalDate.of(2025,1,20),2);
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 20)));
    }
    @Test
    void isModelAvailableInDateWithOverlappingDate_fromRight(){
        rentalService.rentalMethod("Ferrari Roma", LocalDate.of(2025, 1,1), LocalDate.of(2025,1,30),2);
        rentalService.rentalMethod("Ferrari Roma", LocalDate.of(2025, 1,20), LocalDate.of(2025,2,1),2);
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.of(2025, 1,20), LocalDate.of(2025,2,1)));
    }
    @Test
    void overlappingTheWholeDate(){
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 2,1), LocalDate.of(2025,2,20),2);
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 1,1), LocalDate.of(2025,3,20),2);
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.of(2025, 1,1), LocalDate.of(2025,3,20)));

    }
    @Test
    void checkingThreeOverlappingRentals(){
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 2,1), LocalDate.of(2025,3,20),2);
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 3,1), LocalDate.of(2025,3,2),2);
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 3,10), LocalDate.of(2025,3,21),2);
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.of(2025, 3,1), LocalDate.of(2025,3,2)));
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.of(2025, 3,10), LocalDate.of(2025,3,21)));
    }
    @Test
    void checkingIfRentalCanBeTakeByOtherClient(){
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 2,1), LocalDate.of(2025,3,20),2);
        rentalService.rentalMethod("Audi Q7", LocalDate.of(2025, 3,1), LocalDate.of(2025,3,2),4);
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.of(2025, 3,1), LocalDate.of(2025,3,2)));
    }

}
