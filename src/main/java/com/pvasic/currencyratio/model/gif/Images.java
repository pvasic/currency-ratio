package com.pvasic.currencyratio.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    private Original original;

    public Images() {
    }

    public Images(Original original) {
        this.original = original;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }
}
