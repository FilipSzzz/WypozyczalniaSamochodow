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
        assertFalse(rentalService.isModelAvailableInDate(1111, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));
    }
    @Test
    @DisplayName("Sprawdzenie czy mozna podac null")
    void testingIsModelAvailableInDateWithNull(){
        assertFalse(rentalService.isModelAvailableInDate(1234, null, null));    }
    @Test
    @DisplayName("Case gdy dataOd jest po dataDo")
    void testingIsModelAvailableInDateWithDateOdAfterDateDo(){
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.now(), LocalDate.now().minusDays(1)));    }
    @Test
    @DisplayName("Wypozyczenie samochodu na jeden dzien")
    void testingIsModelAvailableInDateWithDateOdEqualsDateDo(){
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.now().plusDays(20), LocalDate.now().plusDays(20)));
    }
    @Test
    @DisplayName("Overlapping date, should return false after rental first case")
    void isModelAvailableInDateWithOverlappingDate_fromLeft(){
        rentalService.rental("Ferrari Roma", LocalDate.now().plusDays(1), LocalDate.now().plusDays(30),2);
        rentalService.rental("Ferrari Roma", LocalDate.now().plusDays(1), LocalDate.now().plusDays(20),2);
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.now().plusDays(1), LocalDate.now().plusDays(20)));
    }
    @Test
    void isModelAvailableInDateWithOverlappingDate_fromRight(){
        rentalService.rental("Ferrari Roma", LocalDate.now().plusDays(1), LocalDate.now().plusDays(30),2);
        rentalService.rental("Ferrari Roma", LocalDate.now().plusDays(20), LocalDate.now().plusDays(40),2);
        assertFalse(rentalService.isModelAvailableInDate(2222, LocalDate.now().plusDays(20), LocalDate.now().plusDays(40)));
    }
    @Test
    void overlappingTheWholeDate(){
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(30), LocalDate.now().plusDays(50),2);
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(20), LocalDate.now().plusDays(60),2);
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.now().plusDays(20), LocalDate.now().plusDays(60)));

    }
    @Test
    void checkingThreeOverlappingRentals(){
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(10), LocalDate.now().plusDays(30),2);
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(32), LocalDate.now().plusDays(35),2);
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(38), LocalDate.now().plusDays(45),2);
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.now().plusDays(32), LocalDate.now().plusDays(35)));
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.now().plusDays(38), LocalDate.now().plusDays(45)));
    }
    @Test
    void checkingIfRentalCanBeTakeByOtherClient(){
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(10), LocalDate.now().plusDays(30),2);
        rentalService.rental("Audi Q7", LocalDate.now().plusDays(20), LocalDate.now().plusDays(25),4);
        rentalStorage.getRentals().forEach(System.out::println);
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.now().plusDays(20), LocalDate.now().plusDays(25)));
    }
    @Test
    void checkingIfRentCanBeInThePas(){
        assertFalse(rentalService.isModelAvailableInDate(7777, LocalDate.now().minusDays(10),LocalDate.now().plusDays(10)));
    }

}
