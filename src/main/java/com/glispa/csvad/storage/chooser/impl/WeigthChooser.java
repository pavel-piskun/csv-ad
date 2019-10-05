package com.glispa.csvad.storage.chooser.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.storage.chooser.Chooser;
import com.glispa.csvad.storage.chooser.ChooserType;

import java.util.List;

public class WeigthChooser implements Chooser {
    //TODO
    @Override
    public List<Ad> getAds(List<Ad> fullList, int count) {
        return null;
    }

    @Override
    public String getType() {
        return ChooserType.WEIGHT.name();
    }
}
