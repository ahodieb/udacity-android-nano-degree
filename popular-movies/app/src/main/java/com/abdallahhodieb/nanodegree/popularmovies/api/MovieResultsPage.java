package com.abdallahhodieb.nanodegree.popularmovies.api;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MovieResultsPage {
    private long page;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    @SerializedName("results")
    List<Movie> movies;
}
