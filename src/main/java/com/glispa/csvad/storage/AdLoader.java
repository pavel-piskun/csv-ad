package com.glispa.csvad.storage;

import com.glispa.csvad.model.Ad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdLoader {
    List<Ad> load();
}
