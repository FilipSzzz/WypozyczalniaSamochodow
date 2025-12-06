package wypozyczalniasamochodw;

import java.time.LocalDate;

public class Rental {

    private LocalDate odData;
    private LocalDate doData;
    private int klient_id;
    private int carVin;

    public Rental(LocalDate odData, LocalDate doData, int klient_id, int carVin) {
        this.odData = odData;
        this.doData = doData;
        this.klient_id = klient_id;
        this.carVin = carVin;
    }
    public int getKlient_id(){
        return klient_id;
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
                ", klient_id=" + klient_id +
                ", carVin=" + carVin +
                '}';
    }

}