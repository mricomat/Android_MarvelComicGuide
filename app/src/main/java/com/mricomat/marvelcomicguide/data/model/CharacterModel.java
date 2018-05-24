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
    @SerializedName("comics")
    private ComicListModel mComicsList;
    @SerializedName("series")
    private ComicListModel mSeriesList;
    @SerializedName("events")
    private ComicListModel mEventsList;
    @SerializedName("urls")
    private List<UrlModel> mUrlModels;

    public CharacterModel() {
        // Requires empty public constructor
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

    public ComicListModel getComicsList() {
        return mComicsList;
    }

    public void setComicsList(ComicListModel comicsList) {
        mComicsList = comicsList;
    }

    public ComicListModel getSeriesList() {
        return mSeriesList;
    }

    public void setSeriesList(ComicListModel seriesList) {
        mSeriesList = seriesList;
    }

    public ComicListModel getEventsList() {
        return mEventsList;
    }

    public void setEventsList(ComicListModel eventsList) {
        mEventsList = eventsList;
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
        mComicsList = in.readParcelable(ComicListModel.class.getClassLoader());
        mSeriesList = in.readParcelable(ComicListModel.class.getClassLoader());
        mEventsList = in.readParcelable(ComicListModel.class.getClassLoader());
        mUrlModels = in.createTypedArrayList(UrlModel.CREATOR);
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
        parcel.writeParcelable(mThumbnail, i);
        parcel.writeParcelable(mComicsList, i);
        parcel.writeParcelable(mSeriesList, i);
        parcel.writeParcelable(mEventsList, i);
        parcel.writeTypedList(mUrlModels);
    }
}
