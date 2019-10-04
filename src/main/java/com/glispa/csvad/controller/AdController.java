package com.glispa.csvad.controller;

import com.glispa.csvad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdController {
    @Autowired
    private AdService adService;

    @GetMapping("/GetAds")
    public String getAds() {
        return "Greetings from Spring Boot!";
    }
}
