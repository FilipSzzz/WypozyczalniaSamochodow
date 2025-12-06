package wypozyczalniasamochodw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class CarRentalApp { // glowna klasa sluzaca jako odpalenie aplikacji, tutaj jest dobrze

    private final RentalService rentalService;

    public CarRentalApp(RentalService rentalService){
        this.rentalService = rentalService;

        rentalService.wypozyczenie("Ferrari Roma ",
                LocalDate.now().plusDays(10),
                LocalDate.now().plusDays(15),1);

    }

    public static void main(String[] args) {

        SpringApplication.run(CarRentalApp.class, args);

    }
}