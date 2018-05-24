package com.mricomat.marvelcomicguide.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mricomat on 15/05/2018.
 */

public class DataWrapperModel<T> {

    @SerializedName("code")
    private int mCode;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("attributionHTML")
    private String mAttributionText;
    @SerializedName("data")
    private DataContainerModel<T> mData;

    public DataWrapperModel() {
        // Requires empty public constructor
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
