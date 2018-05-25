package com.mricomat.marvelcomicguide.ui.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListViewHolder extends RecyclerView.ViewHolder {

    private static final int START_IMAGE_URL = 20;

    @BindView(R.id.image_character)
    ImageView mImageView;

    @BindView(R.id.character_name)
    TextView mCharacterName;

    private View mItemView;
    private HomeListListener mListener;
    private PictureDownloader mPictureDownloader;

    public HomeListViewHolder(View itemView, final HomeListListener listener, PictureDownloader pictureDownloader) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mItemView = itemView;
        this.mListener = listener;
        this.mPictureDownloader = pictureDownloader;
    }

    public void bindModel(final CharacterModel character) {
        String imageUrl = character.getThumbnail().getCompleteImagePath().substring(START_IMAGE_URL);
        //mImageView.setImageResource(R.drawable.detail);
        mPictureDownloader.download(imageUrl, mImageView);
        mCharacterName.setText(character.getName());
        mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onCharacterClick(character);
                }
            }
        });
    }
}
