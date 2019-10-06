package com.glispa.csvad.storage.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.storage.AdLoader;
import com.glispa.csvad.storage.AdStorage;
import com.glispa.csvad.storage.chooser.ChooserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

@Component
public class DefaultAdStorage implements AdStorage {

    private AdLoader adLoader;
    private ChooserProvider chooserProvider;

    private List<Ad> adList;

    @Autowired
    public DefaultAdStorage(AdLoader adLoader, ChooserProvider chooserProvider) {
        this.adLoader = adLoader;
        this.chooserProvider = chooserProvider;
        init();
    }

    @Override
    public List<Ad> getAllAds() {
        return this.adList;
    }

    @Override
    public List<Ad> get(int count, String chooserType) {
        return chooserProvider.get(chooserType).getAds(adList, count);
    }

    private void init() {
        this.adList = Collections.unmodifiableList(adLoader.load());
        validate(adList);
    }

    private void validate(List<Ad> adList) {
        long uniqueIdAmount = adList.stream()
                .map(Ad::getId)
                .distinct()
                .count();
        if (uniqueIdAmount != adList.size()) {
            throw new ValidationException("List contains non unique Ad's id.");
        }
    }

}