package org.example;

import java.util.*;

public class Towar extends ListaList{
    //lista regalow
    ArrayList<ArrayList> regaly = new ArrayList<ArrayList>();

    //lity zawarte w skład regałów, regały zawierające półki
    ArrayList<LinkedList> RegalPierwszy = new ArrayList<LinkedList>(3);
    ArrayList<LinkedList> RegalDrugi = new ArrayList<LinkedList>(3);

    //listy powiązane zawierające proukty.
    LinkedList<String> polkaGorna = new LinkedList<String>();
    LinkedList<String> polkaSrodkowa = new LinkedList<String>();
    LinkedList<String> polkaDolna = new LinkedList<String>();

    LinkedList<String> polkaGornaR2 = new LinkedList<String>();
    LinkedList<String> polkaSrodkowaR2 = new LinkedList<String>();
    LinkedList<String> polkaDolnaR2 = new LinkedList<String>();

    //Stack
   static Stack<String> koszykPromocyjny = new Stack<String>();

    public void  sprawdzArtykuly(ArrayList<LinkedList> regal,LinkedList<String> polka, String zJakiejPolki){


        ListIterator iteratorListy = polka.listIterator(0);
        System.out.println("sprawdzam artykuly z polki " + zJakiejPolki);

        while(iteratorListy.hasNext()){
            System.out.print(iteratorListy.next() + " | ");
        }
        if(!iteratorListy.hasPrevious())
            if(!iteratorListy.hasNext()){
                System.out.println(nadajPomaranczowy + "polka jest pusta" + usunKolor);
            }

        System.out.println("");


    }

    public void dodajArtykuly(ArrayList<LinkedList> regal, LinkedList polka, String artykul){
        ListIterator iterator =  polka.listIterator(polka.size());
        if(!pracownicyNaZmianie.isEmpty()) {
            if (iterator.hasPrevious()) {
                System.out.println("dokladam artykul: " + artykul + " ,obok " + polka.toString());
            }

            polka.push(artykul);
        }

    }
    static int polkaPusta = 0;
    public void usunArtykuly(LinkedList lista){


        if(!lista.isEmpty()){
        godzinaWSklepie();
        System.out.println("artykuly" + lista.getFirst() + "sie przeterminowal, wyrzucam go");
        polkaPusta = 0;
        lista.remove(0);}
        else if(polkaPusta < 1){
        System.out.println(nadajPomaranczowy + "polka wyczyszczona" + usunKolor);
        polkaPusta++;}

    }

    public void dorzucDoKoszyka(String artykul){

        System.out.println("dorzucam artykul promocyjny do koszyka: " + artykul + " | ");

        koszykPromocyjny.add(artykul);

    }
    ArrayList<String> listaDorzucanych = new ArrayList<>();
    public void dorzucamy(){
        int granica = 16;
        int los = losowoscWydarzen(granica);
        listaDorzucanych.add("chleb tostowy");
        listaDorzucanych.add("płarki owsiane");
        listaDorzucanych.add("pieczarki");
        listaDorzucanych.add("gazeta");
        listaDorzucanych.add("zabawka");
        listaDorzucanych.add("skarpeta");
        Iterator iter = listaDorzucanych.iterator();
            if(!pracownicyNaZmianie.isEmpty()){
            if(los < listaDorzucanych.size())
            dorzucDoKoszyka(listaDorzucanych.get(los));
            }

    }

    public void zabierzZKoszyka(){
        if(!pracownicyNaZmianie.isEmpty()) {
            int los = losowoscWydarzen(5);
            if (koszykPromocyjny.isEmpty()) {
                System.out.println(nadajPomaranczowy + "koszyk pusty " + usunKolor);
                dorzucDoKoszyka("kredki");
            } else if (los > 3) {
                System.out.println("zabrano z koszyka: " + koszykPromocyjny.lastElement());

                koszykPromocyjny.pop();

                System.out.println("w koszyku pozostały: " + koszykPromocyjny);
            }

        }
    }
    public void zabierzZPolki(ArrayList<LinkedList> regal, LinkedList<String> polka){
        if(!polka.isEmpty() && !pracownicyNaZmianie.isEmpty()){
           String artykul = polka.getFirst();
            System.out.println("zabieram z polki: " + artykul);
            polka.removeFirst();

            if(polka.isEmpty())
                for(int i = 0; i < 4; i++)
                dodajArtykuly(regal, polka, "artykul odpowiadajacy polce");

        }
    }
    public void wydarzeniaDniaPiekarnia(){

            int zakres = losowoscWydarzen(100);
            Towar towar = new Towar();


            if(zakres >=0 && zakres <= 50) {
                dodajArtykuly(RegalPierwszy, polkaDolna, "Grachamka");
                dodajArtykuly(RegalPierwszy, polkaDolna, "Chleb");
                dodajArtykuly(RegalPierwszy, polkaDolna, "Kajzerka");
            }
            if(zakres >54 ) {
                dodajArtykuly(RegalPierwszy, polkaDolna, "Ciemna");
                dodajArtykuly(RegalPierwszy, polkaDolna, "Chleb ziarnisty");
                dodajArtykuly(RegalPierwszy, polkaDolna, "Kajzerka");
            }
            if(zakres >50 && zakres <=54)
                System.out.println("piec sie zepsul");

            sprawdzArtykuly(RegalPierwszy, polkaDolna, "dolnej");
    }

    public void wydarzeniaDniaMleczarnia(){
    int i = losowoscWydarzen(2);
        for(int j = 0; j < i; j++)
            dodajArtykuly(RegalPierwszy, polkaDolna, "mleko");


        for(int j = 0; j< i ; j++)
        dodajArtykuly(RegalPierwszy, polkaDolna, "maslo");

        for(int j = 0; j< i; j++)
        dodajArtykuly(RegalPierwszy, polkaDolna, "ser");
    }

    public void wydarzeniaPiekarnia(){
        int los = losowoscWydarzen(100);
        if(los < 10)
            zabierzZPolki(RegalPierwszy, polkaDolna);
        if(los >= 45 && los < 60)
            zabierzZPolki(RegalPierwszy, polkaSrodkowa);
        if(los > 90)
            zabierzZPolki(RegalPierwszy, polkaGorna);
    }
    ArrayList<Towar> losowe = new ArrayList<>();
    public void losoweZdarzenia(){
        if(!pracownicyNaZmianie.isEmpty()) {
            godzina += 2;
            int losowosc = losowoscWydarzen(100);
            if (losowosc == 23) {
                usunArtykuly(polkaSrodkowa);

            }
            if (losowosc > 62 && losowosc < 68) {
                usunArtykuly(polkaGorna);
                zabierzZKoszyka();
                godzinaWSklepie();
            }
            if (losowosc > 87 && losowosc < 91) {
                wydarzeniaDniaPiekarnia();
                godzinaWSklepie();
            }
            if (losowosc > 99) {
                Pracownik pracownik = new Pracownik();
                pracownik.choryPracownik();
                godzinaWSklepie();
            }
        }
    }
    private void godzinaWSklepie(){
        if(godzina%50 > 9)
        System.out.println(nadajFioletowy + "godzina : " + godzina/50 + ":" +(godzina%50) + usunKolor);
        else
            System.out.println(nadajFioletowy + "godzina : " + godzina/50 + ":0" +(godzina%50) + usunKolor);
    }



}
