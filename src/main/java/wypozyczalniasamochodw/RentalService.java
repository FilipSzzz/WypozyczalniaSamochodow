package wypozyczalniasamochodw;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

/*
tutaj jest do zaimplementowania logika wypozyczenia samochodow:
- wypozyczenie ( sprawdzenie dostepnosci, sprawdzenie czy nie bedzie przypadku nalozenia dat na siebie PATRZ ZDJECIE,
- sprawdzenie dostepnosci po modelu, dacie od i do,
- sprawdzenie czy nie wystepuje przypadek nalozenia dat na siebie
 */
@Service
public class RentalService {
    private final RentalStorage rentalStorage;
    private final CarStorage carStorage;

    public RentalService(RentalStorage rentalStorage, CarStorage carStorage) {
        this.rentalStorage = rentalStorage;
        this.carStorage = carStorage;
    }

    public void wypozyczenie(String model, LocalDate dateFrom, LocalDate dateTo, int clientId) {

    }

    public boolean dostepnosc(String model, LocalDate dateFrom, LocalDate dateTo) {
        if (carStorage.getCarByModel(model) == null) {
            return false;
        }

        return true;

    }

    public boolean isModelAvailableInDate(int vin, LocalDate dateFrom, LocalDate dateTo) {
        if (carStorage.getCarByVin(vin)) {
            return false;
        }
        return true;
    }
}
