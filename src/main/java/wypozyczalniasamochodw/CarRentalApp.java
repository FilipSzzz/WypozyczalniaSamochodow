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


    public CarRentalApp(RentalService rentalService, RentalStorage rentalStorage, CarStorage carStorage) {
        rentalService.rental("Ferrari Roma",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(20), 1);
        rentalService.rental("Passat w gazie",
                LocalDate.now().plusDays(4),
                LocalDate.now().plusDays(10),2);
        rentalService.rental("Audi Q7",
                LocalDate.now().plusDays(20),
                LocalDate.now().plusDays(40),4);
//        rentalStorage.getRentals().forEach(System.out::println);
//        carStorage.getAllCars().forEach(System.out::println);
    }

    public static void main(String[] args) {

        SpringApplication.run(CarRentalApp.class, args);

    }
}