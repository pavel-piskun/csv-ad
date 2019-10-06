package com.glispa.csvad.service.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.service.AdService;
import com.glispa.csvad.storage.AdStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class DefaultAdService implements AdService {
    @Value("${default.ad.count}")
    private int defaultAdCount;
    @Value("${default.chooser.type}")
    private String defaultChooserType;

    @Autowired
    private AdStorage adStorage;


    @Override
    public List<Ad> getAdList(Integer count, String strategy) {
        if(Objects.isNull(count)) {
            count = defaultAdCount;
        }
        if(Objects.isNull(strategy)) {
            strategy = defaultChooserType;
        }
        return adStorage.get(count, strategy);
    }
}
