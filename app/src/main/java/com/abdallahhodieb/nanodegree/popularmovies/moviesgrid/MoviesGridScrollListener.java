package com.abdallahhodieb.nanodegree.popularmovies.moviesgrid;


import android.widget.AbsListView;

public class MoviesGridScrollListener implements AbsListView.OnScrollListener {

    private final GridDataFetcher dataFetcher;
    private long pageToLoad = 1L;

    public MoviesGridScrollListener(GridDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isIdle(scrollState) && reachedFirstHalf(view)) {
            dataFetcher.fetch(pageToLoad++);
        }
    }

    private boolean reachedFirstHalf(AbsListView view) {
        return view.getLastVisiblePosition() >= (view.getAdapter().getCount() - 1) / 2;
    }

    private boolean isIdle(int scrollState) {
        return scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
    }

    @Override
    public void onScroll(AbsListView lw, final int firstVisibleItem,
                         final int visibleItemCount, final int totalItemCount) {
    }
}
