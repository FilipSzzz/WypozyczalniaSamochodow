package wypozyczalniasamochodw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RentalServiceTest {
    private RentalService rentalService;
    private CarStorage carStorage;
    private RentalStorage rentalStorage;
    LocalDate january = LocalDate.of(2025,1,1);

    @BeforeEach
    void setUp() {
        RentalService rentalService = new RentalService(new RentalStorage(), new CarStorage());

    }
    @Test
    void shouldReturnFalseBecauseOfLoopingDate_fromLeft() {

    }

}
