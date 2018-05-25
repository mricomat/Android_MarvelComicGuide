package com.mricomat.marvelcomicguide.data.model;

/**
 * Created by mricomat on 25/05/2018.
 */

public class ErrorResponseModel {

    private String mErrorMessageTitle;
    private String mErrorMessageDetail;
    private String mLabelCta;
    private String mOperationCta;

    public ErrorResponseModel() {
    }

    public String getErrorMessageTitle() {
        return mErrorMessageTitle;
    }

    public void setErrorMessageTitle(String errorMessageTitle) {
        mErrorMessageTitle = errorMessageTitle;
    }

    public String getErrorMessageDetail() {
        return mErrorMessageDetail;
    }

    public void setErrorMessageDetail(String errorMessageDetail) {
        mErrorMessageDetail = errorMessageDetail;
    }

    public String getLabelCta() {
        return mLabelCta;
    }

    public void setLabelCta(String labelCta) {
        mLabelCta = labelCta;
    }

    public String getOperationCta() {
        return mOperationCta;
    }

    public void setOperationCta(String operationCta) {
        mOperationCta = operationCta;
    }
}
