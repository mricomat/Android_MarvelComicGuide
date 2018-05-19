package com.mricomat.marvelcomicguide.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mricomat on 15/05/2018.
 */

public class DataWrapperModel<T> {

    @JsonProperty("code")
    private int mCode;
    @JsonProperty("status")
    private String mStatus;
    @JsonProperty("copyright")
    private String mCopyright;
    @JsonProperty("attributionHTML")
    private String mAttributionText;
    @JsonProperty("data")
    private DataContainerModel<T> mData;

    public DataWrapperModel() {
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public String getAttributionText() {
        return mAttributionText;
    }

    public void setAttributionText(String attributionText) {
        mAttributionText = attributionText;
    }

    public DataContainerModel<T> getData() {
        return mData;
    }

    public void setData(DataContainerModel<T> data) {
        mData = data;
    }
}
