package com.abdallahhodieb.nanodegree.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdallahhodieb.nanodegree.popularmovies.api.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private final static Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setupToolbar();

        Movie movie = getMovieDataFromIntent();
        fillData(movie);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented in Stage 1", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void fillData(Movie movie) {
        setTitle(movie.getTitle());

        TextView title = (TextView) findViewById(R.id.movieTitle);
        title.setText(movie.getTitle());

        TextView rating = (TextView) findViewById(R.id.movieRating);
        rating.setText(movie.getRating());

        TextView duration = (TextView) findViewById(R.id.movieDuration);

        TextView year = (TextView) findViewById(R.id.movieYear);
        year.setText(movie.getReleaseDate());

        ImageView moviePoster = (ImageView) findViewById(R.id.moviePoster);
        Picasso.with(getBaseContext()).load(movie.getPosterImagePath(Movie.PosterSize.w185)).into(moviePoster);

        TextView plot = (TextView) findViewById(R.id.moviePlot);
        plot.setText(movie.getPlot());

    }

    private Movie getMovieDataFromIntent() {
        Intent intent = getIntent();
        return gson.fromJson(intent.getStringExtra(Intent.EXTRA_TEXT), Movie.class);
    }
}
