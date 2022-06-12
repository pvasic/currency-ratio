package com.pvasic.currencyratio.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gif {
    private Data [] data;

    public Gif() {
    }

    public Gif(Data[] data) {
        this.data = data;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }
}
