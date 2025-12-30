package wypozyczalniasamochodw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RentalStorageTest {
    private RentalStorage rentalStorage;

    @BeforeEach
    void setUp() {
        rentalStorage = new RentalStorage();
    }
    @Test
    @DisplayName("Podstawowe dodanie rentala")
    void addingRental(){
        Rental rental = new Rental(LocalDate.of(2025,1,1),LocalDate.of(2025,1,2),1,1234);
        rentalStorage.addRental(rental);
        rentalStorage.existingRental(rental.getCarVin());

        assertTrue(rentalStorage.existingRental(rental.getCarVin()));
    }

    @Test
    @DisplayName("Dodawanie istniejacego juz rentala, powinien dodac gdyz w rentalStorage nie ma implementacji sprawdzenia duplikacji")
    void addingExistingRental(){
        Rental rental = new Rental(LocalDate.of(2025,2,1), LocalDate.of(2025,2,3), 1, 1234);
        rentalStorage.addRental(rental);
        rentalStorage.printRentals();
        assertTrue(rentalStorage.getRentals().contains(rental));
    }
    @Test
    @DisplayName("Sprawdzenie po clientID ktory nie ma wypozyczenia")
    void searchingByClientIdSad(){
        int klientID = 999;
        rentalStorage.getRentalsByClientId(klientID);
        assertEquals(0,rentalStorage.getRentalsByClientId(klientID).size());
    }
    @Test
    @DisplayName("Sprawdzenie po clientId ktory ma wypozyczenie")
    void searchingByClientIdHappy(){
        int klientID = 1;
        rentalStorage.getRentalsByClientId(klientID);
        assertEquals(1,rentalStorage.getRentalsByClientId(klientID).size());
    }
    @Test
    @DisplayName("Sprawdzenie czy wypozyczenie bedzie w print")
    void printingRentals(){
        Rental rental = new Rental(LocalDate.of(2025,3,1),LocalDate.of(2025,4,2),1,1234);
        rentalStorage.addRental(rental);
        assertTrue(rentalStorage.getRentals().contains(rental));
    }
    @Test
    @DisplayName("Sprawdzenie istniejacego rentala")
    void checkingIfRentalExistHappy(){
        Rental rental = new Rental(LocalDate.of(2025,3,1),LocalDate.of(2025,4,2),1,1234);
        rentalStorage.addRental(rental);
        assertTrue(rentalStorage.existingRental(rental.getCarVin()));
    }




}
