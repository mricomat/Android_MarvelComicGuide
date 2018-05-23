package com.mricomat.marvelcomicguide.ui.character.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterListListener;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mricomat on 23/05/2018.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private CharacterListListener mListListener;
    private PictureDownloader mPictureDownloader;
    private List<ComicModel> mComics;

    public CharacterAdapter(CharacterListListener listListener, PictureDownloader pictureDownloader) {
        this.mListListener = listListener;
        this.mPictureDownloader = pictureDownloader;
        mComics = new ArrayList<>();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comic_list_item, parent, false);
        return new CharacterViewHolder(view, mListListener, mPictureDownloader);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        holder.bindModel(mComics.get(position));
    }

    @Override
    public int getItemCount() {
        return mComics == null ? 0 : mComics.size();
    }

    public void setComics(List<ComicModel> comics) {
        mComics = comics;
        notifyDataSetChanged();
    }

    public List<ComicModel> getComics() {
        return mComics;
    }
}
