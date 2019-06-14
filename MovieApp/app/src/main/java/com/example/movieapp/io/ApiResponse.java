package com.example.movieapp.io;

import com.example.movieapp.Model.MoviesEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private List<MoviesEntity> movies;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "page='" + page + '\'' +
                ", total_results=" + total_results +
                ", total_pages='" + total_pages + '\'' +
                ", movies=" + movies +
                '}';
    }


    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<MoviesEntity> getMovies() {
        return movies;
    }
}
