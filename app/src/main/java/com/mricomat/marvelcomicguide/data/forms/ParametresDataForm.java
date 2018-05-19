package com.mricomat.marvelcomicguide.data.forms;

/**
 * Created by mricomat on 15/05/2018.
 */

public class ParametresDataForm {

    private String mNameQuery;
    private String mNameStartWithQuery;
    private int mOffset;
    private int mLimit;

    public ParametresDataForm() {

    }

    public String getNameQuery() {
        return mNameQuery;
    }

    public void setNameQuery(String nameQuery) {
        mNameQuery = nameQuery;
    }

    public String getNameStartWithQuery() {
        return mNameStartWithQuery;
    }

    public void setNameStartWithQuery(String nameStartWithQuery) {
        mNameStartWithQuery = nameStartWithQuery;
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
}
