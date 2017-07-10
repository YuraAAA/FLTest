package com.dev24.flicker.utils;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Yuriy Aizenberg
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private static final int VISIBLE_THRESHOLD = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private Handler postingHandler = new Handler();

    private StaggeredGridLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(StaggeredGridLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = mLinearLayoutManager.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        int[] firstVisibleItems = null;
        firstVisibleItems = mLinearLayoutManager.findFirstVisibleItemPositions(firstVisibleItems);
        if (firstVisibleItems != null && firstVisibleItems.length > 0) {
            pastVisibleItems = firstVisibleItems[0];
        }

        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
            onLoadMore();
        }
    }

    public abstract void onLoadMore();
}