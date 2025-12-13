package wypozyczalniasamochodw;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    private ArrayList<Rental> rentals = new ArrayList<>();

    public RentalStorage() {
        rentals.add(new Rental(LocalDate.of(2025,2,20), LocalDate.of(2025,3,10), 2, 5555));
        rentals.add(new Rental(LocalDate.of(2025,4,1), LocalDate.of(2025,4,20), 3, 7777));
        rentals.add(new Rental(LocalDate.of(2025,2,1), LocalDate.of(2025,2,3), 1, 1234));
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }
    public ArrayList<Rental> getRentals() {
        return rentals;
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