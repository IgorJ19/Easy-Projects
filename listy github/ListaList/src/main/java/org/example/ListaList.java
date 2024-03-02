package org.example;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaList {
    public void dniTygodnia(String dzien){
       pracownicy();
        System.out.println(dzien);
        try {
            godzinaWSklepie();
        } catch (InterruptedException ex) {
            Logger.getLogger(ListaList.class.getName()).log(Level.SEVERE, null, ex);
        }
        zbiorLosow.clear();
        pracownicyNaZmianie.clear();
    }
    static Scanner podany = new Scanner(System.in);
   public static void wybranyDzien(String podanyD) {
       if (podanyD.equals("poniedzialek") || podanyD.equals("Poniedzialek") || podanyD.equals("Poniedziałek") || podanyD.equals("poniedziałek"))
           System.out.println("ehhh... poniedzialek");
       else if (podanyD.equals("wtorek") || podanyD.equals("Wtorek"))
       System.out.println("dobrze że już po poniedziałku...");
       else if (podanyD.equals("sroda") || podanyD.equals("Sroda") || podanyD.equals("Środa") || podanyD.equals("środa"))
           System.out.println("środek tygodnia :) ");
        else  if (podanyD.equals("czwartek") || podanyD.equals("Czwartek"))
           System.out.println("prawie weekend");
       else if(podanyD.equals("piatek") || podanyD.equals("Piatek") || podanyD.equals("Piątek") || podanyD.equals("piątek") )
           System.out.println("w koncu weekend...");
        else  if (podanyD.equals("sobota") || podanyD.equals("Sobota"))
           System.out.println("weekend");
         else if(podanyD.equals("niedziela") || podanyD.equals("Niedziela")){
           System.out.println("niehandlowa :DDDDDDDDDD");
       System.exit(0);}
         else {
           System.out.println(nadajPomaranczowy + "to nie dzien tygodnia ;(, lub jest niepoprawnie zapisany" + usunKolor);
           System.exit(0);
       }


   }

    static String podanePrzezUzytkownika(){
        return podany.nextLine();
    }
    public static void main(String[] args) {
        ListaList dzien = new ListaList();
        System.out.println("podaj dzien: ");
        String podanyDzien = podanePrzezUzytkownika();
        wybranyDzien(podanyDzien);
        dzien.dniTygodnia(podanyDzien);



    }

    public static TreeMap<Integer, String> pracownicyNaZmianie = new TreeMap();
    public static void pracownicyNaZmianie(int Kolejnosc ,String ImieINazwisko){
        pracownicyNaZmianie.put(Kolejnosc, ImieINazwisko);
    }
    public static void pracownicy(){

        zbiorLosow.add(losPracownika);
        Pracownik pracownik = new Pracownik();

        switch (zbiorWolnychLosow.indexOf(losPracownika)) {
            case 0:

                pracownik.wariantPracownika(getNadajZolty + "Kasanowa Barbara" + usunKolor, "Sztrudel Damian", 0);
                break;
            case 1:
                pracownik.wariantPracownika(getNadajZolty + "Mateusz Tadeusz" + usunKolor, "Bomasz Tomasz", 1);
                break;
            case 2:
               pracownik.wariantPracownika(getNadajZolty + "Lindor Fryderyk" + usunKolor, "Hulk Roman", 2);
                break;
            case 3:
                pracownik.wariantPracownika(getNadajZolty + "Kora Ryszard" + usunKolor, "Bonarka Ludmila", 3);
                break;
            case 4:
                pracownik.wariantPracownika(getNadajZolty + "Buc Klaudia" + usunKolor, "Czerep Karolina", 4);
                break;
            case 5:
                pracownik.wariantPracownika(getNadajZolty + "Pac Maria" + usunKolor, "Kran Ludwik", 5);
                break;
            case 6:
                pracownik.wariantPracownika(getNadajZolty + "Kola Robert" + usunKolor, "Zlola Marian", 6);
                break;
            case 7:
                pracownik.wariantPracownika(getNadajZolty + "Gola Robert" + usunKolor, "Zkopa Kacper", 7);
                break;
            case 8:
                pracownik.wariantPracownika(getNadajZolty + "Pepsi Wiktoria" + usunKolor, "Zlekcji Natasza", 8);
                break;
            case 9:
                pracownik.wariantPracownika(getNadajZolty + "Piach Martyna" + usunKolor, "Sebastian-Bach Pawel", 9);
                break;
            case 10:
                pracownik.wariantPracownika(getNadajZolty + "Zbik Dominika" + usunKolor, "Bzik Emilia", 10);
                break;
            case 11:
                pracownik.wariantPracownika(getNadajZolty + "Piach Marcelina" + usunKolor, "Sebastian-Bach Norbert", 11);
                break;

            default:
                break;
        }
        /*
        public void dodajLosowegoPraocwnika(){
          for(ZbiorPracownikow pracownicy:listaPracownikow){
            Collections.shuffle(listaPracownikow);
                System.out.println(pracownicy.toString()):
         }
         */
    }

    private static ArrayList<Integer> zbiorLosow = new ArrayList<>();
    public static LinkedList<Integer> zbiorWolnychLosow = new LinkedList<>();
    public static int losPracownika;
    public static int kolejnosc = 0;
    public static int godzina = 300;

    public static String usunKolor = "\u001B[0m";
    public static String nadajFioletowy = "\u001B[35m";
    public static String nadajNiebieski = "\u001B[34m";
    public static String getNadajZolty = "\u001B[33m";
    public static String nadajZielony = "\u001B[32m";
    public static String nadajPomaranczowy = "\u001B[31m";

    private static void godzinaWSklepie() throws InterruptedException{
        ArrayList<ArrayList> regaly = new ArrayList<ArrayList>();
        //zakres ilosci istniejących pracowników
        int rozmiar = zbiorWolnychLosow.size()-1;;
        Thread watekGodziny;
        watekGodziny = new Thread();
        boolean koniec = false;
        if(zbiorWolnychLosow.size() < 12)
        for(int i =0; i<12; i++){
            zbiorWolnychLosow.add(i);
        }

        while(koniec != true){
            Pracownik pracownik = new Pracownik();
            for(int j = 0; j<401; j++){
                //opoznienie akcji przyjscia
                watekGodziny.sleep(150);

                //nadanie zakresu wyboru liczb losowych
                losPracownika = losowoscWydarzen(11);
                Towar towar = new Towar();
                towar.losoweZdarzenia();

                los = losowoscWydarzen(100);
                if(j == 0){
                    int ilePrzed = 60-pracownik.spoznienie();
                    System.out.print(nadajZielony + "godzina: " + "6:" + ilePrzed  + " sklep otwarty, otworzył:" + usunKolor);
                    pracownicy();
                    System.out.println(pracownicyNaZmianie.get(1));
                    towar.wydarzeniaDniaPiekarnia();
                    towar.zabierzZKoszyka();
                    System.out.println(" na zmianie są: " + pracownicyNaZmianie);



                }
                if(j == 50){
                    pracownik.przyjscieDoPracy(8, 2);
                    towar.wydarzeniaPiekarnia();
                    towar.wydarzeniaDniaMleczarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();
                    towar.wydarzeniaPiekarnia();
                    towar.wydarzeniaDniaMleczarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();



                }
                if(j == 100){
                   pracownik.przyjscieDoPracy(10, 3);
                    towar.wydarzeniaPiekarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();
                    towar.wydarzeniaDniaMleczarnia();
                    towar.dorzucamy();
                    towar.wydarzeniaPiekarnia();
                    towar.zabierzZKoszyka();

                }
                if(j == 150){
                   pracownik.przyjscieDoPracy(12, 4);

                    towar.wydarzeniaDniaMleczarnia();
                    towar.dorzucamy();
                   towar.wydarzeniaPiekarnia();
                    towar.zabierzZKoszyka();
                    towar.usunArtykuly(towar.polkaSrodkowa);
                    towar.wydarzeniaPiekarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();


                }
                if(j == 200){
                   pracownik.przyjscieDoPracy(14, 5);
                    towar.zabierzZKoszyka();
                    towar.dorzucamy();
                   pracownik.pracownikPoszedlDoDomu(1, 14);
                   towar.usunArtykuly(towar.polkaGorna);

                }
                if(j == 250){
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();
                    towar.zabierzZKoszyka();
                    towar.dorzucamy();
                    towar.usunArtykuly(towar.polkaSrodkowaR2);
                    pracownik.pracownikPoszedlDoDomu(2, 16);
                }
                if(j == 300){
                    towar.wydarzeniaPiekarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();
                    towar.usunArtykuly(towar.polkaSrodkowaR2);
                    towar.wydarzeniaDniaMleczarnia();
                    towar.dorzucamy();

                    pracownik.pracownikPoszedlDoDomu(3, 18);
                }
                if(j == 350){
                    towar.wydarzeniaDniaMleczarnia();
                    towar.dorzucamy();
                    towar.wydarzeniaPiekarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();


                    pracownik.pracownikPoszedlDoDomu(4, 20);
                }
                if(j == 400){
                    towar.zabierzZKoszyka();
                    towar.dorzucamy();
                    towar.wydarzeniaPiekarnia();
                    towar.dorzucamy();
                    towar.zabierzZKoszyka();
                    pracownik.pracownikPoszedlDoDomu(5, 22);

                }
            }
            System.out.println(zbiorLosow);
            koniec = true;

        }

    }
    public static int losowoscWydarzen(int granica){
        Random los = new Random();
        int int_random = los.nextInt(granica);
        return int_random;


    }

    private static int los;


}

