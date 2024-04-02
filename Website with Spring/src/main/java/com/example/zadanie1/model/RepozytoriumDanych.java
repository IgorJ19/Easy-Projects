package com.example.zadanie1.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource(path = "dane", collectionResourceRel = "baza danych")
public interface RepozytoriumDanych extends JpaRepository<Dane, Integer> {
   /*
   usuwanie przez Id lub bez, false oznacza wyłączenie możliwości usuwania
   @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Zadania entity);
    */



}
