package com.example.zadanie1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "zadania")
public class GrupaZadan {
    @NotBlank(message = "opis nie może być pusty")
    private  String opis;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;
    private boolean wykonano;
    public String tytul = "Tytul zadania numer: ";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupa")
    private Set<Zadania> zadania;


    GrupaZadan(){
    }
    //gettery i settery muszą pozostać nie naruszone(nazwy metod) gdyż
    //są czytane przez Springa w postmanie
    public String getTytul() {
        tytul = tytul + Id;
        return tytul;
    }

    public void setTytul(String tytul) {
        if(tytul == null){
        tytul = tytul + Id;
        this.tytul = tytul;}
        else
            this.tytul = tytul;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isWykonano() {
        return wykonano;
    }

    public void setWykonano(boolean wykonano) {
        this.wykonano = wykonano;
    }

    public Set<Zadania> getZadania() {
        return zadania;
    }

    void setZadania(Set<Zadania> zadania) {
        this.zadania = zadania;
    }
}
