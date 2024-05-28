package com.epam.rd.autotasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "data", collectionResourceRel = "data base")
public interface Repozytory extends JpaRepository<DbManager, Integer> {

}
