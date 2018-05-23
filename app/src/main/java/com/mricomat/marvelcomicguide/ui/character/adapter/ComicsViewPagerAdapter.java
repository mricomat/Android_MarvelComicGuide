package com.mricomat.marvelcomicguide.ui.character.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mricomat on 23/05/2018.
 */

public class ComicsViewPagerAdapter extends PagerAdapter {

    @Inject
    Context mContext;

    @Inject
    PictureDownloader mPictureDownloader;

    private LayoutInflater mLayoutInflater;
    private List<ComicModel> mComics;

    public ComicsViewPagerAdapter(List<ComicModel> comics) {
        mComics = comics;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mComics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       View itemView = mLayoutInflater.inflate(R.layout.comic_list_item, container);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_character);
        mPictureDownloader.download(mComics.get(position).getThumbnail().getCompleteImagePath(), imageView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
