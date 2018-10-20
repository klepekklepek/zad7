package pl.sda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rachunek {
    private int numerRekordu;
    private LocalDateTime dataWpisu;
    private LocalDate dataRachunku;
    private BigDecimal kwota;
    private String opisRachunku;

    public Rachunek(int numerRekordu, LocalDateTime dataWpisu, LocalDate dataRachunku, BigDecimal kwota, String opisRachunku) {
        this.numerRekordu = numerRekordu;
        this.dataWpisu = dataWpisu;
        this.dataRachunku = dataRachunku;
        this.kwota = kwota;
        this.opisRachunku = opisRachunku;
    }

    public Rachunek(LocalDate dataRachunku, BigDecimal kwota, String opisRachunku) {
        this.dataRachunku = dataRachunku;
        this.kwota = kwota;
        this.opisRachunku = opisRachunku;
        dataWpisu=LocalDateTime.now();
    }

    public int getNumerRekordu() {
        return numerRekordu;
    }

    public void setNumerRekordu(int numerRekordu) {
        this.numerRekordu = numerRekordu;
    }

    public LocalDateTime getDataWpisu() {
        return dataWpisu;
    }

    public LocalDate getDataRachunku() {
        return dataRachunku;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public String getOpisRachunku() {
        return opisRachunku;
    }

    @Override
    public String toString() {
        return "Nr. " + getNumerRekordu() + " -  Rachunek na " +
                getKwota().setScale(2) + " zł z dnia " +getDataRachunku().format(Main.formatter1) + "   Opis: " +
                getOpisRachunku();
    }
}
