package wypozyczalniasamochodw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        Rental rental = new Rental(LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),1,1234);
        rentalStorage.addRental(rental);
    }

    @Test
    @DisplayName("Dodawanie istniejacego juz rentala, powinien dodac gdyz w rentalStorage nie ma implementacji sprawdzenia duplikacji")
    void addingExistingRental(){
        Rental rental = new Rental(LocalDate.now().plusDays(30), LocalDate.now().plusDays(32), 1, 1234);
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
        Rental rental = new Rental(LocalDate.now().plusDays(60),LocalDate.now().plusDays(90),1,1234);
        rentalStorage.addRental(rental);
        assertTrue(rentalStorage.getRentals().contains(rental));
    }
    @Test
    @DisplayName("Sprawdzenie istniejacego rentala")
    void checkingIfRentalExistHappy(){
        Rental rental = new Rental(LocalDate.now().plusDays(60),LocalDate.now().plusDays(90),1,1234);
        rentalStorage.addRental(rental);

    }




}
