package pl.sda;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WprowadzenieRachunku {

    private String rekordDoZapisu;
    private static Map<Integer, Rachunek> rachunkiDoZapisu = new HashMap<>();


    static void wprowadzanie() {
        LocalDate tempData;
        BigDecimal tempBig;
        int index = 0;
        do {

            try {
                index++;
                System.out.println("Podaj datę jakiej dotyczy wprowadzany wydatek (w formacie dd-mm-rrrr)");
                tempData = LocalDate.parse(Main.scanner.nextLine(), Main.formatter);

                System.out.println("\n Podaj kwotę rachunku");
                tempBig = Main.scanner.nextBigDecimal();
                Main.scanner.nextLine();

                System.out.println("\n Podaj opis tego rachunku");
                rachunkiDoZapisu.put(index, new Rachunek(tempData, tempBig, Main.scanner.nextLine()));


            } catch (DateTimeParseException ex) {
                System.out.println("\n Podano błędny format daty! Datę prosze podać w formacie: dzień-miesiąc-rok");
                index--;
            } catch (InputMismatchException ex) {
                System.out.println("Podano błędny format kwoty. Kwote podaj w formacie xx,xx");
                Main.scanner.nextLine();
                index--;
            } finally {
                System.out.println(" Czy chcesz wprowadzić jeszcze jakiś rachunek?  (t/n)");

            }

        } while (Pytania.tCzyN());

    }

    public static void wyswietlenieListyDoZapisu() {

        System.out.println(" Następujące rachunki zostały wprowadzone ale jeszcze nie zostały zapisane:\n");
        for (Map.Entry<Integer, Rachunek> entry : rachunkiDoZapisu.entrySet()) {
            System.out.println("Nr. " + entry.getKey() + " -  Rachunek na " + entry.getValue().getKwota().setScale(2) + " zł z dnia " +
                    entry.getValue().getDataRachunku().format(Main.formatter1) + "   Opis: " +
                    entry.getValue().getOpisRachunku());
            //System.out.println(entry.getValue().getDataWpisu()+"   "+entry.getValue().getNumerRekordu());

        }
    }

    public static void zapisDoPliku() {
        int index = 0;
        String tempString1;
        List<String> rekordy = new LinkedList<>();
        List<String> tempPlik=ObslugaPliku.odczyt();
        tempPlik=tempPlik.subList(tempPlik.size()-1,tempPlik.size());
        String tempRekordy[]=tempPlik.get(0).split(" | ");
        index=Integer.valueOf(tempRekordy[0]);


        for (Map.Entry<Integer, Rachunek> rachunek : rachunkiDoZapisu.entrySet()) {
            rachunek.getValue().setNumerRekordu(++index);
            tempString1 = rachunek.getValue().getNumerRekordu() + " | " + rachunek.getValue().getDataWpisu().format(Main.dateTimeFormatter) +
                    " | " + rachunek.getValue().getDataRachunku().format(Main.formatter) + " | " +
                    rachunek.getValue().getKwota() + " | " + rachunek.getValue().getOpisRachunku();
            rekordy.add(tempString1);
        }
        ObslugaPliku.zapis(rekordy);
        rachunkiDoZapisu.clear();
    }

    public static void usuniecieRekordu(){
        do {
            try {
                System.out.println("Podaj numer rekordu który chcesz usunąć:");
                rachunkiDoZapisu.remove(Main.scanner.nextInt());
            }catch (InputMismatchException ex){
                System.out.println("Podany został nieprawidłowy index.");
            } finally {
                Main.scanner.nextLine();
                System.out.println(" Chcesz usunąć jeszcze jeden rekord?  (t/n)");
            }
        } while (Pytania.tCzyN());
    }

}
