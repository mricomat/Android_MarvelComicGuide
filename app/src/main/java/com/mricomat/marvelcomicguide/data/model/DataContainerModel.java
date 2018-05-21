package com.mricomat.marvelcomicguide.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mricomat on 15/05/2018.
 */

public class DataContainerModel<T> {

    @SerializedName("offset")
    private int mOffset;
    @SerializedName("limit")
    private int mLimit;
    @SerializedName("total")
    private int mTotal;
    @SerializedName("count")
    private int mCount;
    @SerializedName("results")
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
