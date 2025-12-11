package wypozyczalniasamochodw;

import java.time.LocalDate;

public class Rental {

    private LocalDate odData;
    private LocalDate doData;
    private int klientId;
    private int carVin;

    public Rental(LocalDate odData, LocalDate doData, int klientId, int carVin) {
        this.odData = odData;
        this.doData = doData;
        this.klientId = klientId;
        this.carVin = carVin;
    }
    public int getKlientId(){
        return klientId;
    }
    public LocalDate getDoData(){
        return doData;
    }
    public int getCarVin(){
        return carVin;
    }
    public LocalDate getOdData(){
        return odData;
    }
    @Override
    public String toString() {
        return "Rental{" +
                "odData=" + odData +
                ", doData=" + doData +
                ", klient_id=" + klientId +
                ", carVin=" + carVin +
                '}';
    }

}