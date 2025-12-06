package wypozyczalniasamochodw;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
DO ZAIMPLEMENTOWANIA:
- DODANIE DO RENTALA,
- getRentalsByClientID,
-
 */
@Component
public class RentalStorage {
    private ArrayList<Rental> rentals = new ArrayList<>();

    public RentalStorage() {
        rentals.add(new Rental(LocalDate.now(), LocalDate.now().plusDays(1), 1, 1234));
        rentals.add(new Rental(LocalDate.now(), LocalDate.now().plusDays(2), 2, 2222));
        rentals.add(new Rental(LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), 3, 2222));
    }

    public void addRental(Rental rental) {
        {
        }
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public List<Rental> getRentalsByClientId(int clientId) {
        return rentals.stream()
                .filter(rental -> rental.getKlient_id() == clientId)
                .toList();
    }


}