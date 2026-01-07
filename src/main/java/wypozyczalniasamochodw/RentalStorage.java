package wypozyczalniasamochodw;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    private final ArrayList<Rental> rentals = new ArrayList<>();

    public RentalStorage() {
        rentals.add(new Rental(LocalDate.now().plusDays(40), LocalDate.now().plusDays(60), 2, 5555));
        rentals.add(new Rental(LocalDate.now().plusDays(90), LocalDate.now().plusDays(110), 3, 7777));
        rentals.add(new Rental(LocalDate.now().plusDays(30), LocalDate.now().plusDays(32), 1, 1234));
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }
    public List<Rental> getRentals() {
        return new ArrayList<>(rentals);
    }

    public void printRentals(){
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }
    public List<Rental> getRentalsByClientId(int clientId) {
        return rentals.stream()
                .filter(rental -> rental.getKlientId() == clientId)
                .toList();
    }
}