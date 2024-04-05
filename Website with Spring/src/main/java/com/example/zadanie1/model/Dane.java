package com.example.zadanie1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "Dane")
public class Dane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;

    @NotBlank(message = "nazwa nie może być pusta")
    public String nazwa;

    public Date data = new Date();

    @NotBlank(message = "proszę o podanie danych kontaktowych")
    public String kontakt;

    @NotBlank(message = "wiadmość nie może być pusta")
    public  String wiadomosc;



    Dane(){
    }
    //gettery i settery muszą pozostać nie naruszone(nazwy metod) gdyż
    //są czytane przez Springa w postmanie
    public String getNazwa() {
        nazwa = nazwa + " " + ID;
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if(nazwa == null){
            nazwa = nazwa + ID;
            this.nazwa = nazwa;}
        else
            this.nazwa = nazwa;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }
    public Date getData() { return this.data; }
    void setData(Date data) { this.data = data; }
    public void updateFrom(final Dane source){
        wiadomosc = source.wiadomosc;
        data = source.data;
        nazwa = source.nazwa;
    }


}
