package wypozyczalniasamochodw;

import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class RentalService {
    private final RentalStorage rentalStorage;
    private final CarStorage carStorage;

    public RentalService(RentalStorage rentalStorage, CarStorage carStorage){
        this.rentalStorage = rentalStorage;
        this.carStorage = carStorage;
    }

    public void wypozyczenie(String model, LocalDate dateFrom, LocalDate dateTo, int clientId){

    }

    public boolean dostepnosc(String model, LocalDate dateFrom, LocalDate dateTo) {
        if (carStorage.getCarByModel(model)==null){
            return false;
        }



    }
    public boolean isModelAvailableInDate(int vin, LocalDate dateFrom, LocalDate dateTo){
        if(carStorage.getCarByVin(vin)){
            return
        }

    }

}

