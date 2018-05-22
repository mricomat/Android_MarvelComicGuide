package com.mricomat.marvelcomicguide.ui.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mricomat.marvelcomicguide.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mricomat on 21/05/2018.
 */

public class ProgressBarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    public ProgressBarViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }
}
