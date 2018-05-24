package com.mricomat.marvelcomicguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mricomat on 15/05/2018.
 */

public class ImageModel implements Parcelable{

    @SerializedName("path")
    private String mPath;
    @SerializedName("extension")
    private String mExtension;

    public ImageModel() {
        // Requires empty public constructor
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getExtension() {
        return mExtension;
    }

    public void setExtension(String extension) {
        mExtension = extension;
    }

    public String getCompleteImagePath() {
        return mPath + "." + mExtension;
    }

    protected ImageModel(Parcel in) {
        mPath = in.readString();
        mExtension = in.readString();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mPath);
        parcel.writeString(mExtension);
    }
}
