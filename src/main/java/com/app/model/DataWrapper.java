package com.app.model;

public class DataWrapper<T> {
    private String attributionText;
    private DataContainer<T> data;

    public DataWrapper(String attributionText, DataContainer<T> data) {
        this.attributionText = attributionText;
        this.data = data;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public DataContainer<T> getData() {
        return data;
    }
}
