package wypozyczalniasamochodw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

/* SPRAWDZENIE CASOW:
1. TEN SAM DZIEN DZIALA
2. SAMOCHOD WYPOZYCZONY OD NP 5 DO 15 A CHCE WYPOZYCZYC OD 10 DO 15
3. NAŁOŻENIE SIE DAT: OD 10 DO 14 WYPOZYCZONY KLIENT CHCE OD 10 DO 15
4. WYPOZYCZENIE OD DZISIAJ DO ZA 4 DNI A WYPOZYCZENIE CHCE KLIENT ZA 1 DZIEN DO DNIA ZA 3 DNI
5. NIE DA SIE WYPOZYCZYC SAMOHODU TEGO SAMEGO DNIA DWA RAZY
* */
@SpringBootApplication
public class CarRentalApp { // glowna klasa sluzaca jako odpalenie aplikacji, tutaj jest dobrze

    private final RentalService rentalService;
    private final RentalStorage rentalStorage;


    public CarRentalApp(RentalService rentalService, RentalStorage rentalStorage, CarStorage carStorage) {
        this.rentalService = rentalService;
        this.rentalStorage = rentalStorage;
//        rentalService.rentalMethod("Ferrari Roma",
//                LocalDate.of(2025, 1,1),
//                LocalDate.of(2025,1,30),2);
//        rentalService.rentalMethod("Passat w gazie",
//                LocalDate.of(2025, 1,1),
//                LocalDate.of(2025,2,20), 2);
//        rentalService.rentalMethod("Audi Q7",
//                LocalDate.of(2025, 1,3),
//                LocalDate.of(2025,2,1), 4);
//        rentalStorage.getRentals().forEach(System.out::println);
//        carStorage.getAllCars().forEach(System.out::println);
    }

    public static void main(String[] args) {

        SpringApplication.run(CarRentalApp.class, args);

    }
}