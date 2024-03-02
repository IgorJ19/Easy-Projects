package com.example.zadanie1.controller;

import com.example.zadanie1.WlasciwosciKonfiguracjiZadan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.RestController;

@RestController
class KontrolerInformacji {

    private DataSourceProperties zrodloDanych;
    private WlasciwosciKonfiguracjiZadan mojeWlasciwosci;
    public KontrolerInformacji(DataSourceProperties zrodloDanych, WlasciwosciKonfiguracjiZadan mojeWlasciwosci){
        this.zrodloDanych = zrodloDanych;
        this.mojeWlasciwosci = mojeWlasciwosci;
    }
    @GetMapping("/info/url")
    String url (){
        return zrodloDanych.getUrl();
    }
    @GetMapping("/info/prop")
    boolean myProp(){
        return mojeWlasciwosci.isAllowMultipleTaskFromTemplate();
    }
    @GetMapping("/zagraj/1")
    String zagraj () throws InterruptedException {
        Thread watek = new Thread();
        String watek1 = null;
        watek.start();
        for(int i =0; i <100; i++){
        watek.sleep(300);
            watek1 = " " + i;
        }
        return watek1;
    }
}
