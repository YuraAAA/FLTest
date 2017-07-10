package com.dev24.flicker.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;

import com.dev24.flicker.R;
import com.dev24.flicker.adapter.BaseRVAdapter;
import com.dev24.flicker.adapter.PhotosAdapter;
import com.dev24.flicker.core.DataProvider;
import com.dev24.flicker.core.fsm.Switcher;
import com.dev24.flicker.model.Photo;
import com.dev24.flicker.utils.EndlessRecyclerOnScrollListener;
import com.dev24.flicker.utils.ItemOffsetDecorator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import rx.Subscription;

/**
 * Created by Yuriy Aizenberg
 */

@EFragment(R.layout.fragment_main)
public class PhotoListFragment extends BaseFragment {

    @Bean
    DataProvider dataProvider;
    @ViewById(R.id.rv_main)
    RecyclerView recyclerView;
    @ViewById(R.id.progress_loading)
    ProgressBar progressBar;
    boolean isLoading;
    boolean isEndOfPhotos;
    private PhotosAdapter photosAdapter;
    private int page = 1;


    @AfterViews
    public void initialize() {
        photosAdapter = new PhotosAdapter(getActivity());
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new ItemOffsetDecorator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(photosAdapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        getPhotos(page);
        photosAdapter.setTouchListener((data, position) -> {
            Bundle args = new Bundle();
            args.putSerializable(ShowPhotoFragment.KEY, data);
            Switcher.obtainSwitcher(getActivity()).switchToAsAdd(ShowPhotoFragment_.class, args);
        });
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore() {
                callLoadMore();
            }
        });
    }

    private Subscription getPhotos(int page) {
        switchLoading(true);
        return dataProvider.getPhotos(page, photos -> {
            switchLoading(false);
            List<Photo> photo = photos.getPhotos().getPhoto();
            if (photosAdapter.isEmpty()) {
                photosAdapter.setData(photo);
            } else {
                photosAdapter.addData(photo);
            }
            if (photo.size() < DataProvider.PER_PAGE) {
                isEndOfPhotos = true;
            }
        }, PhotoListFragment.this);
    }

    private void switchLoading(boolean isLoad) {
        if (isLoad) {
            isLoading = true;
            progressBar.setVisibility(View.VISIBLE);
        } else {
            isLoading = false;
            progressBar.setVisibility(View.GONE);
        }
    }

    private void callLoadMore() {
        if (isLoading) return;
        if (isEndOfPhotos) return;
        isLoading = true;
        page++;
        getPhotos(page);
    }

}
