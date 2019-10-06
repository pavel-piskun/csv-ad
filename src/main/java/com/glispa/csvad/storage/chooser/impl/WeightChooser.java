package com.glispa.csvad.storage.chooser.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.storage.chooser.Chooser;
import com.glispa.csvad.storage.chooser.ChooserType;
import com.glispa.csvad.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.glispa.csvad.utils.MathUtil.getDistributedAdAmount;

@Component
public class WeightChooser implements Chooser {
    private static final String OTHER = "other";

    private Map<String, Integer> campaigns;

    private Map<String, List<Ad>> campaignAdMap = null;

    @Autowired
    public WeightChooser(@Value(("#{${campaigns}}")) Map<String, Integer> campaigns) {
        validateCompaingConfig(campaigns);
        this.campaigns = campaigns;
    }

    private void validateCompaingConfig(Map<String, Integer> campaigns) {
        if(100 < campaigns.values().stream().reduce(0, Integer::sum)) {
            throw new IllegalStateException("Sum of campaign weights can't be more than 100");
        }
    }

    @Override
    public String getType() {
        return ChooserType.WEIGHT.name();
    }

    @Override
    public List<Ad> getAds(List<Ad> fullList, int count) {
        if (campaignAdMap == null) {
            buildCampaignMap(fullList);
        }

        return generateDistributedAdsList(count);
    }

    private void buildCampaignMap(List<Ad> fullList) {
        synchronized (this) {
            if (campaignAdMap == null) {
                initCampaignMap();
                fillCampaignMap(fullList);
            }
        }
    }

    private void initCampaignMap() {
        campaignAdMap = new HashMap<>();
        for(String campaignName: this.campaigns.keySet()) {
            campaignAdMap.put(campaignName, new ArrayList<>());
        }
        campaignAdMap.put(OTHER, new ArrayList<>());
    }

    private void fillCampaignMap(List<Ad> fullList) {
        for (Ad ad : fullList) {
            if (StringUtils.isBlank(ad.getCampaign())) {
                continue;
            }
            boolean isInCampaign = false;
            for(String campaignName: this.campaigns.keySet()) {
                if (ad.getCampaign().equals(campaignName)) {
                    campaignAdMap.get(campaignName).add(ad);
                    isInCampaign = true;
                }
            }
            if(!isInCampaign) {
                campaignAdMap.get(OTHER).add(ad);
            }
        }
    }

    private List<Ad> generateDistributedAdsList(int count) {
        List<Ad> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: this.campaigns.entrySet()) {
            populateAdsResultList(result, getDistributedAdAmount(count, entry.getValue()), entry.getKey());
        }
        populateAdsResultList(result, count-result.size(), OTHER);
        return result;
    }

    private void populateAdsResultList(List<Ad> result, int weightCount, String key) {
        List<Ad> ads = campaignAdMap.get(key);
        if (ads.isEmpty()) {
            return;
        }
        result.addAll(ListUtils.getRandomWithoutDuplicates(weightCount, ads));
    }
}