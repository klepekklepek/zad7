package pl.sda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static Scanner scanner=new Scanner(System.in);
    static DateTimeFormatter formatter =DateTimeFormatter.ofPattern("d-M-yyyy");
    static DateTimeFormatter formatter1 =DateTimeFormatter.ofPattern("d/MMMM/yyyy");
    static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {

        do {
            System.out.println(" Podaj co chcesz wykonać:\n w - Wyświetlenie zebranych danych\n n - wprowadzenie nowych danych");
            if (Pytania.wCzyN()){

            } else {
                 WprowadzenieRachunku.wprowadzanie();
                 WprowadzenieRachunku.wyswietlenieListyDoZapisu();
            }


             WprowadzenieRachunku.usuniecieRekordu();
             WprowadzenieRachunku.wyswietlenieListyDoZapisu();
             WprowadzenieRachunku.zapisDoPliku();
            AnalizaRachunkow.odebranieDanychZPliku();

            AnalizaRachunkow.wyswietlenierekordow(AnalizaRachunkow.wyszukiwaniePoDacie());
            //AnalizaRachunkow.wyswietlenierekordow(AnalizaRachunkow.wyszukiwaniePoDacie(3,2017));
            //AnalizaRachunkow.wyswietlenierekordow(AnalizaRachunkow.wyszukiwaniePoDacie(LocalDate.parse("2017-04-17"),LocalDate.now()));
            AnalizaRachunkow.wyswietlenierekordow(AnalizaRachunkow.wyszukiwaniePoKwocie(BigDecimal.valueOf(50)));
            System.out.println(" Chcesz zakończyć pracę programu? (t/n)");
        } while (!Pytania.tCzyN());
    }
}
