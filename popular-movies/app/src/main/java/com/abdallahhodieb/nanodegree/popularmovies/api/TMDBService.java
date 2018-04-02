package com.abdallahhodieb.nanodegree.popularmovies.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBService {
    @GET("discover/movie")
    Call<MovieResultsPage> discoverMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, @Query("page") Long page);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") long movieId, @Query("api_key") String apiKey);
}
