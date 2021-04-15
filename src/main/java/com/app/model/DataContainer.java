package com.app.model;

import java.util.List;

public class DataContainer<T> {
    private List<T> results;
    private int count;
    private int total;

    public DataContainer(List<T> results, int count, int total) {
        this.results = results;
        this.count = count;
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }
}
