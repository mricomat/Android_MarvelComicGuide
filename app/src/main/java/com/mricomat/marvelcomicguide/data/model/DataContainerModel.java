package com.mricomat.marvelcomicguide.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mricomat on 15/05/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataContainerModel<T> {

    @JsonProperty("offset")
    private int mOffset;
    @JsonProperty("limit")
    private int mLimit;
    @JsonProperty("total")
    private int mTotal;
    @JsonProperty("count")
    private int mCount;
    @JsonProperty("results")
    private T mResults;

    public DataContainerModel() {

    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public T getResults() {
        return mResults;
    }

    public void setResults(T results) {
        mResults = results;
    }
}
