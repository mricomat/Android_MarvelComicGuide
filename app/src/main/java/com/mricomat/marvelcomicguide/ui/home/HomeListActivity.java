package com.mricomat.marvelcomicguide.ui.home;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.ui.home.view.HomeListViewFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class HomeListActivity extends DaggerAppCompatActivity {

    private final static int VIEW_FRAME = R.id.contentFrame;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    HomeListViewFragment mHomeListViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_list_activity);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            navigateViewFragment();
        }
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.marvel_title));
        }
    }

    private void navigateViewFragment() {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(VIEW_FRAME, mHomeListViewFragment)
            .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
