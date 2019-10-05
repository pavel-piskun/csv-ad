package com.glispa.csvad.storage.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.storage.AdLoader;
import com.glispa.csvad.storage.AdStorage;
import com.glispa.csvad.storage.chooser.ChooserProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class DefaultAdStorage implements AdStorage {
    @Autowired
    private AdLoader adLoader;
    @Autowired
    private ChooserProvider chooserProvider;

    private List<Ad> adList;

    public DefaultAdStorage() {
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
        //TODO: validate thai id's are unique
    }


}
