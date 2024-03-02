package com.example.zadanie1.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource(path = "zadania", collectionResourceRel = "do zrobienia")
public interface RepozytoriumZadan extends JpaRepository<Zadania, Integer> {
   /*
   usuwanie przez Id lub bez, false oznacza wyłączenie możliwości usuwania
   @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Zadania entity);
    */

    @RestResource(path = "wykonano", rel = "wykonano")
    List<Zadania> findByWykonano(@Param("wykonano")boolean wykonano);


}
