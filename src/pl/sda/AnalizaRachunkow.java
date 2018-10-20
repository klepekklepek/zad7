package pl.sda;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalizaRachunkow {

    private static List<Rachunek> rachunkiAnaliza=new LinkedList<>();
    private static List<Rachunek> wybraneRachunki=new ArrayList<>();

    public static List<Rachunek> odebranieDanychZPliku(){
        rachunkiAnaliza.clear();
        List<String> tempPlik=ObslugaPliku.odczyt();
        for (String rekord:tempPlik) {
            String tempPole[]=rekord.replace(" | ",";;").split(";;");
            rachunkiAnaliza.add(new Rachunek(Integer.valueOf(tempPole[0]), LocalDateTime.parse(tempPole[1],Main.dateTimeFormatter),
                    LocalDate.parse(tempPole[2],Main.formatter), BigDecimal.valueOf(Double.valueOf(tempPole[3])),tempPole[4]));

        }
        return rachunkiAnaliza;
    }



    public static void wyswietlenierekordow(List<Rachunek> wysw){
        wysw.stream().forEach(s -> System.out.println(s.toString()));

        BigDecimal suma=wysw.stream()
                .map(kwota->kwota.getKwota())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        Optional<BigDecimal> max=wysw.stream()
                .map(Rachunek::getKwota)
                .max(BigDecimal::compareTo);

        wysw.stream()
                .map(Rachunek::getKwota)
                .min(BigDecimal::compareTo)
                .ifPresent(min-> System.out.println("\n W wybranych rekordach najniższa kwota wynosi: "+min.setScale(2)+"zł, a" +
                        " najwyższa to: "+max.get().setScale(2)+"zł. Łączna suma kwot wyświetlonych" +
                        " rachunków wynosi: "+suma.setScale(2)+"zł.\n"));
    }

    public static List wyszukiwaniePoDacie(int miesiac,int rok){

        wybraneRachunki=rachunkiAnaliza.stream().
                filter(s -> s.getDataRachunku().getMonthValue() == miesiac && s.getDataRachunku().getYear() == rok)
                .collect(Collectors.toList());
        return wybraneRachunki;
    }

    public static List wyszukiwaniePoDacie(){
        wybraneRachunki=rachunkiAnaliza;
        return wybraneRachunki;
    }

    public static List wyszukiwaniePoDacie(LocalDate dataPoczatkowa,LocalDate dataKoncowa){
        wybraneRachunki=rachunkiAnaliza.stream()
                .filter(rachunek -> rachunek.getDataRachunku().isAfter(dataPoczatkowa))
                .filter(rachunek -> rachunek.getDataRachunku().isBefore(dataKoncowa))
                .collect(Collectors.toList());
        return wybraneRachunki;
    }

    public static List wyszukiwaniePoKwocie(BigDecimal kwotaMin, BigDecimal kwotaMax){
        return wybraneRachunki.stream()
                .filter(rachunek -> rachunek.getKwota().compareTo(kwotaMin)>=0)
                .filter(rachunek -> rachunek.getKwota().compareTo(kwotaMax)<=0)
                .collect(Collectors.toList());
       // return wybraneRachunki;
    }
    public static List wyszukiwaniePoKwocie(BigDecimal kwotaMin){
        return wybraneRachunki.stream()
                .filter(rachunek -> rachunek.getKwota().compareTo(kwotaMin)>=0)
                .collect(Collectors.toList());

    }


}

