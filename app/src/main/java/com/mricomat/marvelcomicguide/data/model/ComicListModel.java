package com.mricomat.marvelcomicguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mricomat on 24/05/2018.
 */

public class ComicListModel implements Parcelable {

    @SerializedName("available")
    private int mAvailable;
    @SerializedName("returned")
    private int mReturned;
    @SerializedName("collectionURI")
    private String mCollectionURI;
    @SerializedName("items")
    private ArrayList<ComicModel> mComics;

    public ComicListModel() {
        // Requires empty public constructor
    }

    public int getAvailable() {
        return mAvailable;
    }

    public void setAvailable(int available) {
        mAvailable = available;
    }

    public int getReturned() {
        return mReturned;
    }

    public void setReturned(int returned) {
        mReturned = returned;
    }

    public String getCollectionURI() {
        return mCollectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        mCollectionURI = collectionURI;
    }

    public ArrayList<ComicModel> getComics() {
        return mComics;
    }

    public void setComics(ArrayList<ComicModel> comics) {
        mComics = comics;
    }

    protected ComicListModel(Parcel in) {
        mAvailable = in.readInt();
        mReturned = in.readInt();
        mCollectionURI = in.readString();
        mComics = in.createTypedArrayList(ComicModel.CREATOR);
    }

    public static final Creator<ComicListModel> CREATOR = new Creator<ComicListModel>() {
        @Override
        public ComicListModel createFromParcel(Parcel in) {
            return new ComicListModel(in);
        }

        @Override
        public ComicListModel[] newArray(int size) {
            return new ComicListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mAvailable);
        parcel.writeInt(mReturned);
        parcel.writeString(mCollectionURI);
        parcel.writeTypedList(mComics);
    }
}
