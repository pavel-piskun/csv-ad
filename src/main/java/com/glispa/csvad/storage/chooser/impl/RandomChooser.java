package com.glispa.csvad.storage.chooser.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.storage.chooser.Chooser;
import com.glispa.csvad.storage.chooser.ChooserType;
import com.glispa.csvad.utils.ListUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomChooser implements Chooser {
    /**
     * Returns random ad list without duplicates
     * if {@code count} more than source list size - whole list will be returned
     */
    @Override
    public List<Ad> getAds(List<Ad> fullList, int count) {
        if(fullList.size() <= count) {
            return fullList;
        }
        return ListUtils.getRandomWithoutDuplicates(count, fullList);
    }

    @Override
    public String getType() {
        return ChooserType.RANDOM.name();
    }


}
