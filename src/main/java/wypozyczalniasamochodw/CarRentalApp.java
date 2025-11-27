package wypozyczalniasamochodw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class CarRentalApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CarRentalApp.class, args);

        RentalService rentalService = context.getBean(RentalService.class);
        CarStorage carStorage = context.getBean(CarStorage.class);
        RentalStorage rentalStorage = context.getBean(RentalStorage.class);


        // WYPISANIE DOSTEPNYCH SAMOCHODOW

        System.out.println("Dostepne samochody:");
        for(Car car : carStorage.getAllCars()){
            System.out.println("  - " + car);
        }

        System.out.println("TEST WYPOZYCZANIA SAMOCHODOW");

        rentalService.wypozyczenie("Ferrari Roma", LocalDate.now().plusDays(10), LocalDate.now().plusDays(13), 1);
        rentalService.wypozyczenie("Ferrari Rom", LocalDate.now().plusDays(10), LocalDate.now().plusDays(13), 1);
    }
}
