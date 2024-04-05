package com.example.zadanie1.controller.controller;

import com.example.zadanie1.model.RepozytoriumDanych;
import com.example.zadanie1.model.Dane;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//powoduje brak linkow href
@RepositoryRestController
class KontrolerDanych {
    private static final Logger logger = LoggerFactory.getLogger(KontrolerDanych.class);

    private final RepozytoriumDanych repozytorium;
    static int iloscProb = 0;

    static int iloscProb1 = 0;

    KontrolerDanych(final RepozytoriumDanych repozytorium){
        this.repozytorium = (RepozytoriumDanych) repozytorium;
    }

    //pozwala na sortowanie pomimo braku href
    @GetMapping(value = "/dane/pobierz", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Dane>> wczytajDane(){
        logger.warn("Uwidaczniam dane!");
        return ResponseEntity.ok(repozytorium.findAll());
    }



    //dzięki użyciu list w typie danych zawartych w Response Entity
    //wyswietlamy bez linkow a zachowujemy sortowanie
    @GetMapping("/dane/{id}")
    ResponseEntity<Optional<Dane>> znajdzDanePoID(@PathVariable Integer id){
        return ResponseEntity.ok(repozytorium.findById(id));
    }
    @PostMapping("/dane/stworz")
    ResponseEntity<List<?>> utworzDane(@RequestBody  Dane toCreate){
        repozytorium.save(toCreate);
        return  ResponseEntity.ok().build();
    }
    @PutMapping("/dane/wrzuc/{id}")
    ResponseEntity<List<?>> zmienDanePoID(@PathVariable Integer id, @RequestBody @Valid Dane aktualizowane) {
        if (id != 0) {
            aktualizowane.setID(id);
            repozytorium.save(aktualizowane);
        }
        else {
            iloscProb++;
            logger.info("próba wprowadzenia 0 nr: " + iloscProb);
        }
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/dane/wrzuc/{id}/{title}")
    ResponseEntity<?> zakutalizujDanePoIDiTytule(@PathVariable int id, @PathVariable @Valid  String title, @RequestBody Dane aktualizowane){
        aktualizowane.nazwa = title;
        aktualizowane.ID = id;
        logger.info("zmieniam tytul dla id " + aktualizowane.ID + " na " + title);
        repozytorium.save(aktualizowane);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/dane/usun")
    ResponseEntity<Optional<Dane>> usunWszystkie() {
        repozytorium.deleteAll();
        return ResponseEntity.noContent().build();
    }
    @Transactional
    @PatchMapping("/dane/patch/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        if(id != 0){
            return ResponseEntity.noContent().build();
        }
        else{
            iloscProb1++;
            logger.info("id = 0 w Patch: localhost:8080/tasks/{id} po raz " + iloscProb1);
            return ResponseEntity.notFound().build();
        }
    }


}
