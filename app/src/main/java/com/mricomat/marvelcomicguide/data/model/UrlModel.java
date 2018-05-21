package com.mricomat.marvelcomicguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mricomat on 15/05/2018.
 */

public class UrlModel implements Parcelable{

    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;

    public UrlModel() {
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    protected UrlModel(Parcel in) {
        mType = in.readString();
        mUrl = in.readString();
    }

    public static final Creator<UrlModel> CREATOR = new Creator<UrlModel>() {
        @Override
        public UrlModel createFromParcel(Parcel in) {
            return new UrlModel(in);
        }

        @Override
        public UrlModel[] newArray(int size) {
            return new UrlModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mType);
        parcel.writeString(mUrl);
    }
}
