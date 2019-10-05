package com.glispa.csvad.storage.chooser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChooserProvider {
    @Autowired
    List<Chooser> chooserList;

    public Chooser get(String type) {
        for(Chooser chooser: chooserList) {
            if(chooser.getType().equals(type)) {
                return chooser;
            }
        }
        throw new UnsupportedOperationException("Unknown strategy type: " + type);
    }
}
