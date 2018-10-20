package pl.sda;

public class Pytania {


    static public boolean tCzyN(){
        String tempString1=Main.scanner.nextLine();
        Boolean tempBolen1=true;
        Boolean wynik=false;

        do {
            if ("T".equals(tempString1.toUpperCase())) {
                wynik = true;
                tempBolen1 = false;
            } else if ("N".equals(tempString1.toUpperCase())) {
                tempBolen1 = false;
            } else {
                System.out.println(" Wprowadzono błędne dane prosze wybrać T lub N (Tak/Nie).");
                tempString1 = Main.scanner.nextLine();
            }
        }while (tempBolen1);

      return wynik;
    }

    static public boolean wCzyN(){
        String tempString1=Main.scanner.nextLine();
        Boolean tempBolen1=true;
        Boolean wynik=false;

        do {
            if ("W".equals(tempString1.toUpperCase())) {
                wynik = true;
                tempBolen1 = false;
            } else if ("N".equals(tempString1.toUpperCase())) {
                tempBolen1 = false;
            } else {
                System.out.println(" Wprowadzono błędne dane prosze wybrać W lub N (w - odczyt/n - zapis).");
                tempString1 = Main.scanner.nextLine();
            }
        }while (tempBolen1);

        return wynik;
    }

}
