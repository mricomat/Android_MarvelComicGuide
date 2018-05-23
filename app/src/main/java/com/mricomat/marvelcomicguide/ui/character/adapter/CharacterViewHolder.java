package com.mricomat.marvelcomicguide.ui.character.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterListListener;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mricomat on 23/05/2018.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private static final int START_IMAGE_URL = 20;

    @BindView(R.id.image_comic)
    ImageView mImageView;

    private PictureDownloader mPictureDownloader;
    private CharacterListListener mListener;
    private View mItemView;

    @Inject
    public CharacterViewHolder(View itemView, final CharacterListListener listener, PictureDownloader pictureDownloader) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mItemView = itemView;
        this.mListener = listener;
        this.mPictureDownloader = pictureDownloader;
    }

    public void bindModel(final ComicModel comicModel) {
        String imageUrl = comicModel.getThumbnail().getCompleteImagePath().substring(START_IMAGE_URL);
        mPictureDownloader.download(imageUrl, mImageView);
        mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onCharacterClick(comicModel);
                }
            }
        });
    }
}
