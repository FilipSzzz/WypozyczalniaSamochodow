package wypozyczalniasamochodw;

import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class RentalService {
    private RentalStorage rentalStorage;
    private CarStorage carStorage;

    public RentalService(RentalStorage rentalStorage, CarStorage carStorage){
        this.rentalStorage = rentalStorage;
        this.carStorage = carStorage;
    }

    public void wypozyczenie(String model, LocalDate dateFrom, LocalDate dateTo, int clientId){
        Car car = this.carStorage.getCarByModel(model);

        if (car != null && dostepnosc(car.getVin(), dateFrom, dateTo)){
            Rental rental = new Rental(dateFrom, dateTo, clientId, car.getVin());
            rentalStorage.addRental(rental);
            System.out.println("Samochod wypozyczony pomyslnie: " + rental);
        } else {
            System.out.println("Samochod niedostepny w wybranym terminie");
        }
    }

    public boolean dostepnosc(int vin, LocalDate dateFrom, LocalDate dateTo) {
        return rentalStorage.isAvailable(vin, dateFrom, dateTo);
    }
}

