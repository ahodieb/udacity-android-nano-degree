package com.abdallahhodieb.nanodegree.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.abdallahhodieb.nanodegree.popularmovies.api.Movie;
import com.abdallahhodieb.nanodegree.popularmovies.api.TMDBService;
import com.abdallahhodieb.nanodegree.popularmovies.moviesgrid.GridDataFetcher;
import com.abdallahhodieb.nanodegree.popularmovies.moviesgrid.MoviesGridScrollListener;
import com.abdallahhodieb.nanodegree.popularmovies.moviesgrid.MoviesGridViewAdapter;
import com.google.gson.Gson;

import static com.abdallahhodieb.nanodegree.popularmovies.BuildConfig.TMDB_API_KEY;
import static com.abdallahhodieb.nanodegree.popularmovies.api.ApiUtil.createApiService;

public class MainActivity extends AppCompatActivity {
    private final static Gson gson = new Gson();

    private MoviesGridViewAdapter gridAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id ==  R.id.action_order_by ) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridAdapter = new MoviesGridViewAdapter(this, R.layout.grid_item_layout);
        GridView gridView = (GridView) findViewById(R.id.moviesGrid);
        gridView.setAdapter(gridAdapter);

        TMDBService service = createApiService();
        final GridDataFetcher gridDataFetcher = new GridDataFetcher(service, TMDB_API_KEY,
                gridView, gridAdapter);
        setGridListeners(gridView, gridDataFetcher);

        gridDataFetcher.fetch();
    }

    private void setGridListeners(GridView gridView, GridDataFetcher gridDataFetcher) {
        gridView.setOnScrollListener(new MoviesGridScrollListener(gridDataFetcher));
        setOnClickListener(gridView);
    }

    private void setOnClickListener(GridView gridView) {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openDetailActivity(gridAdapter.getItem(i));
            }
        });
    }

    private void openDetailActivity(Movie item) {
        final Intent intent = new Intent(this, MovieDetailsActivity.class)
                .putExtra(Intent.EXTRA_TEXT, gson.toJson(item));
        startActivity(intent);
    }
}