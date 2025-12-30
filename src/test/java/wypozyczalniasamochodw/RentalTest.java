package wypozyczalniasamochodw;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    private Rental rental;

    @Test
    void getKlientIdCorrect(){
        int klientId = 33;
        int carVin = 9999;
        LocalDate odData = LocalDate.of(2025,1,1);
        LocalDate doData = LocalDate.of(2025,1,2);

        rental = new Rental(odData,doData,klientId,carVin);

        assertEquals(klientId, rental.getKlientId());
        assertNotNull(rental);
    }

    @Test
    void checkIfRentalIsCorrect(){

    }
}