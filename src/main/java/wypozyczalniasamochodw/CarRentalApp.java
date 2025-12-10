package wypozyczalniasamochodw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
/* SPRAWDZENIE CASOW:
1. TEN SAM DZIEN DZIALA
2. SAMOCHOD WYPOZYCZONY OD NP 5 DO 15 A CHCE WYPOZYCZYC OD 10 DO 15
3. NAŁOŻENIE SIE DAT: OD 10 DO 14 WYPOZYCZONY KLIENT CHCE OD 10 DO 15
4. WYPOZYCZENIE OD DZISIAJ DO ZA 4 DNI A WYPOZYCZENIE CHCE KLIENT ZA 1 DZIEN DO DNIA ZA 3 DNI
5. NIE DA SIE WYPOZYCZYC SAMOCHODU TEGO SAMEGO DNIA DWA RAZY
* */
@SpringBootApplication
public class CarRentalApp { // glowna klasa sluzaca jako odpalenie aplikacji, tutaj jest dobrze

    private final RentalService rentalService;

    private final RentalStorage rentalStorage;

    public CarRentalApp(RentalService rentalService, RentalStorage rentalStorage){
        this.rentalService = rentalService;
        this.rentalStorage = rentalStorage;
        rentalService.rentalMethod("Ferrari Roma",
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(9),1);
        rentalService.rentalMethod("Passat w gazie",
                LocalDate.now(),
                LocalDate.now().plusDays(1),2);
        rentalService.rentalMethod("Audi Q7",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4),3);
        rentalStorage.printRentals();
    }

    public static void main(String[] args) {

        SpringApplication.run(CarRentalApp.class, args);

    }
}