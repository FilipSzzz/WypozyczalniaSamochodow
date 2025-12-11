package wypozyczalniasamochodw;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    private ArrayList<Rental> rentals = new ArrayList<>();

    public RentalStorage() {
        rentals.add(new Rental(LocalDate.now().plusDays(10), LocalDate.now().plusDays(14), 2, 2222));
        rentals.add(new Rental(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), 3, 7777));
        rentals.add(new Rental(LocalDate.now().plusDays(2), LocalDate.now().plusDays(5), 1, 1234));
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