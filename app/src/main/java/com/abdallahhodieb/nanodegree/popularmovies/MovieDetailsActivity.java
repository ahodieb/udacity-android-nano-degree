package com.abdallahhodieb.nanodegree.popularmovies;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdallahhodieb.nanodegree.popularmovies.api.Movie;
import com.abdallahhodieb.nanodegree.popularmovies.api.TMDBService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdallahhodieb.nanodegree.popularmovies.api.ApiUtil.createApiService;

public class MovieDetailsActivity extends AppCompatActivity {

    private final static Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setupToolbar();
        setupAddToFavouritsButton();

        final Movie movie = getMovieDataFromIntent();
        fillMovieDetailsFromService(movie);
    }

    private void setupAddToFavouritsButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented in Stage 1", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fillMovieDetailsFromService(final Movie movie) {
        TMDBService service = createApiService();
        Call<Movie> getMovieDetails = service.getMovieDetails(movie.getId(), BuildConfig.TMDB_API_KEY);

        getMovieDetails.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                fillData(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                fillData(movie);
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
        if(movie.getRuntime() != null) {
            Resources res = getResources();
            String ratingText = String.format(res.getString(R.string.rating_format), movie.getRating());
            rating.setText(ratingText);
        }

        TextView duration = (TextView) findViewById(R.id.movieDuration);
        if(movie.getRuntime() != null) {
            Resources res = getResources();
            String durationText = String.format(res.getString(R.string.duration_format), movie.getRuntime());
            duration.setText(durationText);
        }

        TextView year = (TextView) findViewById(R.id.movieYear);
        if(movie.getReleaseDate() != null ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(movie.getReleaseDate());
            year.setText(calendar.get(Calendar.YEAR) + "");
        }

        ImageView moviePoster = (ImageView) findViewById(R.id.moviePoster);
        Picasso.with(getBaseContext())
                .load(movie.getPosterImagePath(Movie.PosterSize.w185))
                .error(R.drawable.not_available)
                .into(moviePoster);

        TextView plot = (TextView) findViewById(R.id.moviePlot);
        plot.setText(movie.getPlot());
    }

    private Movie getMovieDataFromIntent() {
        Intent intent = getIntent();
        return gson.fromJson(intent.getStringExtra(Intent.EXTRA_TEXT), Movie.class);
    }
}
