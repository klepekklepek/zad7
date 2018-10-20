package pl.sda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObslugaPliku {

    static Path sciezka= Paths.get("d:/mk/dane.txt");
    //static Path sciezka= Paths.get("dane.txt").toAbsolutePath();
    static Charset kodowanie=Charset.forName("Utf-8");

    public static List odczyt(){

        try (BufferedReader reader = Files.newBufferedReader(sciezka,kodowanie)) {

            List<String> rekordyDanych=reader.lines()
                    .skip(2)
                    .collect(Collectors.toList());
            return rekordyDanych;

        }catch (IOException ex) {
            List<String> e=new ArrayList<>();
            e.add(" Pilki został utworzony: "+ LocalDateTime.now().format(Main.dateTimeFormatter));
            zapis(e);
            e.remove(0);
            e.add("0 | ");
            return e;

        }

    }

    public static void zapis(List<String> rekordy){

        try (BufferedWriter zapis=Files.newBufferedWriter(sciezka,kodowanie, StandardOpenOption.CREATE,StandardOpenOption.APPEND)){

            for ( String rekord:rekordy) {
                zapis.newLine();
                zapis.write(rekord);
            }

        } catch (IOException exz) {

        }
    }

}
