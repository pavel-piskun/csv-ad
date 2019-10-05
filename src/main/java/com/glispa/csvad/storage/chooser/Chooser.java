package com.glispa.csvad.storage.chooser;

import com.glispa.csvad.model.Ad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Chooser {
    List<Ad> getAds(List<Ad> fullList, int count);
    String getType();
}
