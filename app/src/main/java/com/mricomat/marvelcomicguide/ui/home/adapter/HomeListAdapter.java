package com.mricomat.marvelcomicguide.ui.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.ImageModel;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListViewHolder> {

    private List<CharacterModel> mCharacters;
    private HomeListListener mCharacterListListener;
    private PictureDownloader mPictureDownloader;

    public HomeListAdapter(HomeListListener listener, PictureDownloader pictureDownloader) {

        /*CharacterModel caracter1 = new CharacterModel();
        ImageModel imageModel = new ImageModel();
        imageModel.setExtension("jpg");
        imageModel.setPath("http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16");
        caracter1.setThumbnail(imageModel);



        mCharacters = new ArrayList<>();
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);
        mCharacters.add(caracter1);*/
        mCharacters = new ArrayList<>();
        mCharacterListListener = listener;
        mPictureDownloader = pictureDownloader;
    }

    @Override
    public HomeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_list_item, parent, false);
        return new HomeListViewHolder(view, mCharacterListListener, mPictureDownloader);
    }

    @Override
    public void onBindViewHolder(HomeListViewHolder holder, int position) {
        holder.bindModel(mCharacters.get(position));
    }

    @Override
    public int getItemCount() {
        return mCharacters == null ? 0 : mCharacters.size();
    }

    public void setCharacters(List<CharacterModel> characters) {
        this.mCharacters = characters;
        notifyDataSetChanged();
    }

    public List<CharacterModel> getCharacters() {
        return mCharacters;
    }
}
