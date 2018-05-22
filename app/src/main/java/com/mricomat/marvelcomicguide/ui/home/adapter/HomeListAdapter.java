package com.mricomat.marvelcomicguide.ui.home.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListAdapter extends RecyclerView.Adapter {

    public static final int ITEM_VIEW_TYPE_BASIC = 0;
    public static final int ITEM_VIEW_TYPE_SEARCH = 1;
    private static final int ITEM_VIEW_TYPE_FOOTER = 2;

    private List<CharacterModel> mCharacters;
    private HomeListListener mCharacterListListener;
    private PictureDownloader mPictureDownloader;
    private int mType = ITEM_VIEW_TYPE_BASIC;

    public HomeListAdapter(HomeListListener listener, PictureDownloader pictureDownloader) {
        mCharacters = new ArrayList<>();
        mCharacterListListener = listener;
        mPictureDownloader = pictureDownloader;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;

        if (viewType == ITEM_VIEW_TYPE_BASIC) {
            View view = inflater.inflate(R.layout.home_list_item, parent, false);
            viewHolder = new HomeListViewHolder(view, mCharacterListListener, mPictureDownloader);
        } else if (viewType == ITEM_VIEW_TYPE_SEARCH) {
            View view = inflater.inflate(R.layout.home_list_searched_item, parent, false);
            viewHolder = new HomeListViewHolder(view, mCharacterListListener, mPictureDownloader);
        } else {
            View view = inflater.inflate(R.layout.progress_bar_list_item, parent, false);
            viewHolder = new ProgressBarViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeListViewHolder) {
            ((HomeListViewHolder) holder).bindModel(mCharacters.get(position));
        } else {
            ((ProgressBarViewHolder) holder).getProgressBar().setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mCharacters.get(position) != null) {
            return mType;
        } else {
            return ITEM_VIEW_TYPE_FOOTER;
        }
    }

    @Override
    public int getItemCount() {
        return mCharacters == null ? 0 : mCharacters.size();
    }

    public void setType(int type){
        this.mType = type;
    }

    public GridLayoutManager.SpanSizeLookup getNewSpanSizeLookUp() {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (getItemViewType(position) == ITEM_VIEW_TYPE_BASIC) {
                    return 1;
                } else {
                    return 2;
                }
            }
        };
    }

    public void setCharacters(List<CharacterModel> characters) {
        this.mCharacters.clear();
        this.mCharacters = characters;
        notifyDataSetChanged();
    }

    public List<CharacterModel> getCharacters() {
        return mCharacters;
    }

    public void addCharacters(List<CharacterModel> characters) {
        if (mCharacters.size() != 0) {
            mCharacters.remove(null);
        }
        mCharacters.addAll(characters);
        notifyDataSetChanged();
    }

    public void addCharacter(CharacterModel characterModel) {
        if (characterModel != null) {
            mCharacters.add(characterModel);
            notifyDataSetChanged();
        } else {
            mCharacters.add(null);
            notifyItemInserted(mCharacters.size() - 1);
        }
    }

    public void removeAll() {
        mCharacters.clear();
        notifyDataSetChanged();
    }
}
