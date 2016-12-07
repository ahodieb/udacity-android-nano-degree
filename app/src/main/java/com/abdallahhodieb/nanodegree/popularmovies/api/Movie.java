package com.abdallahhodieb.nanodegree.popularmovies.api;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Movie {
    private String id;
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("overview")
    private String plot;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    private String popularity;

    public String getPosterImagePath(PosterSize size) {
        return String.format("https://image.tmdb.org/t/p/%s%s", size.toString(), posterPath);
    }

    public enum PosterSize {
        w92
        ,w154
        ,w185
        ,w342
        ,w500
        ,w780
        ,original
    }
}
