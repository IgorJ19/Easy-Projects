package org.example;

import java.util.Random;

public class Pracownik extends ListaList{

    public void wariantPracownika(String nazwa, String nazwaAlternatywna, int indeksDoUsuniecia){

        kolejnosc++;
        if(pracownicyNaZmianie.values().contains(nazwa))
            pracownicyNaZmianie(kolejnosc, nazwaAlternatywna);
        else{
            pracownicyNaZmianie(kolejnosc, nazwa);
            zbiorWolnychLosow.remove(indeksDoUsuniecia);
            zbiorWolnychLosow.add(indeksDoUsuniecia, 11);

        }
    }
        int i = spoznienie();
        public String odmianaMinut(){
            String odmieniona = null;
            if(i < 5 && i > 0){
                odmieniona = "minuty";
            return odmieniona;}
            else {
                odmieniona = "minut";
                return  odmieniona;
            }
        }
        public void przyjscieDoPracy(int godzina, int index){
            char ostatnia;
            pracownicy();
            String nazwa = pracownicyNaZmianie.get(index);

            if(nazwa != null) {
                ostatnia = nazwa.charAt(nazwa.length() - 1);
                if (i == 0)
                    System.out.println(nadajZielony + "godzina: " + godzina + ":" + i + "0" + usunKolor);
                else if (i > 0 && i < 10)
                    System.out.println(nadajZielony + "godzina: " + godzina + ":" + "0" + i + usunKolor);
                else
                    System.out.println(nadajZielony + "godzina: " + godzina + ":" + i + usunKolor);

                System.out.print(nadajZielony + " do pracy przychodzi: " + usunKolor);
                System.out.println(pracownicyNaZmianie.get(index));

                if (ostatnia == 'a')
                    System.out.println(" spoznila sie: " + i + odmianaMinut());
                else
                    System.out.println(" spoznil sie: " + i + odmianaMinut());

                System.out.println(" na zmianie są: " + pracownicyNaZmianie);
            }
            else {
                nazwa = "pracownik";
                ostatnia = nazwa.charAt(nazwa.length() - 1);
                if (i == 0)
                    System.out.println(nadajZielony + "godzina: " + godzina + ":" + i + "0" + usunKolor);
                else if (i > 0 && i < 10)
                    System.out.println(nadajZielony + "godzina: " + godzina + ":" + "0" + i + usunKolor);
                else
                    System.out.println(nadajZielony + "godzina: " + godzina + ":" + i + usunKolor);

                System.out.println(" na zmianie są: " + pracownicyNaZmianie);
            }

        }
    private static int iloscZamkniec = 0;
    public static void pracownikPoszedlDoDomu(int index, int godzina) {


        if(pracownicyNaZmianie.get(index) != null) {
            System.out.println("godzina:" + " " + godzina + " " + "do domu idzie: " + pracownicyNaZmianie.get(index));
            pracownicyNaZmianie.remove(index);
        }
        if(pracownicyNaZmianie.isEmpty()) {

            if (iloscZamkniec < 1)
                System.out.println("Zamknięto sklep");
            else
                System.out.print("");
            iloscZamkniec++;
        }

    }
    public static int spoznienie(){
        int i = losowoscWydarzen(30);

        return i;
    }

    public void choryPracownik(){
        System.out.println(nadajPomaranczowy + "pracownik się źle czuje i idzie do domu: " + usunKolor);
        System.out.println("na zmianie pozostali: " + pracownicyNaZmianie);
        pracownicyNaZmianie.remove(pracownicyNaZmianie.firstKey());
    }
    }



