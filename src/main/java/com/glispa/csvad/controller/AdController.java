package com.glispa.csvad.controller;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdController {
    @Autowired
    private AdService adService;

    @GetMapping("/GetAds")
    public List<Ad> getAds(@RequestParam(required = false) Integer count,
                           @RequestParam(required = false) String strategy) {
        return adService.getAdList(count, strategy);
    }
}
