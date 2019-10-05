package com.glispa.csvad.service;

import com.glispa.csvad.model.Ad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdService {
    List<Ad> getAdList(Integer count, String strategy);
}
