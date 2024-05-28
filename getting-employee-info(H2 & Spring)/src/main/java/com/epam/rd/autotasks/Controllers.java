package com.epam.rd.autotasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controllers {

    private final Repozytory repozytory;

    Controllers(final Repozytory repozytorium){
        this.repozytory = (Repozytory) repozytorium;
    }
    private static final Logger logger = LoggerFactory.getLogger(Controllers.class);

    @PostMapping(value = "/departments/post")

    ResponseEntity<List<DbManager>> uploadDepartments(@RequestBody DbManager toCreate){
        logger.warn("Dodaję dane!");
        repozytory.save(toCreate);
        return  ResponseEntity.ok().build();
    }
    @GetMapping(value = "/departments/download", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<DbManager>> loadDepartments(){
        logger.warn("Uwidaczniam dane!");
        return ResponseEntity.ok(repozytory.findAll());
    }
}
