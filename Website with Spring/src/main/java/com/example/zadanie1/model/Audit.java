package com.example.zadanie1.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class Audit {
    public LocalDateTime utworzono;

    private LocalDateTime zaktualizowano;
    @PrePersist
    void prePersist(){
        utworzono = LocalDateTime.now();
    }
    @PreUpdate
    void preMerge(){
        zaktualizowano = LocalDateTime. now();
    }
    public LocalDateTime getUtworzono() {
        return utworzono;
    }

    public void setUtworzono(LocalDateTime utworzono) {
        this.utworzono = utworzono;
    }
}
