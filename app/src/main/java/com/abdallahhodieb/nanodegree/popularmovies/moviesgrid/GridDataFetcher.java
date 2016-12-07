package com.abdallahhodieb.nanodegree.popularmovies.moviesgrid;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.abdallahhodieb.nanodegree.popularmovies.R;
import com.abdallahhodieb.nanodegree.popularmovies.api.MovieResultsPage;
import com.abdallahhodieb.nanodegree.popularmovies.api.TMDBService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class GridDataFetcher {
    private final static String LOG_TAG = GridDataFetcher.class.getName();

    private final String apiKey;
    private final TMDBService service;
    private final MoviesGridViewAdapter adapter;
    private final GridView gridView;
    private long page;

    public GridDataFetcher(@NonNull TMDBService service, @NonNull String apiKey,
                           @NonNull GridView gridView, @NonNull MoviesGridViewAdapter adapter) {

        this.service = service;
        this.apiKey = apiKey;
        this.adapter = adapter;
        this.gridView = gridView;
        this.page = 1L;
    }

    public void fetchNextPage() {
        page++;
        fetch();
    }

    public void fetch() {
        String sortBy = getSortOrderFromPreferences();
        Call<MovieResultsPage> call = service.discoverMovies(apiKey, sortBy, page);

        call.enqueue(new Callback<MovieResultsPage>() {
            @Override
            public void onResponse(Call<MovieResultsPage> call, Response<MovieResultsPage> page) {
                adapter.addAll(page.body().getMovies());
            }

            @Override
            public void onFailure(Call<MovieResultsPage> call, Throwable t) {
                Snackbar.make(gridView, R.string.no_connection_found, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fetch();
                            }
                        })
                        .show();
                Log.e(LOG_TAG, "Failed to fetch data", t);
            }
        });
    }

    private String getSortOrderFromPreferences() {
        Context context = adapter.getContext();
        Resources resources = context.getResources();
        String sortOrderKey = resources.getString(R.string.pref_sort_order_key);
        String defaultValue = resources.getString(R.string.pref_sort_order_default_value);
        SharedPreferences prefs = getDefaultSharedPreferences(context);

        return prefs.getString(sortOrderKey, defaultValue);
    }
}
