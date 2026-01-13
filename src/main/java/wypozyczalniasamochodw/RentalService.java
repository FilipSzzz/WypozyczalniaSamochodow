package wypozyczalniasamochodw;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalService {
    private final RentalStorage rentalStorage;
    private final CarStorage carStorage;

    public RentalService(RentalStorage rentalStorage, CarStorage carStorage) {
        this.rentalStorage = rentalStorage;
        this.carStorage = carStorage;
    }

    public boolean rental(String model, LocalDate dateFrom, LocalDate dateTo, int clientId) {
        if (!isAvailable(model, dateFrom, dateTo)) {
            System.out.println("Żaden egzemplarz modelu " + model + " nie jest dostepny w podanym terminie");
            return false;
        }
        for (Car car : carStorage.getCarByModel(model)) {
            if (isModelAvailableInDate(car.getVin(), dateFrom, dateTo)) {
                Rental rental = new Rental(dateFrom, dateTo, clientId, car.getVin());
                rentalStorage.addRental(rental);
                System.out.println("Wypozyczono samochod: " + model + " (VIN: " + car.getVin() + ")");
                return true;
            }
        }
        System.out.println("Nie udalo sie zrealizowac wypozyczenia.");
        return false;
    }

    public boolean isAvailable(String model, LocalDate dateFrom, LocalDate dateTo) {
        for (Car carToRent : carStorage.getCarByModel(model)) {
            if (isModelAvailableInDate(carToRent.getVin(), dateFrom, dateTo)) {
                return true;
            }
        }
        return false;
    }

    public boolean isModelAvailableInDate(int vin, LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            System.out.println("Data nie moze byc null.");
            return false;
        }
        if (dateFrom.isAfter(dateTo)) {
            System.out.println("Data od nie moze byc po data do.");
            return false;
        }
        if (!carStorage.existByVin(vin)) {
            System.out.println("Nie ma takiego samochodu w bazie danych.");
            return false;
        }

        if (dateFrom.isBefore(LocalDate.now()) || dateTo.isBefore(LocalDate.now())) {
            System.out.println("Nie mozna wypozyczac z data wsteczna.");
            return false;
        }

        if (dateFrom.isEqual(dateTo)) {
            System.out.println("Nie mozna wypozyczyc samochodu na jeden dzien.");
            return false;
        }
        for (Rental existingRental : rentalStorage.getRentals()) { // iterujemy po wszystkich wypozyczeniach, szukajac nalozenia sie
            if (existingRental.getCarVin() == vin) { // jezeli odnajdziemy wypozyczenie na samochod zgodny z podanym VINem
                LocalDate existingStart = existingRental.getOdData(); // zapisuje istniejace wypozyczenie
                LocalDate existingEnd = existingRental.getDoData();

                if (!dateTo.isBefore(existingStart) && !dateFrom.isAfter(existingEnd)) { // przypadek nalozenia sie
                    return false;
                }
            }
        }
        return true;
    }
}