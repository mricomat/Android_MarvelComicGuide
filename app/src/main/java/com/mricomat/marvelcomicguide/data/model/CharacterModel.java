package com.mricomat.marvelcomicguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mricomat on 15/05/2018.
 */

public class CharacterModel implements Parcelable {

    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("thumbnail")
    private ImageModel mThumbnail;
    @SerializedName("urls")
    private List<UrlModel> mUrlModels;

    public CharacterModel() {

    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public ImageModel getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(ImageModel thumbnail) {
        mThumbnail = thumbnail;
    }

    public List<UrlModel> getUrlModels() {
        return mUrlModels;
    }

    public void setUrlModels(List<UrlModel> urlModels) {
        mUrlModels = urlModels;
    }

    protected CharacterModel(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(ImageModel.class.getClassLoader());
        mUrlModels = in.createTypedArrayList(UrlModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeParcelable(mThumbnail, flags);
        dest.writeTypedList(mUrlModels);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CharacterModel> CREATOR = new Creator<CharacterModel>() {
        @Override
        public CharacterModel createFromParcel(Parcel in) {
            return new CharacterModel(in);
        }

        @Override
        public CharacterModel[] newArray(int size) {
            return new CharacterModel[size];
        }
    };
}
