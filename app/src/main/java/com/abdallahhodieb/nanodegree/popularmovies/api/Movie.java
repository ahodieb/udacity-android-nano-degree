package com.abdallahhodieb.nanodegree.popularmovies.api;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class Movie {
    private final  static SimpleDateFormat releaseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final static String LOG_TAG = Movie.class.getName();

    private long id;
    private String title;
    private String popularity;
    private String runtime;

    @SerializedName("release_date")
    @Getter(AccessLevel.NONE)
    private String releaseDateString;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("overview")
    private String plot;

    @SerializedName("poster_path")
    private String posterPath;

    public String getPosterImagePath(PosterSize size) {
        return String.format("https://image.tmdb.org/t/p/%s%s", size.toString(), posterPath);
    }

    public Date getReleaseDate() {
        try {
            return releaseDateFormat.parse(releaseDateString);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Failed to parse release date", e);
            return null;
        }
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
