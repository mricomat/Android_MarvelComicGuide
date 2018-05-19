package com.mricomat.marvelcomicguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mricomat on 15/05/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterModel implements Parcelable{

    @JsonProperty("id")
    private int mId;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("description")
    private String mDescription;
    @JsonProperty("thumbnail")
    private ImageModel mThumbnail;
    @JsonProperty("urls")
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
    }
}
