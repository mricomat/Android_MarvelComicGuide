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
public class ComicModel implements Parcelable{

    @JsonProperty("id")
    private Integer mId;
    @JsonProperty("title")
    private String mTitle;
    @JsonProperty("description")
    private Object mDescription;
    @JsonProperty("startYear")
    private Integer mStartYear;
    @JsonProperty("endYear")
    private Integer mEndYear;
    @JsonProperty("rating")
    private String mRating;
    @JsonProperty("type")
    private String mType;
    @JsonProperty("modified")
    private String mModified;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("resourceURI")
    private String mResourceUri;
    @JsonProperty(value = "thumbnail")
    private ImageModel mThumbnail;
    @JsonProperty(value = "images")
    private List<ImageModel> mImageList;

    public ComicModel() {

    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Object getDescription() {
        return mDescription;
    }

    public void setDescription(Object description) {
        mDescription = description;
    }

    public Integer getStartYear() {
        return mStartYear;
    }

    public void setStartYear(Integer startYear) {
        mStartYear = startYear;
    }

    public Integer getEndYear() {
        return mEndYear;
    }

    public void setEndYear(Integer endYear) {
        mEndYear = endYear;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getResourceUri() {
        return mResourceUri;
    }

    public void setResourceUri(String resourceUri) {
        mResourceUri = resourceUri;
    }

    public ImageModel getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(ImageModel thumbnail) {
        mThumbnail = thumbnail;
    }

    public List<ImageModel> getImageList() {
        return mImageList;
    }

    public void setImageList(List<ImageModel> imageList) {
        mImageList = imageList;
    }

    protected ComicModel(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mTitle = in.readString();
        if (in.readByte() == 0) {
            mStartYear = null;
        } else {
            mStartYear = in.readInt();
        }
        if (in.readByte() == 0) {
            mEndYear = null;
        } else {
            mEndYear = in.readInt();
        }
        mRating = in.readString();
        mType = in.readString();
        mModified = in.readString();
        mName = in.readString();
        mResourceUri = in.readString();
    }

    public static final Creator<ComicModel> CREATOR = new Creator<ComicModel>() {
        @Override
        public ComicModel createFromParcel(Parcel in) {
            return new ComicModel(in);
        }

        @Override
        public ComicModel[] newArray(int size) {
            return new ComicModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (mId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mId);
        }
        parcel.writeString(mTitle);
        if (mStartYear == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mStartYear);
        }
        if (mEndYear == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mEndYear);
        }
        parcel.writeString(mRating);
        parcel.writeString(mType);
        parcel.writeString(mModified);
        parcel.writeString(mName);
        parcel.writeString(mResourceUri);
    }
}
