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

    public void rentalMethod(String model, LocalDate dateFrom, LocalDate dateTo, int clientId) {
        if (isAvailable(model,dateFrom,dateTo)){
            rentalStorage.addRental(new Rental(dateFrom,dateTo,clientId,carStorage.getCarByModel(model).get(0).getVin()));
        }
        System.out.println("Wypozyczenie nie jest mozliwe");
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
        if (!carStorage.getCarByVin(vin)) {
            System.out.println("Nie ma takiego samochodu w bazie danych.");
            return false;
        }
        if (dateFrom == null || dateTo == null) {
            System.out.println("Podane dateFrom lub dateTo nie moze byc null.");
            return false;
        }
        if (dateFrom.isAfter(dateTo)) {
            System.out.println("DataOd nie moze byc po DataDo lub po dniu dzisiejszym.");
            return false;
        }
        if (dateFrom.isEqual(dateTo)) {
            System.out.println("Nie mozna wypozyczyc samochodu na jeden dzien.");
            return false;
        }
        for (Rental existingRental: rentalStorage.getRentals()) { // iterujemy po wszystkich wypozyczeniach, szukajac nalozenia sie
            if (existingRental.getCarVin() == vin){ // jezeli odnajdziemy wypozyczenie na samochod zgodny z podanym VINem
                LocalDate existingStart = existingRental.getOdData(); // zapisuje istniejace wypozyczenie
                LocalDate existingEnd = existingRental.getDoData();

                if (!dateTo.isBefore(existingStart) && !dateFrom.isAfter(existingEnd)){ // przypadek nalozenia sie
                    return false;
                }
            }
        }
        return true;
    }
}