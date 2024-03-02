package com.example.zadanie1.controller;

import com.example.zadanie1.model.RepozytoriumZadan;
import com.example.zadanie1.model.Zadania;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//powoduje brak linkow href
@RepositoryRestController
class KontrolerZadan {
    private static final Logger logger = LoggerFactory.getLogger(KontrolerZadan.class);

    private final RepozytoriumZadan repozytorium;
    static int iloscProb = 0;

    static int iloscProb1 = 0;

    KontrolerZadan(final RepozytoriumZadan repozytorium){
        this.repozytorium = (RepozytoriumZadan) repozytorium;
    }

    //pozwala na sortowanie pomimo braku linkow href
    @GetMapping(value = "/zadania", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Zadania>> readAllTasks(){
        logger.warn("Uwidaczniam zadania!");
        return ResponseEntity.ok(repozytorium.findAll());
    }
    //dzięki użyciu list w typie danych zawartych w Response Entity
    //wyswietlamy bez linkow a zachowujemy sortowanie
    //loggery sluza do wyswietlania informacji w terminalu
    @GetMapping("/zadania")
    ResponseEntity<List<Zadania>> readAllTasks(Pageable strona){
        logger.info("Spersonalizowane stronnicowanie");
        return ResponseEntity.ok(repozytorium.findAll(strona).getContent());
    }
    @GetMapping("/zadania/{id}")
    ResponseEntity<Optional<Zadania>> znajdzZadaniePoIs(@PathVariable Integer id){
        return ResponseEntity.ok(repozytorium.findById(id));
    }
    @PostMapping("/tasks")
    ResponseEntity<List<?>> createTasks(@RequestBody  Zadania toCreate){
        repozytorium.save(toCreate);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/zadania/{id}")
    ResponseEntity<?> updateTaskById(@PathVariable Integer id, @RequestBody @Valid Zadania aktualizowane){
        if(id != 0){
            logger.info("id w put jest rowne = " + id);
            aktualizowane.Id = id;
            repozytorium.save(aktualizowane);}
        else {
            iloscProb++;
            logger.info("próba wprowadzenia 0 nr: " + iloscProb);
        }
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/zadania/{id}/{title}")
    ResponseEntity<?> updateTaskByTitle(@PathVariable int id, @PathVariable @Valid  String title, @RequestBody Zadania aktualizowane){
            aktualizowane.tytul = title;
            aktualizowane.Id = id;
            logger.info("zmieniam tytul dla id " + aktualizowane.Id + " na " + title);
            repozytorium.save(aktualizowane);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/zadania")
    ResponseEntity<Optional<Zadania>> usunWszystkie() {
        repozytorium.deleteAll();
        return ResponseEntity.noContent().build();
    }
    @Transactional
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id){
        if(id != 0){
            repozytorium.findById(id)
                    .ifPresent(task -> task.setWykonano(!task.isWykonano()));
            return ResponseEntity.noContent().build();
        }
        else{
            iloscProb1++;
            logger.info("id = 0 w Patch: localhost:8080/tasks/{id} po raz " + iloscProb1);
            return ResponseEntity.notFound().build();
        }
    }


    }
