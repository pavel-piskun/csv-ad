package com.glispa.csvad.storage;

import com.glispa.csvad.model.Ad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdStorage {
    List<Ad> getAllAds();
    List<Ad> get(int count, String chooserType);
}
